package com.bmcl.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LuigiTest {
    Luigi luigi = new Luigi(1,1);
    @Test
    public void moveUpTest(){
        Position expected = new Position(1,0);
        luigi.setPosition(luigi.moveUp());
        assertEquals(luigi.getPosition(), expected);
    }

    @Test
    public void moveDownTest(){
        Position expected = new Position(1,2);
        luigi.setPosition(luigi.moveDown());
        assertEquals(luigi.getPosition(), expected);
    }
    @Test
    public void moveLeftTest(){
        Position expected = new Position(0,1);
        luigi.setPosition(luigi.moveLeft());
        assertEquals(luigi.getPosition(), expected);
    }
    @Test
    public void moveRightTest(){
        Position expected = new Position(2,1);
        luigi.setPosition(luigi.moveRight());
        assertEquals(luigi.getPosition(), expected);
    }

    @Test
    public void jumpTest(){
        luigi.setJumpState(true);
        assertEquals(luigi.isJumpState(), true);
    }


}