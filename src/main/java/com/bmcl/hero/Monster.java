package com.bmcl.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element {

    private boolean isHit = false;


    private boolean direcao = false; //false == esquerda , true == direita

    public Monster(int x, int y) {
        super(x, y);
    }

    public Monster(int x, int y , boolean direcao) {
        super(x, y);
        this.direcao = direcao;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#f4f13a"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }

    public void drawhit(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "W");
    }


    public Position move() {
        if (direcao)
            return new Position(position.getX() + 1, position.getY());
        else
            return new Position(position.getX() - 1, position.getY());
    }


    public boolean isHit() {
        return isHit;
    }


    public void setHit(boolean hit) {
        isHit = hit;
    }

    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

}
