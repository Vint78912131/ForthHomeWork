/**
 * Lesson 4. HomeWork
 *
 * @author SotnikovVS
 * @version 13.11.2021
 *
 */

import java.util.Random;
import java.util.Scanner;

public class ForthHomeWork {
    public static int SIZE = 5;
    public static int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();
    public static Random chance = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            if (chance.nextInt(100) <= 25) //chance of human move 25%
                humanTurn();
            else aiTurn();
            printMap();
            if (isMapFull()) {
                System.out.println("Draw!");
                break;
            }
            if (checkWin(DOT_O)) {
                System.out.println("You're lose, leather bastard!\nPrepare to die!");
                break;
            }
            if (checkWin(DOT_X)) {
                System.out.println("You're win, leather bastard!\nIt isn't the end!");
                break;
            }
        }
        System.out.println("Terminated!");
    }

    public static boolean checkWin(char symb) {
        int countX = 0;
        int countY = 0;
        int countD = 0;
        boolean gapX = false;
        boolean gapY = false;
        boolean gapD = false;

        //check vertical and horizontal lines
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] != symb)
                    gapY = true;
                if (map[i][j] == symb && (!gapY))
                    countY++;
                if (map[j][i] != symb)
                    gapX = true;
                if (map[j][i] == symb && (!gapX))
                    countX++;
                if (countX >= DOTS_TO_WIN || countY >= DOTS_TO_WIN) return true;
            }
            countX = 0;
            countY = 0;
            gapX = false;
            gapY = false;
        }

        //check all diagonal lines
        // ->/
        for (int i = SIZE; i > 0; i--) {
            for (int j = 0; j < SIZE - i + 1; j++) {
                if (map[SIZE - i - j][j] != symb)
                    gapD = true;
                if (map[SIZE - i - j][j] == symb && (!gapD))
                    countD++;
            }
            if (countD != 0) System.out.println(countD);
            if (countD >= DOTS_TO_WIN) return true;

            countD = 0;
            gapD = false;
        }
        // /<-
        for (int i = 1; i < SIZE; i++) {
            for (int j = SIZE - 1; j >= i; j--) {
                if (map[SIZE - 1 + i - j][j] != symb)
                    gapD = true;
                if (map[SIZE - 1 + i - j][j] == symb && (!gapD))
                    countD++;
            }
            if (countD != 0) System.out.println(countD);
            if (countD >= DOTS_TO_WIN) return true;

            countD = 0;
            gapD = false;
        }
        // ->\
        for (int i = 0; i < SIZE; i++) {
            for (int j = i; j < SIZE; j++) {
                if (map[j - i][j] != symb)
                    gapD = true;
                if (map[j - i][j] == symb && (!gapD))
                    countD++;
            }
            if (countD != 0) System.out.println(countD);
            if (countD >= DOTS_TO_WIN) return true;

            countD = 0;
            gapD = false;
        }
        // \<-
        for (int i = 1; i < SIZE; i++) {
            for (int j = i; j < SIZE; j++) {
                if (map[j][j - i] != symb)
                    gapD = true;
                if (map[j][j - i] == symb && (!gapD))
                    countD++;
            }
            if (countD != 0) System.out.println(countD);
            if (countD >= DOTS_TO_WIN) return true;

            countD = 0;
            gapD = false;
        }
        return false;
    }
    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("I'll kill you, leather bustard! " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }
    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Choose your destiny: X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    public static void printMap() {
        System.out.print("   ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.print("X\n");
        System.out.print("  ");
        for (int i = 0; i <= SIZE; i++) {
            System.out.print("**");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i+1) + "| ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.print("|\n");
        }
        System.out.print("Y ");
        for (int i = 0; i <= SIZE; i++) {
            System.out.print("**");
        }
        System.out.println();
    }
}