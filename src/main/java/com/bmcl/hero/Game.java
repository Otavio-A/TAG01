package com.bmcl.hero;

import com.bmcl.hero.Menu.Menu;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.awt.*;
import java.io.*;

public class Game {
    private final TerminalScreen screen;
    private final Arena arena;
    private Font font;
    private com.bmcl.hero.Menu.Menu menu;


    TextGraphics grafics;

    boolean gamePaused = false;

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }


    public Font changeFont(String path, int size) {
        File fontFile = new File(path);
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font loaded = font.deriveFont(Font.PLAIN, size);
        return loaded;
    }


    public Game(int width, int heigt) throws IOException {
        setFont(changeFont("src/main/java/com/bmcl/hero/SquareUpdated.ttf", 20));
        AWTTerminalFontConfiguration cfg = new SwingTerminalFontConfiguration(true, AWTTerminalFontConfiguration.BoldMode.NOTHING, getFont());
        Terminal terminal = new DefaultTerminalFactory()
                .setForceAWTOverSwing(true)
                .setInitialTerminalSize(new TerminalSize(width, heigt))
                .setTerminalEmulatorFontConfiguration(cfg)
                .createTerminal();
        screen = new TerminalScreen(terminal);
        grafics = screen.newTextGraphics();

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        // Cria um novo menu e associa ao ecra atual
        menu = new Menu(screen.newTextGraphics());

        //Aguarda input do utilizador antes de começar o jogo
        waitForMenuInput();

        arena = new Arena(width, heigt, this); //pro Jump() em Arena
    }

    private void waitForMenuInput() throws IOException {
        while (true) {
            menu.draw();
            screen.refresh();

            KeyStroke key = screen.readInput();
            menu.processKey(key);

            if (key.getKeyType() == KeyType.Enter) {
                if (menu.isStartSelected()) {
                    break;
                } else if (menu.isLeaderboardSelected()) {
                    displayLeaderboard();
                } else if (menu.isExitSelected()) {
                    System.exit(0);
                }
            }
        }
    }



    public void displayLeaderboard() {

        screen.clear();
        File ficheiro = new File("src/main/java/com/bmcl/hero/leaderboard.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(ficheiro))) {
            String line;
            int lineNumber = 0;
            grafics.setForegroundColor(TextColor.ANSI.WHITE);

            while ((line = reader.readLine()) != null) {
                grafics.putString(new TerminalPosition(1, 1 + lineNumber), line);
                lineNumber++;
            }
            screen.refresh();

            screen.readInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void draw() throws IOException {
        if (grafics == null) {
            grafics = screen.newTextGraphics();
        }

        if (gamePaused) {
            arena.drawPause(grafics);
        } else {
            screen.clear();
            arena.draw(grafics);
        }

        screen.refresh();
    }

    //Exemplo basico sem controlo de velocidade

    public void run() throws IOException {

        int FPS = 144;
        int frameTime = 1000 / FPS;
        long lastMonsterMovement = 0;

        while (true) {
            long startTime = System.currentTimeMillis();

            draw();


            KeyStroke key = screen.pollInput(); //Não fica à espera de teclas, vai armazenando num buffer, devolve null se nenhuma tecla está no buffer
            //funcao para processar a keyinput
            menu.processKey(key);

            if (!gamePaused){
                if (key != null) {
                    if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'p' && !gamePaused) {
                        arena.drawPause(grafics);
                        gamePaused = true;
                    }
                    if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                        System.exit(0);
                    }
                    if (key.getKeyType() == KeyType.EOF) {
                        break;
                    }
                    arena.processKey(key);
                }
                arena.Monsterfall();
                if (!arena.isHeroJumping()) {
                    arena.Herofall();
                }
                if (!arena.isLuigiJumping()) {
                    arena.Luigifall();
                }

                if (startTime - lastMonsterMovement > 500) {
                    arena.moveMonsters();
                    arena.verifyMonsterCollisions();
                    lastMonsterMovement = startTime;
                }


                long elapsedTime = System.currentTimeMillis() - startTime;
                long sleepTime = frameTime - elapsedTime;
                if (sleepTime > 0) try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {}

            }
            else if (gamePaused){
                if (key != null){
                    if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                        System.exit(0);}
                    if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'p' && gamePaused) {
                        gamePaused = false;
                    }
                }
            }
        }
    }
}
