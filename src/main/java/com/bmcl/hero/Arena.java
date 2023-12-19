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
    private final List<Monster> monsters;

    public Arena(int width, int height, Game gameInstance) {
        this.width = width;
        this.height = height;

        hero = new Hero(width / 2, height - 2);
        this.walls = createWalls();

        this.monsters = createMonsters();

        this.gameInstance = gameInstance; //PARA O JUMP()
    }

    private List<Monster> createMonsters() {
       ArrayList<Monster> monsters = new ArrayList<>();
        Timer timer = new Timer();
        TimerTask myTask = new TimerTask() {

            public void run() {
                if (monsters.size() != 5)
                    if (monsters.size() % 2 == 0)
                        monsters.add(new Monster(4, 2 ,true));
                    else
                        monsters.add(new Monster(77, 2 ,false));
            }
        };

        timer.schedule(myTask, 1000, 4000);

        return monsters;
    }

    private List<Wall> createWalls() {

        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height ));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width , r));
        }

        return walls;
    }


    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls) wall.draw(graphics);
        for (Monster monster : monsters) monster.draw(graphics);
    }

    public void processKey(KeyStroke key) {

        if (key.getKeyType() == KeyType.ArrowUp && isplat(hero.getPosition())) jump();
        if (key.getKeyType() == KeyType.ArrowRight) moveHero(hero.moveRight());
        if (key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.moveLeft());
       // if (key.getKeyType() == KeyType.ArrowDown));

        verifyMonsterCollisions();
    }


    public void verifyMonsterCollisions() {
        monsters.removeIf(monster -> hero.getPosition().equals(monster.getPosition()) && monster.isHit());
        for (Monster monster : monsters)
            if (hero.getPosition().equals(monster.getPosition()) && !monster.isHit()) {
                System.out.println("You died!");
                System.exit(0);
            }
    }

    public void moveMonsters() {
        for (Monster monster : monsters) {
            Position monsterPosition = monster.move();
            if (canMonsterMove(monsterPosition,monster)) {
                monster.setPosition(monsterPosition);
            }
        }
    }

    private boolean canMonsterMove(Position position,Monster monster) {
        if (!canBichoMove(position))return false;

        if (position.getX() == 78 && position.getY() == 19 ) //baixo direita
        {
            monster.setPosition(new Position(4, 2));
            return  false;
        }

        if (position.getX() == 2 && position.getY() == 19 )//baixo esquerda
        {
            monster.setPosition(new Position(77, 2));
            return  false;
        }



        if (position.getX() > 79)
        {
            monster.setPosition(new Position(0, position.getY()));
            return  false;

        }

        if (position.getX() < 1 )
        {
            monster.setPosition(new Position(80, position.getY()));
            return  false;
        }

        return true;
    }

    public boolean isplat(Position position) {
        //Primeira plat
        if (position.getY() == 4 && position.getX() < 35) return true;
        if (position.getY() == 4 && position.getX() >= 45) return true;

        //Segunda plat
        if (position.getY() == 10 && position.getX() < 7) return true;
        if (position.getY() == 9 && position.getX() > 23 && position.getX() < 59) return true;
        if (position.getY() == 10 && position.getX() >= 73) return true;

        //terceira plat
        if (position.getY() == 14 && position.getX() < 30) return true;
        if (position.getY() == 14 && position.getX() >= 50) return true;

        //POW
        if (position.getY() == 14 && position.getX() > 38 && position.getX() < 41) return true;

        //CHAO
        if (position.getY() == 19 ) return true;

        return false;
    }


    private boolean hitPlat(Position position) {    //hero se pular em baixo da plataforma vai ativar hitplat para matar o monstro
        //Primeira plat
        if (position.getY() == 6 && position.getX() < 35 && hero.isJumpState()) {
            //TODO metodo para matar o bicho
            applyHit();
            return true;
        }
        if (position.getY() == 6 && position.getX() >= 45 && hero.isJumpState()) return true;

        //Segunda plat
        if (position.getY() == 12 && position.getX() < 7 && hero.isJumpState()) return true;
        if (position.get Y() == 11 && position.getX() > 23 && position.getX() < 59 && hero.isJumpState()) return true;
        if (position.getY() == 12 && position.getX() >= 73 && hero.isJumpState()) return true;

        //terceira plat
        if (position.getY() == 16 && position.getX() < 30 && hero.isJumpState()) return true;
        if (position.getY() == 16 && position.getX() >= 50 && hero.isJumpState()) return true;

        //POW
        if (position.getY() == 16 && position.getX() > 38 && position.getX() < 41 && hero.isJumpState()) return true;

        return false;
    }

    private void applyHit(){
        for (Monster monster1 : monsters)
            if (monster1.position.getY() == hero.position.getY()-2 && monster1.position.getX() == hero.position.getX())
            {
                monster1.setHit(true);
            }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
        }
    }

    public void moveMonster(Position position,Monster monster) {
        if (canMonsterMove(position, monster)) {
            monster.setPosition(position);
        }
    }


    public boolean canBichoMove(Position position) {
        //Primeira plat
        if (position.getY() == 5 && position.getX() < 35) return false;
        if (position.getY() == 5 && position.getX() >= 45) return false;

        //Segunda plat
        if (position.getY() == 11 && position.getX() < 7) return false;
        if (position.getY() == 10 && position.getX() > 23 && position.getX() < 59) return false;
        if (position.getY() == 11 && position.getX() >= 73) return false;

        //terceira plat
        if (position.getY() == 15 && position.getX() < 30) return false;
        if (position.getY() == 15 && position.getX() >= 50) return false;

        //POW
        if (position.getY() == 15 && position.getX() > 38 && position.getX() < 41) return false;

        if (position.getY() == 20) return false;

        return true;
    }



    private boolean canHeroMove(Position position) {
        if (!canBichoMove(position))return false;

        if (position.getX() > 80)
        {
            hero.setPosition(new Position(0, position.getY()));
            return false;
        }

        if (position.getX() < 0 )
        {
            hero.setPosition(new Position(80, position.getY()));
            return false;
        }


        if(!isplat(position) && hero.isJumpState())
        {
            //System.out.println("jumping");
        }

        return true;
    }
    /*
    JUMP VERSÃO 2 | usando thread.sleep
     */
    private final Game gameInstance;

    public void jump() {

        try {
            hero.setJumpState(true);
            for (int i = 0; i < 6; i++) {
                moveHero(hero.moveUp());
                Thread.sleep(30);
                gameInstance.draw();
                hitPlat(hero.getPosition());
            }
        hero.setJumpState(false);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("Error jump()");
        }

    }

    public void Monsterfall(){
        for (Monster monster : monsters) {
            moveMonster(monster.moveDown(),monster);
        }
    }

    public void Herofall(){
        moveHero(hero.moveDown());
    }

    public boolean isHeroJumping() {
        return hero.isJumpState();
    }
}
