package sda.training.angry_nerds_game.game;


import java.util.InputMismatchException;
import java.util.Scanner;

public class GameMenu {

    private final static String MENU = "*-------------------------------------------------*\n"
            + "*---------------Angry Nerd's v.1.0----------------*\n"
            + "*-------------------------------------------------*\n"
            + "*------------------- GAME MENU -------------------*\n"
            + "*-------------------------------------------------*\n"
            + "1. Enter Name \n"
            + "2. Previous Menu\n"
            + "*-------------------------------------------------*\n"
            + "Wybierz 1-2 : ";


    public int selectMenu() {

        System.out.print(MENU);

        int opcja = 0;
        try {
            opcja = enterNumber();
        } catch (InputMismatchException e) {
            System.out.println("Wprowadź poprawną liczbę.");
            return 0;
        }
        return opcja;
    }

    public void clearMenu() {
        for (int i = 0; i < 40; i++) {
            System.out.println();
            //drukuje 20 pustych lini zeby to jakoś wyglądało
        }
    }

    public void runApp() {

        int wybor = 0;

        do {
            wybor = selectMenu();
            switch (wybor) {
                case 1:
                    clearMenu();
                    System.out.println("Wybrałeś Enter Name Player :");
                    SinglePlayerGame singlePlayerGame = new SinglePlayerGame();
                    singlePlayerGame.startGame();
                    break;
                case 2:
                    clearMenu();
                    System.out.println("Wybrałeś Back Menu :");
                    break;
                default:
                    clearMenu();
                    System.out.println("Wybierz poprawnie 1-2");
            }
        } while (wybor != 2);

    }

    private int enterNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}
