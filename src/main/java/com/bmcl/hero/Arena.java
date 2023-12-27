package com.bmcl.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Arena {
    private final Hero hero;


    private final Hero luigi;

    private int width;
    private int height;

<<<<<<< HEAD
    private int pontos = 0;
=======
    private int score = 420;

    public int getScore() {
        return score;
    }

>>>>>>> 2c8479b1b811bc73760cfe662726f3c43076b2c5
    private List<Wall> walls;
    private final List<Monster> monsters;

    private Position respawn = new Position(39,14);


    public Arena(int width, int height, Game gameInstance) {
        this.width = width;
        this.height = height;

        hero = new Hero(width / 2, height - 2);
        luigi = new Hero(width / 2, height - 2);
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
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));//fundo preto
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));//letra vermelha
        graphics.putString(new TerminalPosition(0, 0), "SCORE:"+pontos);
        graphics.putString(new TerminalPosition(71, 0),"LIFES:eee");
        if(hero.getDirecao()){
            hero.marioDireita(graphics);
        }else{
            hero.marioEsquerda(graphics);
        }

        if(luigi.getDirecao()){
            luigi.luigiDireita(graphics);
        }else{
            luigi.luigiEsquerda(graphics);
        }

        for (Wall wall : walls) {
            wall.draw(graphics);
        }

        for (Monster monster : monsters){
            if(monster.isHit()){
                monster.drawhit(graphics);
            }
            else
            {
                monster.draw(graphics);
            }
        }
    }

    public void processKey(KeyStroke key) {

        //verificar se key está null
        if (key == null) {
            return;
        }

        if (key.getKeyType() == KeyType.ArrowUp && isplat(hero.getPosition())) jump(hero);
        if (key.getKeyType() == KeyType.ArrowRight){
            hero.setDirecao(true);
            moveHero(hero.moveRight(), hero);

        }
        if (key.getKeyType() == KeyType.ArrowLeft){
            hero.setDirecao(false);
            moveHero(hero.moveLeft(), hero);

        }


        Character keyChar = key.getCharacter();
        if (key.getKeyType() == KeyType.Character && keyChar != null) {
            if (keyChar == 'a') {
                luigi.setDirecao(false);
                moveHero(luigi.moveLeft(), luigi);
            }
            if (keyChar == 'd') {
                luigi.setDirecao(true);
                moveHero(luigi.moveRight(), luigi);
            }
            if (keyChar == 'w' && isplat(luigi.getPosition())){
                jump(luigi);
            }
        }

       // if (key.getKeyType() == KeyType.ArrowDown));
        verifyMonsterCollisions();
    }


    public void verifyMonsterCollisions() {
        monsters.removeIf(monster -> hero.getPosition().equals(monster.getPosition()) && monster.isHit()) ;
        monsters.removeIf(monster -> luigi.getPosition().equals(monster.getPosition()) && monster.isHit());

        //for (Monster monster : monsters){
            //if(hero.getPosition().equals(monster.getPosition()) && monster.isHit()
            //monster.drawPontos();

        //}



        for (Monster monster : monsters)
            if (hero.getPosition().equals(monster.getPosition()) && !monster.isHit()) {
                System.out.println("You died!");
                hero.setLives(hero.getLives() -1);

                hero.setPosition(respawn);
                if (hero.getLives() == 0) {
                    System.exit(0);
                }
            }else if(luigi.getPosition().equals(monster.getPosition()) && !monster.isHit()){
                System.out.println("You died!");
                hero.setLives(hero.getLives() -1);
               luigi.setPosition(respawn);
                if (hero.getLives() == 0) {
                    System.exit(0);
                }
            }
    }

    public void moveMonsters() {
        for (Monster monster : monsters) {
            Position monsterPosition = monster.move();
            if(!monster.isHit()){
            if (canMonsterMove(monsterPosition,monster)) {
                monster.setPosition(monsterPosition);
            }}
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
        if (position.getY() == 14 && position.getX() == 39) return true;

        //CHAO
        if (position.getY() == 19 ) return true;

        return false;
    }


    private void hitPlat(Position position, Hero personagem) {    //hero se pular em baixo da plataforma vai ativar hitplat para matar o monstro
        //Primeira plat
        if (position.getY() == 6 && position.getX() < 35 && personagem.isJumpState()) applyHit(personagem);
        if (position.getY() == 6 && position.getX() >= 45 && personagem.isJumpState()) applyHit(personagem);

        //Segunda plat
        if (position.getY() == 12 && position.getX() < 7 && personagem.isJumpState()) applyHit(personagem);
        if (position.getY() == 11 && position.getX() > 23 && position.getX() < 59 && personagem.isJumpState()) applyHit(personagem);
        if (position.getY() == 12 && position.getX() >= 73 && personagem.isJumpState()) applyHit(personagem);

        //terceira plat
        if (position.getY() == 16 && position.getX() < 30 && personagem.isJumpState()) applyHit(personagem);
        if (position.getY() == 16 && position.getX() >= 50 && personagem.isJumpState()) applyHit(personagem);

        //POW
        if (position.getY() == 16 && position.getX() == 39 && personagem.isJumpState())
        {powBlock();}
    }

    private void applyHit(Hero personagem){
        for (Monster monster1 : monsters)
            if (monster1.position.getY() == personagem.position.getY()-2 && monster1.position.getX() == personagem.position.getX())
            {
                monster1.setHit(true);
            }
    }
    private void powBlock(){
        for (Monster monster1 : monsters)
            if (isplat(monster1.getPosition()))
                monster1.setHit(true);

    }

    public void moveHero(Position position, Hero personagem) {
        if (canHeroMove(position, personagem)) {
            personagem.setPosition(position);
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
        if (position.getY() == 15 && position.getX() == 39) return false;

        if (position.getY() == 20) return false;

        return true;
    }



    private boolean canHeroMove(Position position, Hero personagem) {
        if (!canBichoMove(position))return false;

        if (position.getX() > 80)
        {
            personagem.setPosition(new Position(0, position.getY()));
            return false;
        }

        if (position.getX() < 0 )
        {
            personagem.setPosition(new Position(80, position.getY()));
            return false;
        }

        return true;
    }
    /*
    JUMP VERSÃO 2 | usando thread.sleep
     */
    private final Game gameInstance;

    public void jump(Hero personagem) {

        try {
            personagem.setJumpState(true);
            for (int i = 0; i < 6; i++) {
                moveHero(personagem.moveUp(), personagem);
                Thread.sleep(30);
                gameInstance.draw();
                hitPlat(personagem.getPosition(), personagem);
            }
        personagem.setJumpState(false);
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
        moveHero(hero.moveDown(), hero);
    }
    public void Luigifall(){
        moveHero(luigi.moveDown(), luigi);
    }
    public boolean isHeroJumping() {
        return hero.isJumpState();
    }
    public boolean isLuigiJumping() {
        return luigi.isJumpState();
    }
}
