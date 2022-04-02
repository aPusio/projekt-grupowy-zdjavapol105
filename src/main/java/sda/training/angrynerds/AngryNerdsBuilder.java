package sda.training.angrynerds;

import sda.training.angrynerds.game.AngryNerdsGame;
import sda.training.angrynerds.game.AngryNerdsUserIo;
import sda.training.angrynerds.game.map.AngryNerdsDisplay;
import sda.training.angrynerds.menu.AngryNerdsMenu;
import sda.training.utils.UserIoService;

public class AngryNerdsBuilder {
    public static AngryNerds build() {
        UserIoService userIoService = new UserIoService();
        return new AngryNerds(
                userIoService,
                new AngryNerdsMenu(),
                new AngryNerdsGame(new AngryNerdsDisplay(userIoService), new AngryNerdsUserIo(userIoService))
        );
    }
}
