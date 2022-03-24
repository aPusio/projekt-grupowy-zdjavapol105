package sda.training.sos.menu;

import java.util.Scanner;

public class SosMenu {

    public void printMenu(){
        System.out.println("MENU GRY");
        System.out.println("1. NOWA GRA");
        System.out.println("1. WCZYTAJ GRE");
    }

    public MenuOption readUserOption(){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        return MenuOption.getOptionByIndex(number);
    }


}
