package com.bmcl.hero;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ArenaTest {
    Hero hero = new Hero(34,19);
    Game game;

    {
        try {
            game = new Game(80,21);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Arena arena = new Arena(80,21,game);

    @Test
    public void moveupTest() throws IOException {
        Position expected = new Position(34,18);
        hero.setPosition(hero.moveUp());
        System.out.println(hero.position.getY());
        assertEquals(hero.position.getY(), expected.getY());
    }
}