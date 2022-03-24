package sda.training.angry_nerds_game.schema;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "Angry_Player")
public class AngryNerdsPlayer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String name;
        private Integer gamesPlayed;
        private Integer gamesWon;
        private Integer gamesLost;
        @OneToMany(mappedBy = "player")
        private Set<AngryNerdsShot> shot;

}
