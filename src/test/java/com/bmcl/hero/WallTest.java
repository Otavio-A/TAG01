package com.bmcl.hero;

import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WallTest {
    Wall wall;
    Position position;

    Arena mockArena= Mockito.mock(Arena.class);
    Game mockGame = Mockito.mock(Game.class);
    private List<Wall> walls;
    Arena arena = new Arena(2,2,mockGame);

    @BeforeEach
    void before(){

    }
    @Test
    void positionTest(){
        position = new Position(1,1);
        wall = new Wall(20,20);
        wall.setPosition(position);
        assertEquals(wall.getPosition(), position);
    }

    @Test
    void powBlock(){
        walls = arena.getWalls();
        wall = new Wall(1,1);
        walls.add(wall);
        arena.powBlock();
        assertEquals(wall.isUsed(), true);
    }
}
