package com.bmcl.monster;

import com.bmcl.hero.Monster;
import com.bmcl.hero.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonsterTest {
    Monster monster = new Monster(1,1,true);
    @Test
    public void moveDownTest(){
        Position expected = new Position(1,2);
        monster.setPosition(monster.moveDown());
        assertEquals(monster.getPosition(), expected);
    }
    @Test
    public void moveLeftTest(){
        Position expected = new Position(0,1);
        monster.setDirecao(false);
        monster.setPosition(monster.move());
        assertEquals(monster.getPosition(), expected);
    }
    @Test
    public void moveRightTest(){
        Position expected = new Position(2,1);
        monster.setDirecao(true);
        monster.setPosition(monster.move());
        assertEquals(monster.getPosition(), expected);
    }
}
