package com.bmcl.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class Menu {

    private final TextGraphics graphics;
    private boolean startSelected = true;

    public Menu(TextGraphics graphics){
        this.graphics = graphics;

    }

    public void draw() {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), graphics.getSize(), ' ');

        graphics.setForegroundColor(startSelected ? TextColor.ANSI.GREEN : TextColor.ANSI.DEFAULT);
        graphics.putString(new TerminalPosition(35, 0), "MARIO BROS ARCADE");

        graphics.setForegroundColor(startSelected ? TextColor.ANSI.GREEN : TextColor.ANSI.DEFAULT);
        graphics.putString(new TerminalPosition(40, 5), "START");

        graphics.setForegroundColor(startSelected ? TextColor.ANSI.GREEN : TextColor.ANSI.DEFAULT);
        graphics.putString(new TerminalPosition(40, 7), "EXIT");
    }


    public void processKey(KeyStroke key) throws IOException{
        if(key.getKeyType()== KeyType.ArrowUp || key.getKeyType()==KeyType.ArrowDown){
            startSelected = !startSelected;
            draw();
        } else if (key.getKeyType()==KeyType.Enter) {
            if(startSelected){
                startGame();
            }else{
                System.exit(0);
            }

        }
    }

    private void startGame() throws IOException{
        Game game = new Game(80,20);
        game.run();
    }

    public boolean getStartSelected() { 
        return startSelected;
    }
}
