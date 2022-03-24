package sda.training.rps.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
    Integer id;
    Player player;
    Integer winStagesNo;
    Integer playerScore;
    Integer computerScore;
}
