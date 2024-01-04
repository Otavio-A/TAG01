package com.bmcl.hero;

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
import java.io.File;
import java.io.IOException;

public class Game {
    private final TerminalScreen screen;
    private final Arena arena;
    private Font font;
    private Menu menu;


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
            //draw ao menu e atualiza o ecrã
            menu.draw();
            screen.refresh();

            //le o input do utilizador e processa a key
            KeyStroke key = screen.readInput();
            menu.processKey(key);

            //verifica se a tecla pressionada foi o enter
            if (key.getKeyType() == KeyType.Enter) {
                //se a opção start estiver for selecionada quebra o loop e inicia o jogo
                if (menu.isStartSelected()) {
                    break;
                } else {
                    //se a opção selecionada for exit, fecha o programa.
                    screen.close();
                    System.exit(0);
                }
            }
        }
    }


    public void draw() throws IOException { // Isso estava como Private antes

        grafics = screen.newTextGraphics();

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

                if (key != null) {
                    if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'p' && !gamePaused) {
                        arena.drawPause(grafics);
                        gamePaused = true;
                    } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'p' && gamePaused) {
                        gamePaused = false;
                    }

                    if (gamePaused) {
                        while (true) {
                            key = screen.readInput();
                            //System.out.println("Game paused.");

                            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'p' && gamePaused) {
                                //System.out.println("Game not paused.");
                                gamePaused = false;
                                break;
                            }
                        }
                    }

                    if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                        screen.close();
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
                } catch (InterruptedException e) {
                }


            }
        }
    }
