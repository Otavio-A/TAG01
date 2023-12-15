package com.bmcl.hero;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PositionTest {

    @Test
    public void testConstructoresEGetts(){
        Position position = new Position(3,4);
        assertEquals(3,position.getX());
        assertEquals(4,position.getY());
    }

    @Test
    public void testSetters(){
        Position position = new Position(1,2);
        position.setX(5);
        position.setY(6);

        assertEquals(5, position.getX());
        assertEquals(6, position.getY());

    }

    @Test
    public void testEquals(){

        //Instancia dois objetos position nas coordenadas 3 e 4
        Position position1 = new Position(3, 4);
        Position position2 = new Position(3, 4);

        //Istancia um objeto position com coordenadas diferentes 5 e 6
        Position position3 = new Position(5, 6);

        //Verifica se o metodo equals retorna true se forem de posições iguais
        assertEquals(position1,position2);

        //Verifica se o metodo equals retorna false se forem de posições diferentes
        assertNotEquals(position1,position3);

    }




}
