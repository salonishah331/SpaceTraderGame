package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;

public class Ship {
    private String name;
    private int cargoCapacity;
    private int fuelCapacity;
    private int health;
    private ArrayList<Good> inventory;
    private Controller control = new Controller();

    public Ship(int cargocapacity, int fuelcapacity, String name, int health) {
        this.cargoCapacity = cargocapacity;
        this.fuelCapacity = fuelcapacity;
        this.name = name;
        this.health = health;
        this.inventory = new ArrayList<>();
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {

        this.fuelCapacity = fuelCapacity;
    }

    public void decrementFuelCapacity() {
        fuelCapacity--;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public ArrayList<Good> getItemInventory() {
        return inventory;
    }

    public void setItemInventory(ArrayList<Good> itemInventory) {
        inventory = itemInventory;
    }

    public ChoiceBox shipContentDropDown() {
        ChoiceBox<Good> shipContentDropDown  = new ChoiceBox<>();
        ObservableList<Good> shipgoodsList = FXCollections.observableArrayList();
        for (Good good: inventory) {
            shipgoodsList.add(good);
        }
        shipContentDropDown.setItems(shipgoodsList);
        return shipContentDropDown;
    }

    public void addToInventory(Good good) {
        boolean result = false;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(good.getName())) {
                inventory.get(i).increaseQuantity();
                result = true;
            }
        }
        if (!result) {
            inventory.add(good);
        }
        Main.getUser().setCreditsValue(Main.getUser().getCreditsValue() - good.getBuyPrice());
    }

    public void removeFromInventory(Good good) {
        boolean result = false;
        if (inventory.size() == 0) {
            control.alertMessage("SELLING ERROR", "EMPTY INVENTORY");
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getName().equals(good.getName())) {
                    inventory.get(i).decreaseQuantity();
                    result = true;
                    Main.getUser().setCreditsValue(Main.getUser().getCreditsValue()
                            + good.getSellPrice());
                }
            }
        }
        if (!result) {
            control.alertMessage("SELLING ERROR", "GOOD NOT AVAILABLE TO SELL");
        }
    }

    public void removeFromInventory(Good good, boolean result) {
        if (inventory.size() == 0) {
            control.alertMessage("ERROR", " Empty inventory");
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getName().equals(good.getName())) {
                    inventory.get(i).decreaseQuantity();
                    cargoCapacity--;
                    result = true;
                }
            }
        }
        if (!result) {
            control.alertMessage("ERROR", "GOOD NOT AVAILABLE");
        }
    }

    public String toString() {
        String str = "";
        for (Good good: inventory) {
            str += "[ " + good.toString() + "], ";
        }
        return str;
    }

}
