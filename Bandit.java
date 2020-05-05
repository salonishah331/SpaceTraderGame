package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.Media;
import javafx.stage.Stage;
//import java.io.File;
//import java.net.URL;
import java.util.Random;



public class Main extends Application {
    private static Stage stage;
    private static Stage anotherStage;

    public static User getUser() {
        return user;
    }

    private static User user = new User();
    private Controller control = new Controller();
    private Universe universe = new Universe();
    private Random random = new Random();


    public static void setStage(Stage newStage) {
        stage = newStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //File file = new File("./starwars.mp3");
        //URL url = file.toURI().toURL();
        //String urlString = url.toString();
        //Media music = new Media(urlString);
        //MediaPlayer mediaplayer = new MediaPlayer(music);
        //mediaplayer.setAutoPlay(true);
        //mediaplayer.setVolume(0.1);

        //WELCOME SCREEN
        stage = primaryStage;
        BorderPane startPane = new BorderPane();
        Label welcome = new Label("WELCOME TO SPACE TRADERS!");
        startPane.setCenter(welcome);
        HBox hbButtons = new HBox();
        Button startBtn = new Button();
        startBtn.setText("Start");
        hbButtons.getChildren().add(startBtn);
        hbButtons.setAlignment(Pos.BOTTOM_CENTER);
        startPane.setBottom(hbButtons);

        // CHARACTER INFO SCREEN
        GridPane configPane = new GridPane();
        configPane.setAlignment(Pos.CENTER);
        configPane.setHgap(5);
        configPane.setVgap(10);
        Label username = new Label("Enter Username: ");
        configPane.add(username, 0, 1);
        TextField usernameField = new TextField();
        configPane.add(usernameField, 1, 1);
        Scene configScene = new Scene(configPane, 500, 300);
        Label difficulty = new Label("Choose Difficulty: ");
        configPane.add(difficulty, 0, 2);
        String[] difficultyLevel = {"EASY (15pt)", "MEDIUM (10pt)", "HARD (5pt)"};
        ChoiceBox difficultyDropDown =
                new ChoiceBox(FXCollections.observableArrayList(difficultyLevel));
        configPane.add(difficultyDropDown, 1, 2);
        Label pilotLabel = new Label("Pilot: ");
        configPane.add(pilotLabel, 0, 3);
        TextField pilotField = new TextField();
        pilotField.setMaxWidth(70);
        configPane.add(pilotField, 1, 3);
        Label fighterLabel = new Label("Fighter: ");
        configPane.add(fighterLabel, 2, 3);
        TextField fighterField = new TextField();
        fighterField.setMaxWidth(70);
        configPane.add(fighterField, 3, 3);
        Label merchantLabel = new Label("Merchant: ");
        configPane.add(merchantLabel, 0, 4);
        TextField merchantField = new TextField();
        merchantField.setMaxWidth(70);
        configPane.add(merchantField, 1, 4);
        Label engineerLabel = new Label("Engineer: ");
        configPane.add(engineerLabel, 2, 4);
        TextField engineerField = new TextField();
        engineerField.setMaxWidth(70);
        configPane.add(engineerField, 3, 4);

        // CREATE CHARACTER BUTTON
        Button createCharacter = new Button("Create Character");
        configPane.add(createCharacter, 0, 5);

        // WELCOME --> CREATE CHARACTER
        startBtn.setOnAction(e -> stage.setScene(configScene));

        // CHARACTER DISPLAY SCREEN
        GridPane characterSheetPane = new GridPane();
        characterSheetPane.setAlignment(Pos.CENTER);
        characterSheetPane.setHgap(5);
        characterSheetPane.setVgap(10);
        Scene characterScene = new Scene(characterSheetPane, 500, 300);

        //SPAWN BUTTON
        Button spawn = new Button("SPAWN");
        characterSheetPane.add(spawn, 0, 5);

        createCharacter.setOnAction(e -> {
            // CREATE USER OBJECT AND STORE VARIABLE VALUES
            user.setUsername(usernameField.getText());
            user.setEngineer(Integer.parseInt(engineerField.getText()));
            user.setMerchant(Integer.parseInt(merchantField.getText()));
            user.setFighter(Integer.parseInt((fighterField.getText())));
            user.setPilot(Integer.parseInt((pilotField.getText())));
            user.setDifficultyChoice((String) difficultyDropDown.getValue());

            if (user.getUsername() == null || user.getUsername().equals("")) {
                control.alertMessage("USERNAME ERROR", "USERNAME CANNOT BE EMPTY");
                return;
            } else if (user.getDifficultyChoice() == null) {
                control.alertMessage("DIFFICULTY CHOICE ERROR",
                        "MUST SELECT A DIFFICULTY LEVEL FROM DROPDOWN MENU");
                return;
            } else if (fighterField.getText().equals("") || pilotField.getText().equals("")
                    || merchantField.getText().equals("") || engineerField.getText().equals("")) {
                control.alertMessage("INPUT ERROR",
                        "ALL THE SKILL POINTS SHOULD BE FILLED OUT");
            }
            if (user.getDifficultyChoice().equals("EASY (15pt)")) {
                user.setDifficultyPoints(15);
                user.setCreditsValue(1000);
            } else if (user.getDifficultyChoice().equals("MEDIUM (10pt)")) {
                user.setDifficultyPoints(10);
                user.setCreditsValue(500);
            } else if (user.getDifficultyChoice().equals("HARD (5pt)")) {
                user.setDifficultyPoints(5);
                user.setCreditsValue(100);
            }
            if (user.getEngineer() < 0 || user.getFighter() < 0
                    || user.getMerchant() < 0 || user.getPilot() < 0) {
                control.alertMessage("SKILL POINTS ERROR",
                        "SKILL POINTS CANNOT BE NEGATIVE");
                return;
            } else if (user.skillPointSum() > user.getDifficultyPoints()) {
                control.alertMessage("SKILL POINTS ERROR",
                        "NOT ENOUGH POINTS TO ALLOCATE: YOU ONLY HAVE "
                                + user.getDifficultyPoints());
                return;
            }
            Label userNameValue = new Label("Username: " + usernameField.getText());
            characterSheetPane.add(userNameValue, 0, 1);
            Label difficultyValue = new Label("Difficulty Level: " + user.getDifficultyChoice());
            characterSheetPane.add(difficultyValue, 0, 2);
            Label creditsValue = new Label("Credits: " + user.getCreditsValue());
            characterSheetPane.add(creditsValue, 2, 2);
            Label pilotValue = new Label("Pilot: " + user.getPilot());
            characterSheetPane.add(pilotValue, 0, 3);
            Label fighterValue = new Label("Fighter: " + user.getFighter());
            characterSheetPane.add(fighterValue, 2, 3);
            Label merchantValue = new Label("Merchant: " + user.getMerchant());
            characterSheetPane.add(merchantValue, 0, 4);
            Label engineerValue = new Label("Engineer: " + user.getEngineer());
            characterSheetPane.add(engineerValue, 2, 4);
            stage.setScene(characterScene);

        });

        spawn.setOnAction(name -> {
            startRandomRegion();
        });

        stage.setScene(characterScene);
        stage.setTitle("Space Trader");
        stage.setScene(new Scene(startPane, 500, 400));
        stage.show();
    }

