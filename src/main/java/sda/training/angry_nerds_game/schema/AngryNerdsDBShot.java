package sda.training.angry_nerds_game.schema;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "Angry_Shot")
public class AngryNerdsDBShot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer shotX;
    private Integer shotY;
    private Integer targetX;
    private Integer targetY;
    private Integer targetDx;
    private Integer targetDy;
    @ManyToOne
    private AngryNerdsDBPlayer player;

}
