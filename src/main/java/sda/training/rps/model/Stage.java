package sda.training.rps.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sda.training.rps.util.Move;
import sda.training.rps.util.Result;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "RPS_STAGE")
public class Stage {
    @Id
    @Column(name = "STA_ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "STA_GAM_ID", referencedColumnName = "GAM_ID")
    private Game game;
    @Column(name = "STA_PLAYER_MOVE")
    @Enumerated(EnumType.STRING)
    private Move playerMove;
    @Column(name = "STA_CP_MOVE")
    @Enumerated(EnumType.STRING)
    private Move computerMove;
    @Column(name = "STA_OUTCOME")
    @Enumerated(EnumType.STRING)
    private Result result;

    public Stage(Game game, Move playerMove, Move computerMove, Result result) {
        this.game = game;
        this.playerMove = playerMove;
        this.computerMove = computerMove;
        this.result = result;
    }
}
