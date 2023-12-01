package com.bmcl.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element {
    public Wall(int x, int y) {
        super(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#0000FF"));
        // graphics.putString(new TerminalPosition(position.getX(), position.getY()), " ");



        graphics.fillRectangle(new TerminalPosition(0, 5), new TerminalSize(35, 1), ' ');
        graphics.fillRectangle(new TerminalPosition(45, 5), new TerminalSize(35, 1), ' ');

        graphics.fillRectangle(new TerminalPosition(0, 11), new TerminalSize(7, 1), ' ');
        graphics.fillRectangle(new TerminalPosition(24, 10), new TerminalSize(35, 1), ' ');
        graphics.fillRectangle(new TerminalPosition(73, 11), new TerminalSize(7, 1), ' ');



        graphics.fillRectangle(new TerminalPosition(0, 15), new TerminalSize(30, 1), ' ');
        graphics.fillRectangle(new TerminalPosition(50, 15), new TerminalSize(30, 1), ' ');


        graphics.fillRectangle(new TerminalPosition(0, 20), new TerminalSize(80, 1), ' ');

    }
}
