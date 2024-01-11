package com.bmcl.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    Hero hero = new Hero(1,1);
    @Test
    public void moveUpTest(){
        Position expected = new Position(1,0);
        hero.setPosition(hero.moveUp());
        assertEquals(hero.getPosition(), expected);
    }

    @Test
    public void moveDownTest(){
        Position expected = new Position(1,2);
        hero.setPosition(hero.moveDown());
        assertEquals(hero.getPosition(), expected);
    }
    @Test
    public void moveLeftTest(){
        Position expected = new Position(0,1);
        hero.setPosition(hero.moveLeft());
        assertEquals(hero.getPosition(), expected);
    }
    @Test
    public void moveRightTest(){
        Position expected = new Position(2,1);
        hero.setPosition(hero.moveRight());
        assertEquals(hero.getPosition(), expected);
    }
    @Test
    public void jumpTest(){
        hero.setJumpState(true);
        assertEquals(hero.isJumpState(), true);
    }


}