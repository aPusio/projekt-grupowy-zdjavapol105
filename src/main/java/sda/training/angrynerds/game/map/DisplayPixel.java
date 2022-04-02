package sda.training.angrynerds.game.map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import sda.training.utils.TextColor;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class DisplayPixel {

    private String aChar;
    private TextColor color;

    public static DisplayPixel of(String aChar) {
        return new DisplayPixel(aChar, null);
    }

    public static DisplayPixel of(String aChar, TextColor textColor) {
        return new DisplayPixel(aChar, textColor);
    }
}
