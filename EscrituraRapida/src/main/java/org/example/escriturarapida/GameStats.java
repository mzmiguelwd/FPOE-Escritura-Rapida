package org.example.escriturarapida;

public class GameStats {
    private static int currentLevel;

    public int getCurrentLevel() {
        return  currentLevel;
    }

    public static void setCurrentLevel(int newCurrentLevel) {
        currentLevel = newCurrentLevel;
    }
}
