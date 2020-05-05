package sample;
//import javafx.event.ActionEvent;
//import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
//import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Universe {
    private Region[] regionArr = new Region[10];
    private String[] nameArr =  {"Venus", "Earth", "Mars", "Neptune", "Mercury",
        "Saturn", "Jupiter", "Pluto", "Zeon", "Uranus"
    };
    private int[] techLevels =  {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
        11, 12, 13, 14, 15, 16, 17, 18, 19, 20
    };
    private int[][] coordinates =  {{0, 0}, {1, 0}, {2, 0}, {3, 0},
        {0, 1}, {1, 1}, {2, 1}, {3, 1}, {0, 2}, {1, 2}
    };
    private String[] descriptions = {"This region is filled with mountains, "
            + "plains, high plateaus, canyons, volcanoes, and ridges. "
            + "It has a temperature of 864 degrees Fahrenheit. "
            + "Local inhabitants of this region are called the Venetians "
            + "and don't interact with space invaders that well.", "This region "
            + "is filled with mountains, oceans, and prairies."
            + "It has a temperature of 70 degrees Fahrenheit. Local inhabitants of this region are "
            + "called the Humans and don't interact with space invaders that well.", "This "
            + "region is filled with dark slope streaks, dust devil tracks, and rivers. "
                   + "It has a temperature of -125 degrees C. "
                   + "Local inhabitants of this region are called the Martians "
            + "and don't interact with space invaders that well. ", "This "
            + "region is very blue and filled with icy slush and dust. "
                   + "It has a temperature of -392 degrees F. "
                   + "Local inhabitants of this region are called the Neptunians"
                   + " and don't interact with space invaders that well. ", "This "
            + "region is filled with lunar highlands and cliffs. "
                   + "It has a temperature of 801 degrees F. "
                   + "Local inhabitants of this region are called the "
                   + "Mercurians and don't interact with space invaders that well. ", "This "
            + "region is filled with swirling gases and liquids deeper down. "
                   + "It has a temperature of -178 degrees Celcius. "
                   + "Local inhabitants of this region are called the "
                   + "Sundials and don't interact with space invaders that well. ", "This "
            + "region is filled with grassy fields and dirt pathways."
                   + " It has a temperature of -578 degrees Celcius. "
                   + "Local inhabitants of this region are called the Loners "
                   + "and don't interact with space invaders that well. ", "This "
            + "region is filled with cliffs, caves, and lakes. "
                   + "It has a temperature of -790 degrees Celcius. "
                   + "Local inhabitants of this region are called the Carls"
                   + " and don't interact with space invaders that well. ", "This "
            + "region is filled with dark slope streaks, salt lakes, "
                   + "icy glaciers, and rivers. It has a temperature of 854 degrees F. "
                   + "Local inhabitants of this region are called the "
                   + "Amomas and don't interact with space invaders that well. ", "This "
            + "region is full of emptiness. It is very large, but cold and windy."
                   + "It has a temperature of -1000 degrees F. "
                   + "Local inhabitanats of this region is called Uranians."
    };
    private Button[] buttonList = new Button[10];
    private int[] currPosition = new int[2];
    private ArrayList<Region> regionsVisited = new ArrayList<>();

    private Random random = new Random();
    private GridPane universePane = new GridPane();
    private List<Object> npcEncounters = new ArrayList<Object>();

    public Universe() {
        npcEncounters.add(0, new Police());
        npcEncounters.add(1, new Trader());
        npcEncounters.add(2, new Bandit());
    }

    public Object getRandomNpcEncounters(int a) {
        if (a == 0 || a == 1 || a == 2) {
            return npcEncounters.get(a);
        }
        return npcEncounters.get(0);
    }

    public Region generateRegion() {
        Random random = new Random();

        String name = null;
        int nameIndex = random.nextInt(10);
        while (name == null) {
            if (nameArr[nameIndex] != null) {
                name = nameArr[nameIndex];
            } else {
                nameIndex = random.nextInt(10);
            }
        }
        nameArr[nameIndex] = null;

        int techLevel = -1;
        int techIndex = random.nextInt(20);
        while (techLevel == -1) {
            if (techLevels[techIndex] != -1) {
                techLevel = techLevels[techIndex];
            } else {
                techIndex = random.nextInt(20);
            }
        }
        techLevels[techIndex] = -1;

        int[] coordinate = null;
        int coordinateIndex = random.nextInt(10);
        while (coordinate == null) {
            if (coordinates[coordinateIndex] != null) {
                coordinate = coordinates[coordinateIndex];
            } else {
                coordinateIndex = random.nextInt(10);
            }
        }

        coordinates[coordinateIndex] = null;
        String description = descriptions[nameIndex];
        return new Region(techLevel, coordinate, name, description);
    }

    public GridPane createPane() {
        for (int i = 0; i < 4; i++) {
            ColumnConstraints column = new ColumnConstraints(125);
            universePane.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 3; i++) {
            RowConstraints row = new RowConstraints(100);
            universePane.getRowConstraints().add(row);
        }
        for (int i = 0; i < 10; i++) {
            regionArr[i] = generateRegion();
        }
        for (int i = 0; i < 10; i++) {
            Button regionButton;
            if (regionArr[i].isVisited()) {
                regionButton = new Button(regionArr[i].getName());
            } else {
                regionButton = new Button(regionArr[i].coordinateToString()
                       + " " + calculateDistance(regionArr[i]));
            }
            buttonList[i] = regionButton;
            regionButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            universePane.add(regionButton,
                   regionArr[i].getCoordinateX(), regionArr[i].getCoordinateY());
        }
        return universePane;
    }

    public double calculateDistance(Region region) {
        int xCoordinate = region.getCoordinateX();
        int yCoordinate = region.getCoordinateY();
        return Math.sqrt(Math.pow((xCoordinate - currPosition[0]), 2)
               + Math.pow(yCoordinate - currPosition[1], 2));
    }

    public Button[] getArrButtons() {
        return buttonList;
    }

    public Region[] getRegionArr() {
        return regionArr;
    }
}
