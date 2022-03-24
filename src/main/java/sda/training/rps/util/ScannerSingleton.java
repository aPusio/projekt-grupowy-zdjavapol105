package sda.training.rps.util;

import java.util.Scanner;

public class ScannerSingleton {
    private static ScannerSingleton instance;
    private Scanner scanner = new Scanner(System.in);
    private ScannerSingleton() {
    }
    public static ScannerSingleton getInstance(){
        if (instance == null){
            instance = new ScannerSingleton();
        }
        return instance;
    }

    public int scannerInt(){
        int scannedInt = scanner.nextInt();
        scanner.nextLine();
        return scannedInt;
    }

    public String scannerLine(){
        return scanner.nextLine();
    }

}
