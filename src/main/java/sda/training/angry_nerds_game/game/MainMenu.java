package sda.training.angry_nerds_game.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    private final static String MENU = "*-------------------------------------------------*\n"
            + "*---------------Angry Nerd's v.1.0----------------*\n"
            + "*-------------------------------------------------*\n"
            + "*--------------------- MENU ----------------------*\n"
            + "*-------------------------------------------------*\n"
            + "1. New Game\n"
            + "2. Load Game\n"
            + "3. Game Settings\n"
            + "4. Quit Game\n"
            + "*-------------------------------------------------*\n"
            + "Wybierz 1-4 : ";


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
                    System.out.println("Wybrałeś New Game :");
                    NewGameMenu newGameMenu = new NewGameMenu();
                    clearMenu();
                    newGameMenu.runApp();
                    break;
                case 2:
                    clearMenu();
                    System.out.println("Wybrałeś Load Game :");

                    break;
                case 3:
                    clearMenu();
                    System.out.println("Wybrałeś Game Settings :");
                    break;
                case 4:
                    clearMenu();
                    System.out.println("Wybrałeś Quit Game ... Do zobaczenia ... ");

                    break;
                default:
                    clearMenu();
                    System.out.println("Wybierz poprawnie 1-4");
            }
        } while (wybor != 4);

    }

    private int EnterNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}
