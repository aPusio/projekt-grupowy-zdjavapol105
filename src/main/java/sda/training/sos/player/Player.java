package sda.training.sos.player;

import lombok.Builder;
import lombok.Value;
import sda.training.sos.game.GameSymbol;
import sda.training.sos.game.Position;

import java.util.AbstractMap;
import java.util.Map;

@Value
@Builder
public class Player {
    String name;

    public Map.Entry<Position, GameSymbol> readMove() {
        //TODO read what player typed
        //x,y, symbol
        //ApacheUtils <-

//        AbstractMap
//        Map.Entry<Position, GameSymbol> positionGameSymbolEntry = new Map.Entry<>();
//        positionGameSymbolEntry.set
        return null;
    }
}
