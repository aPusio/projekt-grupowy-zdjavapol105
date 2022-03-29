package sda.training.angry_nerds_game.schema;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "AngryTest_Player")
public class AngryNerdsDBPlayer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String name;
        private Integer gamesPlayed;
        private Integer gamesWon;
        private Integer gamesLost;
        @OneToMany(mappedBy = "player")
        private Set<AngryNerdsDBShot> shot;

}
