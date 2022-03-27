package sda.training.sos.player;

//TODO fix players loading
public class PlayersService {
    public Player loadPlayer1() {
        return Player.builder()
                .name("Gracz1")
                .build();
    }

    public Player loadPlayer2() {
        return Player.builder()
                .name("Gracz2")
                .build();
    }

}
