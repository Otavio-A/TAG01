package com.bmcl.hero;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private final TerminalScreen screen;
    private final Arena arena;

    public Game(int width, int heigt) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, heigt)).createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        arena = new Arena(width, heigt, this); //pro Jump() em Arena
    }

    public void draw() throws IOException { // Isso estava como Private antes
        screen.clear();
        arena.draw(screen.newTextGraphics());
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
            if (key != null) {
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                    screen.close();
                if (key.getKeyType() == KeyType.EOF)
                    break;
                arena.processKey(key);

            }

            arena.Monsterfall();

            if (!arena.isHeroJumping()){
                arena.Herofall();
            }
            if (!arena.isLuigiJumping()){
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
