package sda.training.sos.menu;

import java.util.Arrays;

public enum MenuOption {
    NEW_GAME(1),
    LOAD_GAME(2),
    UNKNOWN_OPTION(Integer.MIN_VALUE);

    private final int index;

    MenuOption(int i) {
        this.index = i;
    }

    public int getIndex() {
        return index;
    }

    public static MenuOption getOptionByIndex(int index){
        return Arrays.stream(MenuOption.values())
                .filter(a -> a.getIndex() == index)
                .findFirst()
                .orElse(UNKNOWN_OPTION);
    }

}
