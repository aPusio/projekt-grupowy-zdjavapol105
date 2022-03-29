package sda.training.angry_nerds_game.game;

import sda.training.angry_nerds_game.schema.AngryNerdsDBPlayer;
import sda.training.angry_nerds_game.schema.AngryNerdsDBShot;

public class CopyToDBClass {

    public void copyValuesToAngryNerdsDBShot(Shot shot,Target target, AngryNerdsPlayer player){

        AngryNerdsDBShot angryNerdsDBShot = new AngryNerdsDBShot();

        angryNerdsDBShot.setTargetX(target.getxPosition());
        angryNerdsDBShot.setTargetY(target.getyPosition());
        angryNerdsDBShot.setTargetDx(target.getxSize());
        angryNerdsDBShot.setTargetDy(target.getySize());
        angryNerdsDBShot.setShotX(shot.getShotX());
        angryNerdsDBShot.setShotY(shot.getShotY());

        //AngryNerdsDBPlayer angryNerdsDBPlayer = findById(player.getId());
        //angryNerdsDBShot.setPlayer(angryNerdsDBPlayer);

    }


}
