package com.bmcl.hero;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            new Game(80, 21).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}