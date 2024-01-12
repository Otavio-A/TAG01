package com.bmcl.hero;

import com.bmcl.hero.Monster;
import com.bmcl.hero.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    @Test
    void monsterDrawTest(){
        TextGraphics graphicsMock = Mockito.spy(TextGraphics.class);
        Position position = new Position(1,1);
        monster.draw(graphicsMock);
        Mockito.verify(graphicsMock).putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }
    @Test
    void monsterDrawHitTest(){
        TextGraphics graphicsMock = Mockito.spy(TextGraphics.class);
        Position position = new Position(1,1);
        monster.drawhit(graphicsMock);
        Mockito.verify(graphicsMock).putString(new TerminalPosition(position.getX(), position.getY()), "W");
    }
}
