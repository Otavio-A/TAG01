package com.bmcl.hero;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ArenaTest {
    Hero hero = new Hero(34,19);
    Luigi luigi = new Luigi(35,24);
    Game game;

    {
        try {
            game = new Game(80,21);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Arena arena = new Arena(80,21,game);
    Monster monster = new Monster(20,20);
    @Test
    void monsterMoveWall(){
        Position badPosition = new Position(15, 20);
        monster.setPosition(badPosition);
        assertFalse(arena.canBichoMove(monster.getPosition()));
    }

    @Test
    void heroMoveWall(){
        Position badPosition = new Position(15, 20);
        hero.setPosition(badPosition);
        assertFalse(arena.canBichoMove(monster.getPosition()));
    }

    @Test
    void luigiMoveWall(){
        Position badPosition = new Position(20,15);
        luigi.setPosition(badPosition);
        assertFalse(arena.canBichoMove(monster.getPosition()));
    }


    @Test
    void isPlat(){
        Position plataform = new Position(45,4);
        hero.setPosition(plataform);
        assertTrue(arena.isplat(hero.getPosition()));
    }
    @Test
    void powBlock(){   //USAR UM SPY PARA VER SE O POWBLOCK RODOU
        Position position = new Position(39, 16);
        Position plataform = new Position(35,4);
        hero.setPosition(position);
        hero.setJumpState(true);

    }

}