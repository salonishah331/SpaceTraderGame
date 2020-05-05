package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.layout.RowConstraints;

public class Region {
    private int techLevel;
    private int[] coordinate = new int[2];
    private String name;
    private String description;
    private boolean visited;
    private Button map;
    private Button update;
    private Marketplace market = new Marketplace("market");
    private ChoiceBox goodsAvailableDropDown;
    private Good buyChoice;
    private Good sellChoice;
    private Label money;
    private GridPane regionPane;
    private String creditDisplay;
    private Scene scene;

    public Region(int techLevel, int[] coordinate, String name, String description) {
        this.techLevel = techLevel;
        this.coordinate = coordinate;
        this.name = name;
        this.description = description;
        market.goodsAvailable(techLevel);
        goodsAvailableDropDown  = new ChoiceBox<>();
        regionPane = new GridPane();
        creditDisplay = "Money To Spend: " + Main.getUser().getCreditsValue();

    }

    public Scene getScene() {
        return scene;
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public ChoiceBox getGoodsAvailableDropDown() {
        return goodsAvailableDropDown;
    }
    public Button getUpdate() {
        return update;
    }
    public Good getBuyChoice() {
        return buyChoice;
    }
    public GridPane getRegionPane() {
        return regionPane;
    }
    public void setRegionPane(GridPane regionPane) {
        this.regionPane = regionPane;
    }
    public void setBuyChoice(Good buyChoice) {
        this.buyChoice = buyChoice;
    }
    public void updateShipDropDown() {
        regionPane.add(Main.getUser().getShip().shipContentDropDown(), 3, 1);
    }
    public void updateMoneyLabel() {
        money.setText("Money To Spend: " + Main.getUser().getCreditsValue());
    }
    public Good getSellChoice() {
        return sellChoice;
    }
    public void setSellChoice() {
        this.sellChoice =
                (Good) Main.getUser().getShip()
                        .shipContentDropDown().getSelectionModel().getSelectedItem();
    }
    public Marketplace getMarket() {
        return market;
    }
    public int getTechLevel() {
        return techLevel;
    }
    public void setTechLevel(int techLevel) {
        this.techLevel = techLevel;
    }
    public int getCoordinateX() {
        return coordinate[0];
    }
    public int getCoordinateY() {
        return coordinate[1];
    }
    public int[] getCoordinate() {
        return coordinate;
    }

    public String coordinateToString() {
        String str = "";
        str += Integer.toString(coordinate[0]) + ", ";
        str += Integer.toString(coordinate[1]);
        return str;
    }

    public void setCoordinate(int[] coordinate) {
        this.coordinate = coordinate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setIsVisited(boolean isVisited) {
        visited = isVisited;
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public GridPane createGridPane() {
        for (int i = 0; i < 4; i++) {
            ColumnConstraints column = new ColumnConstraints(125);
            regionPane.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 3; i++) {
            RowConstraints row = new RowConstraints(100);
            regionPane.getRowConstraints().add(row);
        }
        Label characteristic = new Label("REGION" + "\n" + name + "\n" + description
                + "\n" + techLevel);
        characteristic.setMinWidth(500);
        characteristic.setMinHeight(500);
        characteristic.setWrapText(true);
        regionPane.add(characteristic, 0, 0);

        Label goodsAvailableLabel = new Label("Goods Available To Buy:");
        regionPane.add(goodsAvailableLabel, 0, 1);
        ObservableList<Good> goodsList = FXCollections.observableArrayList();
        for (Good good: getMarket().getGoodsAvailable()) {
            goodsList.add(good);
        }
        goodsAvailableDropDown.setItems(goodsList);
        regionPane.add(goodsAvailableDropDown, 1, 1);
        money = new Label(creditDisplay);
        regionPane.add(money, 0, 2);
        Label shipContentLabel = new Label("Goods Available To Sell:");
        regionPane.add(shipContentLabel, 2, 1);
        regionPane.add(Main.getUser().getShip().shipContentDropDown(), 3, 1);

        map = new Button("map");
        update = new Button("update");
        regionPane.add(map, 0, 3);
        regionPane.add(market.getBuy(), 1, 3);
        regionPane.add(market.getSell(), 2, 3);
        regionPane.add(update, 3,  3);

        setVisited(true);
        setRegionPane(regionPane);
        return regionPane;
    }

    public Button getMapButton() {
        return map;
    }
}
