package com.bmcl.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HeroTest {
    Hero hero = new Hero(1,1);
    @Test
    void moveUpTest(){
        Position expected = new Position(1,0);
        hero.setPosition(hero.moveUp());
        assertEquals(hero.getPosition(), expected);
    }

    @Test
    void moveDownTest(){
        Position expected = new Position(1,2);
        hero.setPosition(hero.moveDown());
        assertEquals(hero.getPosition(), expected);
    }
    @Test
    void moveLeftTest(){
        Position expected = new Position(0,1);
        hero.setPosition(hero.moveLeft());
        assertEquals(hero.getPosition(), expected);
    }
    @Test
    void moveRightTest(){
        Position expected = new Position(2,1);
        hero.setPosition(hero.moveRight());
        assertEquals(hero.getPosition(), expected);
    }
    @Test
    void jumpTest(){
        hero.setJumpState(true);
        assertEquals(hero.isJumpState(), true);
    }

    @Test
    void direcitonTestEsquerda(){
        hero.moveLeft();
        assertEquals(hero.getDirecao(), false);
    }
    @Test
    void livesTest(){
        hero.setLives(2);
        assertEquals(hero.getLives(), 2);
    }


    @Test
    void marioDireitaTest(){
        TextGraphics graphicsMock = Mockito.spy(TextGraphics.class);
        hero.marioDireita(graphicsMock);
        Mockito.verify(graphicsMock).putString(new TerminalPosition(hero.position.getX(), hero.position.getY()), "m");
    }
    @Test
    void marioEsquerdaTest(){
        TextGraphics graphicsMock = Mockito.spy(TextGraphics.class);
        hero.marioEsquerda(graphicsMock);
        Mockito.verify(graphicsMock).putString(new TerminalPosition(hero.position.getX(), hero.position.getY()), "n");
    }
    @Test
    void luigiDireitaTest(){
        TextGraphics graphicsMock = Mockito.spy(TextGraphics.class);
        hero.luigiDireita(graphicsMock);
        Mockito.verify(graphicsMock).putString(new TerminalPosition(hero.position.getX(), hero.position.getY()), "m");
    }
    @Test
    void luigiEsquerdaTest(){
        TextGraphics graphicsMock = Mockito.spy(TextGraphics.class);
        hero.luigiEsquerda(graphicsMock);
        Mockito.verify(graphicsMock).putString(new TerminalPosition(hero.position.getX(), hero.position.getY()), "n");
    }
    @Test
    void setDirectionTest(){
        hero.setDirecao(true);
        assertEquals(hero.getDirecao(), true);
    }
}