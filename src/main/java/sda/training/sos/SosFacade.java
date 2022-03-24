package sda.training.sos;

import lombok.RequiredArgsConstructor;
import sda.training.HibernateFactory;
import sda.training.sos.game.GameEngine;
import sda.training.sos.menu.MenuOption;
import sda.training.sos.menu.SosMenu;
import sda.training.sos.player.Player;
import sda.training.sos.player.PlayersService;

@RequiredArgsConstructor
public class SosFacade {
    private final HibernateFactory hibernateFactory;
    private SosMenu sosMenu = new SosMenu();
    private GameEngine gameEngine = new GameEngine();
    private PlayersService playersService = new PlayersService();


    public void startGame(){
        sosMenu.printMenu();
        MenuOption option = sosMenu.readUserOption();


        switch (option){
            case NEW_GAME:
                Player player1 = playersService.loadPlayer1();
                Player player2 = playersService.loadPlayer2();
                gameEngine.startNewGame(player1, player2);
                break;
            case LOAD_GAME:
                break;
            case UNKNOWN_OPTION:
                break;
        }
    }
}
