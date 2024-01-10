package com.bmcl.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class MonsterEspecial extends Monster {

    int VidasMonstros = 3;
    boolean especial = true;

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean espc) {
        especial = espc;
    }
    public boolean getVidas() {
        return especial;
    }

    public void setVidas(boolean espc) {
        especial = espc;
    }


    public MonsterEspecial(int x, int y, boolean direcao) {
        super(x, y, direcao);
    }

    private boolean isHit = false;

    public void setDirecao(boolean direcao) {
        this.direcao = direcao;
    }

    private boolean direcao = false; //false == esquerda , true == direita


    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }

    public void drawhit(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "W");
    }


    public Position move() {
        if (direcao)
            return new Position(position.getX() - 1, position.getY());
        else
            return new Position(position.getX() + 1, position.getY());
    }


    public boolean isHit() {
        return isHit;
    }


    public void setHit(boolean hit) {
        VidasMonstros = VidasMonstros -1;
        if (VidasMonstros == 0){
            isHit = hit;
        }
    }

    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

}
