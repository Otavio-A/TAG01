package com.bmcl.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.*;

public class Arena {
    private final Hero hero;

    private int width;
    private int height;

    private List<Wall> walls;
    //private final List<Coin> coins;
    private final List<Monster> monsters;

    public Arena(int width, int height, Game gameInstance) {
        this.width = width;
        this.height = height;

        hero = new Hero(width / 2, height - 2);
        this.walls = createWalls();
        //this.coins = createCoins();
        this.monsters = createMonsters();

        this.gameInstance = gameInstance; //PARA O JUMP()
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return coins;
    }



    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        Timer timer = new Timer();
        TimerTask myTask = new TimerTask() {

            public void run() {
                int counter=monsters.size();
                if (counter != 5)
                    if(counter % 2 == 0)
                        monsters.add(new Monster(4, 2));
                    else
                        monsters.add(new Monster(77, 2));
            }
        };
        timer.schedule(myTask, 2000, 2000);


        return monsters;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        return walls;
    }

    public void draw(TextGraphics graphics) {


        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');


        hero.draw(graphics);

        for (Wall wall : walls) wall.draw(graphics);

        //for (Coin coin : coins) coin.draw(graphics);

        for (Monster monster : monsters) monster.draw(graphics);
    }

    public void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowUp) jump();
        if (key.getKeyType() == KeyType.ArrowRight) moveHero(hero.moveRight());
        if (key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.moveLeft());
        if (key.getKeyType() == KeyType.ArrowDown) moveHero(hero.moveDown());

        //retrieveCoins();

        verifyMonsterCollisions();

    }


    public void verifyMonsterCollisions() {
        for (Monster monster : monsters)
            if (hero.getPosition().equals(monster.getPosition())) {
                System.out.println("You died!");
                System.exit(0);
            }
    }

    public void moveMonsters() {
        for (Monster monster : monsters) {
            Position monsterPosition = monster.move();



            if (canHeroMove(monsterPosition))
                if(canMonsterMove(monsterPosition))
                    monster.setPosition(monsterPosition);
        }
    }

    private boolean canMonsterMove(Position position) {
        //Primeira plat
        if (position.getY() == 5 && position.getX()<35 ) return false;
        if (position.getY() == 5 && position.getX()>=45 ) return false;

        //Segunda plat
        if (position.getY() == 11 && position.getX()<7 ) return false;
        if (position.getY() == 10 && position.getX()>23 && position.getX()<59 ) return false;
        if (position.getY() == 11 && position.getX()>=73 ) return false;

        //terceira plat
        if (position.getY() == 15 && position.getX()<30 ) return false;
        if (position.getY() == 15 && position.getX()>=50 ) return false;

        //POW
        if (position.getY() == 15 && position.getX()>38 && position.getX()<41 ) return false;

        return true;
    }

    /*private void retrieveCoins() {
        for (Coin coin : coins)
            if (hero.getPosition().equals(coin.getPosition())) {
                coins.remove(coin);
                break;
            }
    }*/

    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
        }
    }




    private boolean canHeroMove(Position position) {
        //if (position.getX() <= -1) return false;
        //if (position.getX() > width - 1) return false;
        //if (position.getY() <= -1) return false;
        //if (position.getY() > height - 1) return false;

        //Primeira plat
        if (position.getY() == 5 && position.getX()<35 ) return false;
        if (position.getY() == 5 && position.getX()>=45 ) return false;

        //Segunda plat
        if (position.getY() == 11 && position.getX()<7 ) return false;
        if (position.getY() == 10 && position.getX()>23 && position.getX()<59 ) return false;
        if (position.getY() == 11 && position.getX()>=73 ) return false;

        //terceira plat
        if (position.getY() == 15 && position.getX()<30 ) return false;
        if (position.getY() == 15 && position.getX()>=50 ) return false;

        //POW
        if (position.getY() == 15 && position.getX()>38 && position.getX()<41 ) return false;


        for (Wall wall : walls)
            if (wall.getPosition().equals(position)) return false;

        return true;
    }


    /*
    JUMP VERSÃO 1 | Desenha todo o movimento do Hero, provavelmente deveria ser um observer pattern
     */
    class jumps extends TimerTask {
        public void run() {

            try {
                moveHero(hero.moveUp());
                Thread.sleep(50);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                gameInstance.draw();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private final Game gameInstance;
    public void jump() {
        Timer timer = new Timer();
        for(int i = 0; i < 4; i++) {
            timer.schedule(new jumps(), 1);
        }

        /*
        }
        try {
            for(int i = 0; i < 4; i++) {
                moveHero(hero.moveUp());
                gameInstance.draw();
            }
            for(int i = 0; i < 4; i++) {
                moveHero(hero.moveDown());
                gameInstance.draw();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error jump()");
        }*/

        }


        // And From your main() method or any other method


    }


