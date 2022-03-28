package sda.training;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("WYBIERZ GRE: ");
            System.out.println("1. Kamień-Papier-Nożyczki");
            System.out.println("2. Tu wstaw gre 🚀🚀🚀");
            System.out.println("3. Tu wstaw gre 🚀🚀🚀");
            System.out.println("4. Tu wstaw gre 🚀🚀🚀");
            System.out.println("5. Wyjdź z aplikacji");

            int gameNumber = scanner.nextInt();
            switch (gameNumber) {
                case 1:
                    sda.training.rps.app.App app = new sda.training.rps.app.App();
                    app.startRPS();
                    break;
                case 2:
                    System.out.println("Zaimplementuj mnie 🤣");
                    break;
                case 3:
                    System.out.println("Zaimplementuj mnie 🤣");
                    break;
                case 4:
                    System.out.println("Zaimplementuj mnie 🤣");
                    break;
                case 5:
                    System.out.println("Koniec gry!");
                    return;
                default:
                    System.out.println("Bardzo sie starałeś, lecz gry nie wybrałeś");
            }
        }
    }
}
