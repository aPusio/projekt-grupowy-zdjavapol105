package sda.training.angry_nerds_game.game;

public class ConsoleScreen {


    public void showScreen(int[][] gameScreen ) {
            for (int i = gameScreen[0].length-1; i >=0; i--) {
                for (int j = 0; j < gameScreen.length; j++) {
                    System.out.print((char)gameScreen[j][i]);
                }
                System.out.println();
            }
            System.out.println();
    }

    public void showColorScreen(int[][] gameScreen ) {
        for (int i = gameScreen[0].length-1; i >=0; i--) {
            for (int j = 0; j < gameScreen.length; j++) {

                switch ((char)gameScreen[j][i]) {
                    case '*':
                        System.out.print(toColor(Color.RED,ColorBackground.BLACK_BACKGROUND,"*"));
                        break;
                    case 'T':
                        System.out.print(toColor(Color.GREEN,ColorBackground.GREEN_BACKGROUND,"T"));
                        break;
                    case 'S':
                        System.out.print(toColor(Color.YELLOW,"S"));
                        break;
                    default:
                        System.out.print(toColor(Color.BLACK,ColorBackground.BLACK_BACKGROUND,"."));
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static String toColor(Color color, String string) {
        return color.getColor() + string + Color.RESET.getColor();
    }

    public static String toColor(Color color, ColorBackground colorBackground, String string) {
        return color.getColor() + colorBackground.getColor() + string + Color.RESET.getColor();
    }

}

