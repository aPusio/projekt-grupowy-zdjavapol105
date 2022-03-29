package sda.training.learning;

import java.util.Objects;

public class PostalCodePriceCalculator {
    private static final String KETRZYN = "11-400";
    private static final String GDANSK = "80-298";

    public int calculate(String postalCode){
        if(KETRZYN.equals(postalCode)){
            return 22;
        }
//        else if (Objects.equals(postalCode, GDANSK)){
        else if (GDANSK.equals(postalCode)){
            return 33;
        }
        return 50;
    }
}
