package ru.laimcraft.vanilla;

import java.util.Date;

public class Random {
    public static int getRandom(int end) {
        Date date = new Date();
        return (int) (1 + (date.getTime() % end));
    }

    public static double getRandomDrob(int end) {
        Date date = new Date();
        double value = date.getTime() % (end+1);
        return value;
    }

    public static int getRandomJava(int end) {
        java.util.Random random = new java.util.Random();
        return random.nextInt(end) + 1;
    }

    public static double getRandomJavaZero(int end) { //Drob
        end = end + 1;
        java.util.Random random = new java.util.Random();
        return random.nextInt(end);
    }
}
