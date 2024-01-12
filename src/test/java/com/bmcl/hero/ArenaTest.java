package com.bmcl.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;


import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.googlecode.lanterna.input.KeyType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArenaTest {
    Hero hero = new Hero(34,19);


    Game game= Mockito.mock(Game.class);
    Arena arena = new Arena(80,21,game);

    Monster monster = new Monster(20,20,true);

    List<Monster> monsters = new ArrayList<>();



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
    void isPlat(){
        Position plataform = new Position(45,4);
        hero.setPosition(plataform);
        assertTrue(arena.isplat(hero.getPosition()));
    }
    @Test
    void isMonsterPlat(){
        Position plataform = new Position(45,4);
        monster.setPosition(plataform);
        assertTrue(arena.isplat(monster.getPosition()));
    }



    @Test
    void pow(){
        monsters = arena.getMonsters();
        Position plataform = new Position(45,4);
        Position underPlat = new Position(45,6);

        monster.setPosition(plataform);
        hero.setPosition(underPlat);
        monsters.clear();
        monsters.add(monster);
        arena.applyHit(hero);

        assertTrue(monster.isHit());
    }
    @Test
    void gravity(){
        monsters = arena.getMonsters();
        Position abovePlat = new Position(45,2);
        Position afterFall = new Position(45, 3);
        monster.setPosition(abovePlat);
        monsters.add(monster);
        arena.Monsterfall();
        assertEquals(afterFall, monster.getPosition());
    }

    @Test
    void arenaJumpTest(){
        Position plataform = new Position(45,4);
        Position jumpPostion = new Position(45, -2);
        hero.setPosition(plataform);
        arena.jump(hero);
        assertEquals(hero.getPosition(), jumpPostion);

    }
    @Test
    void deathTest(){
        Position plataform = new Position(45,4);
        monsters = arena.getMonsters();
        monster.setPosition(plataform);
        hero = arena.getHero();
        monster.setHit(false);
        monsters.clear();
        monsters.add(monster);
        hero.setPosition(plataform);
        arena.verifyMonsterCollisions();
        int lives = hero.getLives();
        assertEquals(lives, 2);
    }

    // PROCESS KEY TESTS START HERE-----------------------------

    @Test
    void processKeyTestDireita() throws IOException {
        hero = arena.getHero();
        KeyStroke key = new KeyStroke(ArrowRight);
        arena.processKey(key);
        assertEquals(hero.getDirecao(), true);

    }
    @Test
    void processKeyTestEsquerda() throws IOException {
        hero = arena.getHero();
        KeyStroke key = new KeyStroke(ArrowLeft);
        arena.processKey(key);
        assertEquals(hero.getDirecao(), false);

    }
    @Test
    void processLuigiKeyTestDireita() throws IOException {
        Hero luigi = arena.getLuigi();
        KeyStroke key = KeyStroke.fromString("d");
        arena.processKey(key);
        assertEquals(luigi.getDirecao(), true);

    }
    @Test
    void processLuigiKeyTestEsquerda() throws IOException {
        hero = arena.getHero();
        KeyStroke key = KeyStroke.fromString("a");
        arena.processKey(key);
        assertEquals(hero.getDirecao(), false);
    }

    //PROCESS KEY TESTS END HERE-----------------------------

    //DRAW TESTS START HERE-----------------------------

    @Test
    void drawTest3Lives(){
        TextGraphics graphicsMock = Mockito.spy(TextGraphics.class);
        hero = arena.getHero();
        hero.setLives(3);
        arena.draw(graphicsMock);
        Mockito.verify(graphicsMock).putString(new TerminalPosition(71, 0),"LIFES:eee");
    }

    @Test
    void drawTest2Lives(){
        TextGraphics graphicsMock = Mockito.spy(TextGraphics.class);
        hero = arena.getHero();
        hero.setLives(2);
        arena.draw(graphicsMock);
        Mockito.verify(graphicsMock).putString(new TerminalPosition(71, 0),"LIFES:ee");
    }

    @Test
    void drawTest1Lives(){
        TextGraphics graphicsMock = Mockito.spy(TextGraphics.class);
        hero = arena.getHero();
        hero.setLives(1);
        arena.draw(graphicsMock);
        Mockito.verify(graphicsMock).putString(new TerminalPosition(71, 0),"LIFES:e");
    }
    @Test
    void drawTest0Lives(){
        TextGraphics graphicsMock = Mockito.spy(TextGraphics.class);
        hero = arena.getHero();
        hero.setLives(0);
        arena.draw(graphicsMock);
        Mockito.verify(graphicsMock).putString(new TerminalPosition(71, 0),"LIFES:");
    }

    // DRAW TESTS END HERE -----------------

}