package com.bmcl.hero.Menu;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class Menu {

    private final TextGraphics graphics;
    private int selectedOptionIndex = 0;


    public Menu(TextGraphics graphics) {
        this.graphics = graphics;
    }

    public void draw() {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), graphics.getSize(), ' ');

        //Desenha o mario
        graphics.setForegroundColor(TextColor.ANSI.RED);
        graphics.putString(new TerminalPosition(32, 1), "n");

        //Desenha o Luigi
        graphics.setForegroundColor(TextColor.ANSI.GREEN);
        graphics.putString(new TerminalPosition(52, 1), "m");

        //Desenha o titulo
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.putString(new TerminalPosition(34, 1), "MARIO BROS ARCADE");


        for (int i = 0; i < 3; i++) {
            // Define a cor do texto dependendo da opção que estiver selecionada
            graphics.setForegroundColor(i == selectedOptionIndex ? TextColor.ANSI.GREEN : TextColor.ANSI.RED);

            // Desenha o texto da opcao start, leaderboard, ou exit na posicao especifica
            graphics.putString(new TerminalPosition(38, 5 + i * 2), i == selectedOptionIndex ? "> " : "  ");

            // Utiliza um switch para determinar qual texto deve ser exibido com base no valor de i
            switch (i) {
                case 0:
                    graphics.putString(new TerminalPosition(40, 5 + i * 2), "START");
                    break;
                case 1:
                    graphics.putString(new TerminalPosition(40, 5 + i * 2), "LEADERBOARD");
                    break;
                case 2:
                    graphics.putString(new TerminalPosition(40, 5 + i * 2), "EXIT");
                    break;
                default:
                    break;
            }
        }
    }


    public void processKey(KeyStroke key) {

        //Verifica se não é null
        if (key != null) {
            //Verifica se o tipo de tecla e a arrowup
            if (key.getKeyType() == KeyType.ArrowUp) {
                //Se for a arrowup anda para a seleção em cima
                selectedOptionIndex = (selectedOptionIndex - 1 + 3) % 3;
            } else if (key.getKeyType() == KeyType.ArrowDown) {
                //Se for a arrowdown anda para a seleção em baixo
                selectedOptionIndex = (selectedOptionIndex + 1) % 3;
            }

        }
    }

    public boolean isStartSelected() {
        return selectedOptionIndex == 0;
    }

    public boolean isExitSelected() {
        return selectedOptionIndex == 2;
    }

    public boolean isLeaderboardSelected() {
        return selectedOptionIndex == 1;
    }
}