    private void startRandomRegion() {
        var ref = new Object() {
            Scene universeScene;
            GridPane universePane = universe.createPane();
            int regionIndex = random.nextInt(10);
            Region randomRegion;
            Scene randomScene;
        };
        Button ranRegionBtn = universe.getArrButtons()[ref.regionIndex];
        ref.universeScene = new Scene(ref.universePane);

        ref.randomRegion = universe.getRegionArr()[ref.regionIndex];
        ref.randomScene = new Scene(ref.randomRegion.createGridPane());
        ref.randomRegion.setScene(ref.randomScene);
        stage.setScene(ref.randomRegion.getScene());

        ref.randomRegion.getUpdate().setOnAction(e -> {
            ref.randomRegion.updateShipDropDown();
            ref.randomRegion.updateMoneyLabel();
        });

        ref.randomRegion.getMarket().getBuy().setOnAction(e -> {
            ref.randomRegion.setBuyChoice((Good)
                    ref.randomRegion.getGoodsAvailableDropDown()
                            .getSelectionModel().getSelectedItem());
            if (ref.randomRegion.getBuyChoice().getName().equals("Gold Tinted Sword")) {
                winnerScreen();
            }
            ref.randomRegion.getMarket().buy(ref.randomRegion.getBuyChoice());
        });

        ref.randomRegion.getMarket().getSell().setOnAction(e -> {
            ref.randomRegion.setSellChoice();
            ref.randomRegion.getMarket().sell(ref.randomRegion.getBuyChoice());
        });

        ranRegionBtn.setTooltip(new Tooltip(ref.randomRegion.getName()
                + "\n" + ref.randomRegion.getDescription() + "\n"
                + ref.randomRegion.getTechLevel()));
        //RANDOM REGION --> MAP
        ref.randomRegion.getMapButton().setOnAction(e -> {
            stage.setScene(ref.universeScene);
        });
        startUniverse(ref.universeScene, ref.randomRegion);
    }


