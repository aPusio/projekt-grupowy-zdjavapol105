package sda.training.rps.model;

import lombok.Getter;
import lombok.Setter;
import sda.training.rps.util.Result;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "RPS_GAME")
public class Game {
    @Id
    @Column(name = "GAM_ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "GAM_PLA_ID", referencedColumnName = "PLA_ID")
    private Player player;

    @Column(name = "GAM_WIN_STAGES_NO")
    private Integer winStagesNo;
    @Column(name = "GAM_PLAYER_SCORE")
    private Integer playerScore;
    @Column(name = "GAM_CP_SCORE")
    private Integer computerScore;
    @Column(name = "GAM_RESULT")
    @Enumerated(EnumType.STRING)
    private Result result;
    @Column(name = "GAM_DATETIME_START")
    private LocalDateTime startDate;
    @Column(name = "GAM_DATETIME_END")
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "game")
    private Set<Stage> stages;

}
