package sda.training.learning;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.training.HibernateFactory;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PriceCalculatorTest {
//    private PostalCodePriceCalculator postalCodePriceCalculator = Mockito.mock(PostalCodePriceCalculator.class);
//    private PriceCalculator priceCalculator = new PriceCalculator(postalCodePriceCalculator);

    @Mock
    private PostalCodePriceCalculator postalCodePriceCalculator;

    @Mock
    private MyScanner myScanner;

    @InjectMocks
    private PriceCalculator priceCalculator;

    @Test
    public void letter7x7ShouldBeChargedAsSmallLetter() {
        //given
        int x = 7;
        int y = 7;

        //when
        Mockito.when(postalCodePriceCalculator.calculate("11-400")).thenReturn(100);
        Mockito.when(myScanner.nextInt()).thenReturn(2);
        int result = priceCalculator.calculateLetterPrice(x, y, "11-400");

        //then
        assertEquals(115, result);
    }


    @Test
    public void incorrectLetterSizeShouldThrowException() {
        //given
        int x = -1;
        int y = 7;

        //when
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            priceCalculator.calculateLetterPrice(x, y, "DUMMY");
        });
    }
}