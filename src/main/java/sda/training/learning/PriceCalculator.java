package sda.training.learning;

import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class PriceCalculator {
    private static final int SMALL_LETTER_SIZE = 10;
    private PostalCodePriceCalculator postalCodePriceCalculator;
    private MyScanner scanner;

    public int calculateLetterPrice(int x, int y, String postalCode){
        x = scanner.nextInt();

        if(x < 0 || y < 0) {
            throw new IllegalArgumentException("LETTER SIZE CANNOT BE LESS THAN 0");
        } else if(x <= SMALL_LETTER_SIZE && y <= SMALL_LETTER_SIZE){
            return 15 + postalCodePriceCalculator.calculate(postalCode);
        }
        else if (x > SMALL_LETTER_SIZE && y > SMALL_LETTER_SIZE) {
            return 30 + postalCodePriceCalculator.calculate(postalCode);
        }
        return postalCodePriceCalculator.calculate(postalCode);
    }
}
