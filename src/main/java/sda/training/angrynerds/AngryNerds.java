package sda.training.angrynerds;


import lombok.AllArgsConstructor;
import sda.training.angrynerds.game.AngryNerdsGame;
import sda.training.angrynerds.menu.AngryNerdsMenu;
import sda.training.angrynerds.menu.MenuOption;
import sda.training.utils.UserIoService;

@AllArgsConstructor
public class AngryNerds {
    private UserIoService userIoService;
    private AngryNerdsMenu angryNerdsMenu;
    private AngryNerdsGame angryNerdsGame;
    public void start(){
        MenuOption chosenOption = angryNerdsMenu.execute(userIoService);
        switch (chosenOption){
            case NEW_GAME:
                angryNerdsGame.start();
        }
    }
}