    public void startUniverse(Scene universeScene, Region randomRegion) {
        var rel = new Object() {
            int a;
        };

        for (int q = 0; q < 10; q++) {
            Button regionBtn = universe.getArrButtons()[q];

            var rep = new Object() {
                Region clickedRegion;
            };

            rep.clickedRegion = universe.getRegionArr()[q];

            regionBtn.setOnAction(a -> {

                if (rep.clickedRegion.isVisited()) {
                    stage.setScene(rep.clickedRegion.getScene());
                } else {
                    Scene regionScene = new Scene(rep.clickedRegion.createGridPane());
                    rep.clickedRegion.setScene(regionScene);
                    stage.setScene(regionScene);
                }

                if (Main.getUser().getDifficultyPoints() <= 10 && (rel.a % 2 == 0)) {
                    Object npc = universe.getRandomNpcEncounters(1);
                    rel.a++;
                    if (npc instanceof Police) {
                        rep.clickedRegion =
                                startPoliceEncounter(npc, randomRegion, rep.clickedRegion);
                    } else if (npc instanceof Trader) {
                        rep.clickedRegion =
                                startTraderEncounter(npc, randomRegion, rep.clickedRegion);
                    } else {
                        rep.clickedRegion =
                                startBanditEncounter(npc, randomRegion, rep.clickedRegion);
                    }
                } else {
                    rel.a++;
                }

                rep.clickedRegion.getUpdate().setOnAction(o -> {
                    rep.clickedRegion.updateShipDropDown();
                    rep.clickedRegion.updateMoneyLabel();
                });

                rep.clickedRegion.getMarket().getBuy().setOnAction(o -> {
                    rep.clickedRegion.setBuyChoice((Good)
                            rep.clickedRegion.getGoodsAvailableDropDown()
                                    .getSelectionModel().getSelectedItem());
                    if (rep.clickedRegion.getBuyChoice().getName().equals("Gold Tinted Sword")) {
                        winnerScreen();
                    }
                    rep.clickedRegion.getMarket().buy(rep.clickedRegion.getBuyChoice());
                });

                rep.clickedRegion.getMarket().getSell().setOnAction(o -> {
                    rep.clickedRegion.setSellChoice();
                    rep.clickedRegion.getMarket().sell(rep.clickedRegion.getBuyChoice());

                });
                regionBtn.setTooltip(new Tooltip(rep.clickedRegion.getName()
                        + "\n" + rep.clickedRegion.getDescription() + "\n"
                        + rep.clickedRegion.getTechLevel()));

                //VISITED REGION INFO BECOMES VISIBLE
                rep.clickedRegion.getMapButton().setOnAction(ei -> {
                    stage.setScene(universeScene);
                });
            });
        }
    }

    public Region startPoliceEncounter(Object npc, Region a, Region b) {
        if (((Police) npc).getScene() != null) {
            stage.setScene(((Police) npc).getScene());
        } else {
            Scene scene = new Scene(((Police) npc).policeInteractPane());
            ((Police) npc).setScene(scene);
            stage.setScene(scene);
        }

        var rep = new Object() {
            Region resultRegion;
        };
        rep.resultRegion = b;

        ((Police) npc).getForfeit().setOnAction(e -> {
            ((Police) npc).playForfeit();
            control.alertMessage("POLICE ALERT", "FORFEIT IT IS :( ");
            stage.setScene(b.getScene());
        });

        ((Police) npc).getFlee().setOnAction(e -> {
            ((Police) npc).playFlee();
            if (Main.getUser().getPilot() > 5) {
                control.alertMessage("POLICE ALERT", "SUCCESSFUL FLEE :) \n "
                        + "FUEL CAPACITY DECREASED TO: "
                        + Main.getUser().getShip().getFuelCapacity());
                stage.setScene(a.getScene());
            } else if (Main.getUser().getPilot() <= 5) {
                if (Main.getUser().getShip().getHealth() > 0) {
                    control.alertMessage("POLICE ALERT", "UNSUCCESSFUL FLEE :( \n "
                            + "SHIP HEALTH DECREASED TO: "
                            + Main.getUser().getShip().getHealth());
                    stage.setScene(a.getScene());
                } else {
                    control.alertMessage("POLICE ALERT", "UNSUCCESSFUL FLEE :( ");
                    control.alertMessage("GAME ALERT", "OH NO SHIP HEALTH IS: "
                            + Main.getUser().getShip().getHealth());
                    gameOverScreen();
                }
            }
            rep.resultRegion = a;
        });

        ((Police) npc).getFight().setOnAction(e -> {
            ((Police) npc).playFight();
            if (Main.getUser().getFighter() < 5) {
                control.alertMessage("POLICE ALERT", "UNSUCCESSFUL FIGHT :( ");
            } else {
                control.alertMessage("POLICE ALERT", "SUCCESSFUL FIGHT :) ");
            }
            stage.setScene(b.getScene());
        });

        return rep.resultRegion;
    }

