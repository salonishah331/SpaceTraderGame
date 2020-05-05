package sample;

import javafx.scene.control.Button;
import java.util.ArrayList;

public class Marketplace {
    private String name;
    private ArrayList<Good> goodsAvailable = new ArrayList<>();
    private int marketValue;
    private Controller control = new Controller();
    private Button buy = new Button("buy");
    private Button sell = new Button("sell");

    public Button getBuy() {
        return buy;
    }

    public ArrayList<Good> getGoodsAvailable() {
        return goodsAvailable;
    }

    public Button getSell() {
        return sell;
    }

    public Marketplace(String name) {
        this.name = name;
    }

    public void goodsAvailable(int techLevel) {
        if (techLevel <= 10) {
            goodsAvailable.add(new Good("Map", techLevel, 5));
            goodsAvailable.add(new Good("Morse Code Machine", techLevel, 4));
            goodsAvailable.add(new Good("Notepad", techLevel, 3));
            goodsAvailable.add(new Good("Medicine", techLevel, 2));
            goodsAvailable.add(new Good("Jackets", techLevel, 1));
            goodsAvailable.add(new Good("5 Units of Ship Fuel", techLevel, 5));
        } else if (techLevel > 10 && techLevel <= 15) {
            goodsAvailable.add(new Good("Compass", techLevel, 1));
            goodsAvailable.add(new Good("Missiles", techLevel, 2));
            goodsAvailable.add(new Good("Space suit", techLevel, 3));
            goodsAvailable.add(new Good("GPS", techLevel, 4));
            goodsAvailable.add(new Good("Telegraph", techLevel, 5));
            goodsAvailable.add(new Good("5 Units of Ship Fuel", techLevel, 5));
        } else {
            goodsAvailable.add(new Good("Drone", techLevel, 1));
            goodsAvailable.add(new Good("Guns", techLevel, 2));
            goodsAvailable.add(new Good("Heat pad", techLevel, 3));
            goodsAvailable.add(new Good("Bullets", techLevel, 4));
            goodsAvailable.add(new Good("Telescope", techLevel, 5));
            goodsAvailable.add(new Good("5 Units of Ship Fuel", techLevel, 5));
        }
        int shipHealth = 5;
        if (Main.getUser().getEngineer() < 5) {
            shipHealth = 10;
        } else if (Main.getUser().getEngineer() >= 5 && Main.getUser().getEngineer() < 10) {
            shipHealth = 5;
        } else {
            shipHealth = 3;
        }
        goodsAvailable.add(new Good("5 Units of Health", techLevel, shipHealth));
        int a = (int) ((Math.random() * 9) + 1);
        if (a <= 7) {
            goodsAvailable.add(new Good("Gold Tinted Sword", techLevel, 3));
        }
    }

    public void buy(Good good) {
        if (good.getBuyPrice() > Main.getUser().getCreditsValue()) {
            control.alertMessage("PURCHASE ERROR", "PRICE OF GOOD EXCEEDS AVAILABLE CREDITS");
        }
        if (Main.getUser().getShip().getCargoCapacity()
                == Main.getUser().getShip().getItemInventory().size()) {
            control.alertMessage("PURCHASE ERROR", "SHIP HAS FULL CARGO CAPACITY");
        }
        if (good.getName().equals("5 Units of Health")) {
            Main.getUser().getShip().setHealth(Main.getUser().getShip().getHealth() + 5);
        } else if (good.getName().equals("5 Units of Ship Fuel")) {
            Main.getUser().getShip()
                    .setFuelCapacity(Main.getUser().getShip().getFuelCapacity() + 5);
        } else {
            Main.getUser().getShip().addToInventory(good);
        }
    }

    public void sell(Good good) {
        if (Main.getUser().getShip().getItemInventory().size() == 0) {
            control.alertMessage("PURCHASE ERROR", "SHIP HAS NO GOODS TO SELL");
        }
        Main.getUser().getShip().removeFromInventory(good);
    }

    public String toString() {
        String str = "";
        for (Good good: goodsAvailable) {
            str += "[" + good.toString() + "], " + "\n";
        }
        return str;
    }

}
