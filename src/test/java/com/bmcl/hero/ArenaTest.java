package com.bmcl.hero;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArenaTest {
    Hero hero = new Hero(34,19);
    Luigi luigi = new Luigi(35,24);

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



}