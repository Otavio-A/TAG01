package com.bmcl.hero;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element {
    public Hero(int x, int y) {
        super(x, y);
    }

    private boolean direcao = false ; // verdadeiro = direita , falso = esquerda
    private boolean jumpState = false;

    public boolean isJumpState() {
        return jumpState;
    }

    public void setJumpState(boolean jumpState) {
        this.jumpState = jumpState;
    }

    public boolean getDirecao() {
        return direcao;
    }

    public void setDirecao(boolean direcao) {
        this.direcao = direcao;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }

    public void draw(TextGraphics graphics) {

    }

    public void marioDireita(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#e03e3e"));
        //graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "m");
    }
    public void marioEsquerda(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#e03e3e"));
        //graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "n");
    }

    public void luigiDireita(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#2afc31"));
        //graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "m");
    }

    public void luigiEsquerda(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#2afc31"));
        //graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "n");
    }
}
