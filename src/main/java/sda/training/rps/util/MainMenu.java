package sda.training.rps.util;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MainMenu {
    RULES(1),
    NEW_GAME(2),
    LOAD_GAME(3),
    TOP10(4),
    ALL_GAMES(5),
    YOUR_GAMES(6),
    CHANGE_USER(7),
    QUIT(8),
    OTHER(9);

    private final int value;

    MainMenu(int value) {
        this.value = value;
    }

    public static MainMenu getOptionByIndex(int choice) {
        return Arrays.stream(MainMenu.values())
                .filter(en -> en.getValue() == choice)
                .findFirst()
                .orElse(OTHER);
    }

}

