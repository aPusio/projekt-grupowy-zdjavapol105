package sda.training.battleships.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GAM_ID")
    private Integer id;
    @Column(name = "GAM_WDTH")
    private Integer width;
    @Column(name = "GAM_HGHT")
    private Integer heigth;

    private Player player;
}
