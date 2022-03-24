package sda.training.rps.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }
}
