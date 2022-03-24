package sda.training.rps.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sda.training.rps.util.Move;
import sda.training.rps.util.Result;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Stage {
    Game game;
    Move playerMove;
    Move computerMove;
    Result result;
}