    public Region startTraderEncounter(Object npc, Region a, Region b) {
        if (((Trader) npc).getScene() != null) {
            stage.setScene(((Trader) npc).getScene());
        } else {
            Scene scene = new Scene(((Trader) npc).getTraderPane());
            ((Trader) npc).setScene(scene);
            stage.setScene(scene);
        }

        var rep = new Object() {
            Region resultRegion;
        };
        rep.resultRegion = b;

        ((Trader) npc).getBuy().setOnAction(e -> {
            ((Trader) npc).setBuyChoice((Good)
                    ((Trader) npc).getTraderGoodsAvailableDropDown()
                            .getSelectionModel().getSelectedItem());
            ((Trader) npc).playBuy(((Trader) npc).getBuyChoice());
            control.alertMessage("TRADER ALERT", "SUCCESSFUL PURCHASE! ITEM ADDED TO SHIP.");
            stage.setScene(b.getScene());
        });
        ((Trader) npc).getNegotiate().setOnAction(e -> {
            ((Trader) npc).playNegotiate();
            if (Main.getUser().getMerchant() < 5) {
                control.alertMessage("TRADER ALERT", "UNSUCCESSFUL NEGOTIATION :( ");
            } else {
                control.alertMessage("TRADER ALERT", "SUCCESSFUL NEGOTIATION :) ");
            }
            ((Trader) npc).getTraderPane().getChildren().remove(((Trader) npc).getNegotiate());
        });

        ((Trader) npc).getRob().setOnAction(e -> {
            ((Trader) npc).playRob();
            if (Main.getUser().getFighter() < 5) {
                if (Main.getUser().getShip().getHealth() > 0) {
                    control.alertMessage("TRADER ALERT", "UNSUCCESSFUL ROBBERY :( \n "
                        + "SHIP HEALTH DECREASED TO: "
                        + Main.getUser().getShip().getHealth());
                    stage.setScene(b.getScene());
                } else {
                    control.alertMessage("TRADER ALERT", "UNSUCCESSFUL ROBBERY :( ");
                    control.alertMessage("GAME ALERT", "OH NO SHIP HEALTH IS: "
                            + Main.getUser().getShip().getHealth());
                    gameOverScreen();
                }
            } else {
                control.alertMessage("TRADER ALERT", "SUCCESSFUL ROBBERY :) ");
                stage.setScene(b.getScene());
            }
        });

        ((Trader) npc).getIgnore().setOnAction(e -> {
            control.alertMessage("TRADER ALERT", "TRADER IGNORED");
            stage.setScene(b.getScene());
        });
        return rep.resultRegion;

    }

