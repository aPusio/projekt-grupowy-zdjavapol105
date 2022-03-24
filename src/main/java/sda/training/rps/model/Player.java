package sda.training.rps.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "RPS_PLAYER")
public class Player {
    @Id
    @Column(name = "PLA_ID")
    private Integer id;
    @Column(name = "PLA_NAME")
    private String name;
    @Column(name = "PAL_TOTAL_SCORE")
    private Integer score;

    @OneToMany(mappedBy = "player")
    private Set<Game> games;

    public Player(String name) {
        this.name = name;
    }
}
