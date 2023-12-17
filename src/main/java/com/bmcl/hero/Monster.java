package com.bmcl.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element {

    private boolean isHit = false;

    public Monster(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF3333"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }


    public Position move() {
        if (position.getX()<40)
            return new Position(position.getX() + 1, position.getY());
        else
            return new Position(position.getX() - 1, position.getY());
    }

<<<<<<< HEAD
    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
=======
    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }



>>>>>>> efb349a5762c08a9b2b49965f1b2ee8cc20f8d17
}
