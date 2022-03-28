package sda.training.angry_nerds_game.schema;

import lombok.Data;

import javax.persistence.*;
import java.util.InputMismatchException;
import java.util.Scanner;

@Entity
@Data
@Table(name = "Angry_Shot")
public class AngryNerdsShot {

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
    private AngryNerdsPlayer player;

    public void enterShotValues() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj Paramerty Strzału X: ");
        while (true) {
            try {
                shotX = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Podaj poprawne wartości ...");
            }
            scanner.next();
        }
        System.out.print("Podaj Paramerty Strzału Y: ");
        while (true) {
            try {
                shotY = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Podaj poprawne wartości ...");
            }
            scanner.next();
        }
        scanner.nextLine();
        System.out.println("Paramerty strzału X= " + shotX + " Y= " + shotY);
    }

}
