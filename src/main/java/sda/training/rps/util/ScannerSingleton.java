package sda.training.rps.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerSingleton {
    private static ScannerSingleton instance;
    private final Scanner scanner = new Scanner(System.in);

    private ScannerSingleton() {
    }

    public static ScannerSingleton getInstance() {
        if (instance == null) {
            instance = new ScannerSingleton();
        }
        return instance;
    }

    public int scannerInt() {
        int scannedInt;
        try {
            scannedInt = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Podaj cyfrę!");
            scannedInt = scannerInt();
        }

        return scannedInt;
    }

    public String scannerLine() {
        return scanner.nextLine();
    }

}
