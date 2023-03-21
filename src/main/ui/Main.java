package ui;


import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new GameListConsole();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
