package sda.training.rps.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MainMenu {
    NEW_GAME(1),
    LOAD_GAME(2),
    TOP10(3),
    ALL_GAMES(4),
    YOUR_GAMES(5),
    CHANGE_USER(6),
    QUIT(7),
    OTHER(8);

    private int value;

    MainMenu(int value) {
        this.value = value;
    }

    public static MainMenu getOptionByIndex(int choice){
        return Arrays.stream(MainMenu.values())
                .filter(en -> en.getValue() == choice)
                .findFirst()
                .orElse(OTHER);
    }

}

