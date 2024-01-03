package com.bmcl.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element {

    private boolean empty = false;

    public Wall(int x, int y) {
        super(x, y);
    }
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public void draw(TextGraphics graphics) {



        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));//fundo preto
        graphics.setForegroundColor(TextColor.Factory.fromString("#0000FF"));
        //primeira plat
        graphics.fillRectangle(new TerminalPosition(0, 5), new TerminalSize(35, 1), 'p');
        graphics.fillRectangle(new TerminalPosition(45, 5), new TerminalSize(35, 1), 'p');

        //segunda plat
        graphics.fillRectangle(new TerminalPosition(0, 11), new TerminalSize(7, 1), 'p');
        graphics.fillRectangle(new TerminalPosition(24, 10), new TerminalSize(35, 1), 'p');
        graphics.fillRectangle(new TerminalPosition(73, 11), new TerminalSize(7, 1), 'p');

        //terceira plat
        graphics.fillRectangle(new TerminalPosition(0, 15), new TerminalSize(30, 1), 'p');
        graphics.fillRectangle(new TerminalPosition(50, 15), new TerminalSize(30, 1), 'p');

        //chão
        graphics.fillRectangle(new TerminalPosition(0, 20), new TerminalSize(80, 1), 'p');

        //fundo POW
        graphics.setBackgroundColor(TextColor.Factory.fromString("#00FFF0"));
        if (empty){graphics.setBackgroundColor(TextColor.Factory.fromString("#ff0000"));}

        //POW
        graphics.fillRectangle(new TerminalPosition(39, 15), new TerminalSize(1, 1), ' ');


        graphics.setBackgroundColor(TextColor.Factory.fromString("#00FF00"));//fundo verde

        //cima esquerda
        graphics.fillRectangle(new TerminalPosition(0, 2), new TerminalSize(1, 3), ' ');
        graphics.fillRectangle(new TerminalPosition(1, 2), new TerminalSize(2, 1), ' ');

        //cima direita
        graphics.fillRectangle(new TerminalPosition(79, 2), new TerminalSize(1, 3), ' ');
        graphics.fillRectangle(new TerminalPosition(77, 2), new TerminalSize(2, 1), ' ');

        //baixo esquerda
        graphics.fillRectangle(new TerminalPosition(0, 19), new TerminalSize(2, 1), ' ');

        //baixo direita
        graphics.fillRectangle(new TerminalPosition(78, 19), new TerminalSize(2, 1), ' ');

    }


}
