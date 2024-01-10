package com.bmcl.hero;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameTest {
    TerminalScreen screenMock;
    Game gameMock;
    Font font;
    String path;
    int size;
    @BeforeEach
    void before(){
        screenMock = Mockito.mock(TerminalScreen.class);
        gameMock = Mockito.mock(Game.class);
    }

    @Test
    void fontTest() throws IOException {
        gameMock.changeFont(path, size);
        Mockito.when(gameMock.getFont());
    }
    @Test
    void getFontTest() throws IOException {
        gameMock.getFont();
        Mockito.when(gameMock.getFont()).thenReturn(font);
    }
}