    public Region startBanditEncounter(Object npc, Region a, Region b) {
        if (((Bandit) npc).getScene() != null) {
            stage.setScene(((Bandit) npc).getScene());
        } else {
            Scene scene = new Scene(((Bandit) npc).banditInteractPane());
            ((Bandit) npc).setScene(scene);
            stage.setScene(scene);
        }

        var rep = new Object() {
            Region resultRegion;
        };
        rep.resultRegion = b;

        ((Bandit) npc).getPayDemand().setOnAction(e -> {
            ((Bandit) npc).playPayDemand();
            if (Main.getUser().getCreditsValue() < ((Bandit) npc).getDemand()) {
                if (Main.getUser().getShip().getItemInventory().size() == 0) {
                    if (Main.getUser().getShip().getHealth() > 0) {
                        control.alertMessage("BANDIT ALERT",
                                "NO INVENTORY SO SHIP HEALTH LOWERED TO: "
                                + Main.getUser().getShip().getHealth());
                        stage.setScene(b.getScene());
                    } else {
                        control.alertMessage("GAME ALERT", "OH NO SHIP HEALTH IS: "
                                + Main.getUser().getShip().getHealth());
                        gameOverScreen();
                    }
                } else {
                    control.alertMessage("BANDIT ALERT", "ALL INVENTORY LOST :( ");
                    stage.setScene(b.getScene());
                }
            } else {
                control.alertMessage("BANDIT ALERT", "PAID BANDIT SUCCESSFULLY :( ");
                stage.setScene(b.getScene());
            }
        });

        ((Bandit) npc).getFlee().setOnAction(e -> {
            ((Bandit) npc).playFlee();
            rep.resultRegion = a;
            if (Main.getUser().getShip().getHealth() <= 0) {
                control.alertMessage("GAME ALERT", "OH NO SHIP HEALTH IS: "
                        + Main.getUser().getShip().getHealth());
                gameOverScreen();
            } else if (Main.getUser().getPilot() > 5) {
                control.alertMessage("BANDIT ALERT", "SUCCESSFUL FLEE :) ");
                stage.setScene(a.getScene());
            } else {
                control.alertMessage("BANDIT ALERT", "UNSUCCESSFUL FLEE :( ");
                stage.setScene(a.getScene());
            }
        });

        ((Bandit) npc).getFight().setOnAction(e -> {
            ((Bandit) npc).playFight();

            if (Main.getUser().getFighter() < 5) {
                if (Main.getUser().getShip().getHealth() > 0) {
                    control.alertMessage("BANDIT ALERT", "UNSUCCESSFUL FIGHT :( ");
                    stage.setScene(b.getScene());
                } else {
                    control.alertMessage("BANDIT ALERT", "UNSUCCESSFUL FIGHT :( ");
                    control.alertMessage("GAME ALERT", "OH NO SHIP HEALTH IS: "
                            + Main.getUser().getShip().getHealth());
                    gameOverScreen();
                }
            } else {
                control.alertMessage("BANDIT ALERT", "SUCCESSFUL FIGHT :) ");
                stage.setScene(b.getScene());
            }

        });

        return rep.resultRegion;
    }

    public void gameOverScreen() {

        GridPane gameOverPane = new GridPane();
        gameOverPane.setAlignment(Pos.CENTER);
        gameOverPane.setHgap(5);
        gameOverPane.setVgap(10);
        gameOverPane.setPrefSize(400, 400);

        Label gameOverLabel = new Label("GAME OVER");
        gameOverPane.add(gameOverLabel, 0, 0);
        Label shipHealthLabel = new Label("Ship Health: "
                + Main.getUser().getShip().getHealth());
        gameOverPane.add(shipHealthLabel, 1, 0);

        Button endCredits = new Button("EXIT");
        gameOverPane.add(endCredits, 0, 3);

        Scene gameOver = new Scene(gameOverPane);
        stage.setScene(gameOver);

        endCredits.setOnAction(e -> {
            endCreditsScreen();
        });
    }

    public void winnerScreen() {

        GridPane winnerPane = new GridPane();
        winnerPane.setAlignment(Pos.CENTER);
        winnerPane.setHgap(5);
        winnerPane.setVgap(10);
        winnerPane.setPrefSize(450, 450);

        Label winnerLabel = new Label(" CONGRATS YOU WIN!");
        winnerPane.add(winnerLabel, 0, 0);
        Label shipHealthLabel =
                new Label("You successfully bought the MYSTERY ITEM: GOLD TINTED SWORD!");
        winnerPane.add(shipHealthLabel, 0, 2);

        Button endCredits = new Button("EXIT");
        winnerPane.add(endCredits, 0, 4);

        Scene wonGame = new Scene(winnerPane);
        stage.setScene(wonGame);

        endCredits.setOnAction(e -> {
            endCreditsScreen();
        });
    }

    public void endCreditsScreen() {

        GridPane endCreditsPane = new GridPane();
        endCreditsPane.setAlignment(Pos.CENTER);
        endCreditsPane.setHgap(5);
        endCreditsPane.setVgap(10);
        endCreditsPane.setPrefSize(500, 300);

        Label endCreditsLabel = new Label("Thank you for playing! ~ Khushi, Saloni, Medha, Shruti");
        endCreditsPane.add(endCreditsLabel, 0, 0);
        Label classInfo = new Label("SPRING 2020 CS 2340");
        endCreditsPane.add(classInfo, 1, 0);

        Button startOver = new Button("NEW GAME");
        endCreditsPane.add(startOver, 0, 3);

        Scene endCredits = new Scene(endCreditsPane);
        stage.setScene(endCredits);

        startOver.setOnAction(e -> {
            stage.hide();
            setStage(new Stage());
            try {
                start(stage);
            } catch (Exception ex) {
                System.out.println("nope");
            }
        });
    }


    /**
     * Main method that lauches the code
     *
     * @param args main paramter
     */
    public static void main(String[] args) {
        launch(args);
    }


}
