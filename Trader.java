package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import  java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;

public class Trader {

    private Button buy;
    private Scene scene;
    private Button ignore;
    private Button rob;
    private Button negotiate;
    private Good buyChoice;
    private ChoiceBox traderGoodsAvailableDropDown = new ChoiceBox<>();
    private Good goodOne = new Good("Shoes", 5, 5);
    private Good goodTwo = new Good("Jewelry", 6, 10);
    private Good goodThree = new Good("Water", 7, 15);
    private GridPane traderPane = new GridPane();

    public Trader() {

        this.traderPane = traderInteractPane();
        scene = null;

    }
    public GridPane getTraderPane() {
        return traderPane;
    }

    public void setTraderPane(GridPane traderPane) {
        this.traderPane = traderPane;
    }

    public ChoiceBox getTraderGoodsAvailableDropDown() {
        return traderGoodsAvailableDropDown;
    }

    public Good getBuyChoice() {
        return buyChoice;
    }

    public void setBuyChoice(Good buyChoice) {
        this.buyChoice = buyChoice;
    }

    public Button getBuy() {
        return buy;
    }

    public Button getIgnore() {
        return ignore;
    }

    public Button getRob() {
        return rob;
    }

    public Button getNegotiate() {
        return negotiate;
    }
    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }


    private ArrayList<Good> traderInventory =
            new ArrayList<Good>(Arrays.asList(goodOne, goodTwo, goodThree));
    private Controller control;


    public GridPane traderInteractPane() {
        GridPane traderPane = new GridPane();
        traderPane.setAlignment(Pos.CENTER);
        traderPane.setHgap(5);
        traderPane.setVgap(10);
        traderPane.setPrefSize(400, 400);
        Label npcName = new Label("TRADER: Where do you think you're going?!");
        traderPane.add(npcName, 0, 0);

        // BUTTONS FOR USE CASES
        buy = new Button("BUY");
        ignore = new Button("IGNORE");
        rob = new Button("ROB");
        negotiate = new Button("NEGOTIATE");
        traderPane.add(buy, 3, 3);
        traderPane.add(ignore, 0, 3);
        traderPane.add(negotiate, 2, 3);
        traderPane.add(rob, 1, 3);

        //GOODS THAT TRADER SELLS
        ObservableList<Good> traderGoodsList = FXCollections.observableArrayList();
        for (Good goods : traderInventory) {
            traderGoodsList.add(goods);

        }
        traderGoodsAvailableDropDown.setItems(traderGoodsList);
        traderPane.add(traderGoodsAvailableDropDown, 1, 0);
        return traderPane;
    }

    public void playBuy(Good good) {
        if (good.getBuyPrice() > Main.getUser().getCreditsValue()) {
            control.alertMessage("PURCHASE ERROR", "PRICE OF GOOD EXCEEDS AVAILABLE CREDITS");
        }
        if (Main.getUser().getShip().getCargoCapacity()
                == Main.getUser().getShip().getItemInventory().size()) {
            control.alertMessage("PURCHASE ERROR", "SHIP HAS FULL CARGO CAPACITY");
        }
        Main.getUser().getShip().addToInventory(good);
    }


    public void playRob() {
        if (Main.getUser().getFighter() < 5) {
            Main.getUser().getShip().setHealth(Main.getUser().getShip().getHealth() - 1);
        } else {
            traderInventory.remove(goodTwo);
            traderInventory.remove(goodThree);
            Main.getUser().getShip().addToInventory(goodTwo);
            Main.getUser().getShip().addToInventory(goodThree);
        }
    }

    public void playNegotiate() {
        if (Main.getUser().getMerchant() < 5) {
            for (Good traderGood : traderInventory) {
                traderGood.setSellPrice(traderGood.getSellPrice() + 5);
            }
        } else {
            for (Good traderGood : traderInventory) {
                traderGood.setSellPrice(traderGood.getSellPrice() - 2);
                if (traderGood.getSellPrice() <= 0) {
                    traderGood.setSellPrice(0);
                }
            }
        }
    }
}
