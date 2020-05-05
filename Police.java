package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class Police {

    private Scene scene;
    private Button forfeit;
    private Button flee;
    private Button fight;
    private Good goodTwo;
    private Good goodOne;
    private ArrayList<Good> inventory = Main.getUser().getShip().getItemInventory();

    public Button getForfeit() {
        return forfeit;
    }
    public Button getFlee() {
        return flee;
    }
    public Button getFight() {
        return fight;
    }
    public Good getOne() {
        return goodOne;
    }
    public void setOne(Good one) {
        this.goodOne = one;
    }
    public Good getTwo() {
        return goodTwo;
    }
    public void setTwo(Good two) {
        this.goodTwo = two;
    }
    public Scene getScene() {
        return scene;
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public String itemsToGet() {
        if (inventory.size() == 0) {
            return "You have no items?!";
        } else {
            goodOne = inventory.get(0);
            goodTwo = inventory.get(1);
            return "You are under arrest! Please forfeit the following items: "
                    + "\n" + goodTwo.getName() + "\n" + goodOne.getName();
        }
    }

    public GridPane policeInteractPane() {
        GridPane policePane = new GridPane();
        policePane.setAlignment(Pos.CENTER);
        policePane.setHgap(5);
        policePane.setVgap(10);
        policePane.setPrefSize(400, 400);

        // POLICE ASKS FOR ITEMS
        Label npcName = new Label("POLICE: ");
        policePane.add(npcName, 0, 0);
        Label itemsToForfeit = new Label(itemsToGet());
        policePane.add(itemsToForfeit, 1, 0);

        // BUTTONS FOR USE CASES
        forfeit = new Button("FORFEIT");
        flee = new Button("FLEE");
        fight = new Button("FIGHT");
        policePane.add(fight, 3, 3);
        policePane.add(flee, 0, 3);
        policePane.add(forfeit, 1, 3);

        return policePane;
    }

    public void playForfeit() {
        Main.getUser().getShip().removeFromInventory(goodOne, false);
        Main.getUser().getShip().removeFromInventory(goodTwo, false);
        // go to region B (code in Main)
    }

    public void playFlee() {
        if (Main.getUser().getPilot() > 5) {
            Main.getUser().getShip().decrementFuelCapacity();
        } else {
            playForfeit();
            Main.getUser().setCreditsValue(Main.getUser().getCreditsValue()
                    - goodTwo.getSellPrice() - goodOne.getSellPrice());
            Main.getUser().getShip().setHealth(Main.getUser().getShip().getHealth() - 1);
        }
        // go to region A (code in Main)
    }

    public void playFight() {
        if (Main.getUser().getFighter() < 5) {
            Main.getUser().setCreditsValue(Main.getUser().getCreditsValue()
                    - goodTwo.getSellPrice() - goodOne.getSellPrice());
        }
        // go to region B (code in Main)
    }


}
