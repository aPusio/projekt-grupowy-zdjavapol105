package sda.training.angry_nerds_game.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NewGameMenu {

    private final static String MENU = "*-------------------------------------------------*\n"
            + "*---------------Angry Nerd's v.1.0----------------*\n"
            + "*-------------------------------------------------*\n"
            + "*-----------------NEW GAME MENU ------------------*\n"
            + "*-------------------------------------------------*\n"
            + "1. Single Player\n"
            + "2. Multi Player\n"
            + "3. Previous Menu\n"
            + "*-------------------------------------------------*\n"
            + "Wybierz 1-3 : ";


    public int selectMenu() {

        System.out.print(MENU);

        int opcja = 0;
        try {
            opcja = EnterNumber();
        } catch (InputMismatchException e) {
            System.out.println("Wprowadź poprawną liczbę.");
            return 0;
        }
        return opcja;
    }

    public void clearMenu() {
        for (int i = 0; i < 20; i++) {
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
                    System.out.println("Wybrałeś Single Player :");
                    GameMenu gameMenu = new GameMenu();
                    gameMenu.runApp();
                    break;
                case 2:
                    clearMenu();
                    System.out.println("Wybrałeś Multi Player :");
                    break;
                case 3:
                    clearMenu();
                    System.out.println("Wybrałeś Back Menu :");
                    break;
                default:
                    clearMenu();
                    System.out.println("Wybierz poprawnie 1-3");
            }
        } while (wybor != 3);

    }

    private int EnterNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


}
