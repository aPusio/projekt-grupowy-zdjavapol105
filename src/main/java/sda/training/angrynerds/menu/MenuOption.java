package sda.training.angrynerds.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sda.training.angrynerds.AngryNerdsMessages;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum MenuOption {
    NEW_GAME(AngryNerdsMessages.newGame, 1);

    private String text;
    private Integer number;

    public static Optional<MenuOption> getByNumber(Integer number){
        return Arrays.stream(MenuOption.values())
                .filter(opt -> Objects.equals(opt.getNumber(), number))
                .findFirst();
    }

    public String getNumberAndText(){
        return number + ". " + text;
    }
}
