package ru.gb.Homework_4;

import java.util.Random;
import java.util.Scanner;

public class Homework4 {
    // Домашняя работа № 4

    /**
     * Игра кркстики-нолики
     */

    // Создание игрового поля
    public static final int SIZE = 3;
    public static final int DOTS_TO_WIN = 3;

    // Ячейка поля
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;

    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    // Основной игровой цикл
    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил Искусственный Интелект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    // Проверка победы

//        public static boolean checkWin(char symb) {
//            if (map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
//            if (map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
//            if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
//            if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
//            if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
//            if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
//            if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
//            if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
//            return false;
    /*
    2. Переделать проверку победы, чтобы она не была реализована просто набором условий, например,
       с использованием циклов.
     */

private static boolean checkWin(char symb) {

    //Ряды и столбцы
    int row;
    int col;
    // Диагоналям
    int leftDi = 0;
    int rightDi = 0;

    for (int i = 0; i < SIZE; i++) {
       row = 0;
       col = 0;

        leftDi += map[i][i];
        rightDi += map[i][2 - i];
        // Проверка по диагонали
        if (leftDi == symb * SIZE) {return true; }
        if (rightDi == symb * SIZE) { return true;}

        for (int j = 0; j < SIZE; j++) {
            if (map[i][j] == 0) { return true; }

            row += map[i][j];
            col += map[j][i];
        }
        // Проверка по рядам и сталбцам
        if  (row == symb * SIZE) { return true; }
        if  (col == symb * SIZE) { return true; }
    }
    return false;
}


        // Проверка поля
        public static boolean isMapFull() {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (map[i][j] == DOT_EMPTY) return false;
                }
            }
            return true;
        }

        // Ход компьютера
        public static void aiTurn() {
            int x;
            int y;

            do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
            } while (!isCellValid(x, y));
            System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
            map[y][x] = DOT_O;
        }

        // Ход человека
        public static void humanTurn() {
            int x, y;
            do {
                System.out.println("Введите координаты в форме X Y");
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
            } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
            map[y][x] = DOT_X;
        }

        // Проверка проверяет возможность установки фишки в указанную ячейку.
        public static boolean isCellValid(int x, int y) {
            if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
            if (map[y][x] == DOT_EMPTY) return true;
            return false;
        }

        // Иницилизация поля
        public static void initMap() {
            map = new char[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    map[i][j] = DOT_EMPTY;
                }
            }
        }

        // Вывод поля в консоль
        public static void printMap() {
            for (int i = 0; i <= SIZE; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i = 0; i < SIZE; i++) {
                System.out.print((i + 1) + " ");
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
