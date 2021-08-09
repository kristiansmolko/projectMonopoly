import extra.Chance;
import extra.Player;
import graphics.Board;
import graphics.PlayerWindow;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import static graphics.Graphics.*;
import static extra.Util.*;

public class Game {
    private static final Chance[] chances = createChances();
    private static int turn = 1;
    private static int chanceNum = 0;
    private static int numOfPlayers;
    private static final Rectangle rect = makeDice();
    private static final Label win = new Label();
    private static Group center;
    private static ArrayList<String> tiles = new ArrayList<>();
    private static final int[] values = new int[] {200, 60, 60, 100, 80, 100, 100, 0, 120, 0, 140, 140, 160, 0, 180, 200,
            0, 220, 100, 220, 240, 260, 260, 0, 280, 0, 300, 300, 320, 0, 350, 400};
    private static MediaPlayer wood, villager, chestOpen, nether, ironHoe, eat, arrow, bow, water, ironPick, ironSword, shield, ironArmor,
            portalEffect, enchant, brewing, shulker, start, fanfare, loser;

    private static final String P1WON = "Player 1 has WON this game!";
    private static final String P2WON = "Player 2 has WON this game!";
    private static final String P3WON = "Player 3 has WON this game!";
    private static final String P4WON = "Player 4 has WON this game!";
    private static final String PRISON = "prison";
    private static final String OWNED = "You own this tile.";
    private static final String LOST = " has lost!\n";

    public static ArrayList<String> makeTiles(){
        var CHANCE = "CHANCE";
        var tiles = new String[] {"START", "Wood", "Stick", "TAX", "Crafting Table", "Stone Pickaxe",
                "Cobblestone", CHANCE, "Iron Ore", "JAIL", "Iron Hoe", "Wheat", "Carrot", CHANCE, "Arrow",
                "Bow", "FREE", "Iron Pickaxe", "TAX", "Gold Bar", "Redstone", "Iron Sword", "Shield", CHANCE,
                "Iron Armor", "PORTAL", "Enchanting", "Brew Stand", "Shulker Box", CHANCE, "Diamond", "Emerald"};
        return new ArrayList<>(Arrays.asList(tiles));
    }

    public static TranslateTransition moveBack(Rectangle rect, int x){
        var back = new TranslateTransition();
        back.setByX(-x);
        back.setDuration(Duration.millis(10));
        back.setNode(rect);
        return back;
    }

    public static Timeline expandChance(Rectangle rectangle){
        rectangle.setVisible(true);
        var widthValue = new KeyValue(rectangle.widthProperty(), rectangle.getWidth() + 200);
        var heightValue = new KeyValue(rectangle.heightProperty(), rectangle.getHeight() + 100);
        var frame = new KeyFrame(Duration.seconds(3), widthValue, heightValue);
        return new Timeline(frame);
    }

    public static FadeTransition fadeChance(Rectangle rectangle){
        var fade = new FadeTransition();
        fade.setNode(rectangle);
        fade.setToValue(0);
        return fade;
    }

    public static FadeTransition fadeInChance(Rectangle rectangle){
        var fadeIn = new FadeTransition();
        fadeIn.setNode(rectangle);
        fadeIn.setToValue(100);
        return fadeIn;
    }

    public static int randomNum(){
        var rnd = new Random();
        return (rnd.nextInt(6)+1);
    }

    public static ParallelTransition parallel(Rectangle rect, int x){
        var translate = new TranslateTransition(Duration.millis(500));
        translate.setByX(x);
        translate.setDuration(Duration.millis(1000));
        var rotate = new RotateTransition();
        rotate.setByAngle(360);
        rotate.setCycleCount(2);
        return new ParallelTransition(rect, translate, rotate);
    }

    private static void makeSounds(){
        var woodSound = new Media(new File("resources/wood1.mp3").toURI().toString());
        wood = new MediaPlayer(woodSound);
        var villagerSound = new Media(new File("resources/idle2.mp3").toURI().toString());
        villager = new MediaPlayer(villagerSound);
        var chanceSound = new Media(new File("resources/open.mp3").toURI().toString());
        chestOpen = new MediaPlayer(chanceSound);
        var netherSound = new Media(new File("resources/moan2.mp3").toURI().toString());
        nether = new MediaPlayer(netherSound);
        var ironHoeSound = new Media(new File("resources/till3.mp3").toURI().toString());
        ironHoe = new MediaPlayer(ironHoeSound);
        var eatSound = new Media(new File("resources/eat1.mp3").toURI().toString());
        eat = new MediaPlayer(eatSound);
        var arrowSound = new Media(new File("resources/bowhit4.mp3").toURI().toString());
        arrow = new MediaPlayer(arrowSound);
        var bowSound = new Media(new File("resources/bow.mp3").toURI().toString());
        bow = new MediaPlayer(bowSound);
        var waterSound = new Media(new File("resources/splash2.mp3").toURI().toString());
        water = new MediaPlayer(waterSound);
        var equipmentSound = new Media(new File("resources/anvil_use.mp3").toURI().toString());
        ironPick = new MediaPlayer(equipmentSound);
        var swordSound = new Media(new File("resources/anvil_use.mp3").toURI().toString());
        ironSword = new MediaPlayer(swordSound);
        var shieldSound = new Media(new File("resources/block5.mp3").toURI().toString());
        shield = new MediaPlayer(shieldSound);
        var armorSound = new Media(new File("resources/equip_iron2.mp3").toURI().toString());
        ironArmor = new MediaPlayer(armorSound);
        var portalSound = new Media(new File("resources/travel.mp3").toURI().toString());
        portalEffect = new MediaPlayer(portalSound);
        var enchanting = new Media(new File("resources/levelup.mp3").toURI().toString());
        enchant = new MediaPlayer(enchanting);
        var brewingSound = new Media(new File("resources/drink.mp3").toURI().toString());
        brewing = new MediaPlayer(brewingSound);
        var shulkerOpen = new Media(new File("resources/open_shulker.mp3").toURI().toString());
        shulker = new MediaPlayer(shulkerOpen);
        var startSound = new Media(new File("resources/grass6.mp3").toURI().toString());
        start = new MediaPlayer(startSound);
        var fanfareSound = new Media(new File("resources/fanfare.mp3").toURI().toString());
        fanfare = new MediaPlayer(fanfareSound);
        fanfare.setStartTime(Duration.millis(37000));
        fanfare.setStopTime(Duration.millis(55000));
        var loseSound = new Media(new File("resources/lose.mp3").toURI().toString());
        loser = new MediaPlayer(loseSound);
    }

    private static void musicStop(){
        PlayerWindow.coins.stop();
        wood.stop();
        villager.stop();
        chestOpen.stop();
        nether.stop();
        ironHoe.stop();
        eat.stop();
        bow.stop();
        arrow.stop();
        water.stop();
        ironPick.stop();
        ironSword.stop();
        shield.stop();
        ironArmor.stop();
        portalEffect.stop();
        enchant.stop();
        brewing.stop();
        shulker.stop();
        start.stop();
        loser.stop();
    }

    private static void nextTurn(){
        if (turn == numOfPlayers)
            turn = 1;
        else
            turn -= -1;
    }

    private static final TranslateTransition translateFigure = new TranslateTransition();
    private static final TranslateTransition translateFigure2 = new TranslateTransition();
    private static final TranslateTransition transY = new TranslateTransition();
    private static final TranslateTransition transY2 = new TranslateTransition();
    private static final TranslateTransition portal = new TranslateTransition();
    private static final TranslateTransition chanceMove = new TranslateTransition();
    private static final PauseTransition pauseMove = new PauseTransition();
    private static final FadeTransition fade = new FadeTransition();

    private static void setMovesForFigure(ImageView figure){
        translateFigure.setNode(figure);
        transY.setNode(figure);
        portal.setNode(figure);
        chanceMove.setNode(figure);
        fade.setNode(figure);
    }

    public static BorderPane createGame(Map<String, String> figures){
        var root = new BorderPane();
        var border = new BorderPane();
        center = new Group();
        var buy = new BorderPane();
        var right = new BorderPane();
        var playerWindows = new GridPane();
        var x = 250; //move
        //moves
        TranslateTransition back = moveBack(rect, x);
        ParallelTransition par = parallel(rect, x);

        //creating sounds
        makeSounds();
        //dots in dice
        Circle c1 = get1(rect, x);
        Circle[] set2 = get2(rect, x);
        Circle[] set3 = get3(rect, x);
        Circle[] set4 = get4(rect, x);
        Circle[] set5 = get5(rect, x);
        Circle[] set6 = get6(rect, x);

        //set new players
        var player1 = new Player(1);
        var player2 = new Player(2);
        var player3 = new Player(3);
        var player4 = new Player(4);
        numOfPlayers = figures.size();
        //add figures to players
        switch (numOfPlayers) {
            case 3 -> {
                setPlayer(player1, figures, 1);
                setPlayer(player2, figures, 2);
                setPlayer(player3, figures, 3);
                }
            case 4 -> {
                setPlayer(player1, figures, 1);
                setPlayer(player2, figures, 2);
                setPlayer(player3, figures, 3);
                setPlayer(player4, figures, 4);
            }
            default -> {
                setPlayer(player1, figures, 1);
                setPlayer(player2, figures, 2);
                }
        }



        //figures
        ImageView figure1 = makeFigure(player1.getFigure());
        ImageView figure2 = makeFigure(player2.getFigure());
        ImageView figure3 = makeFigure(player3.getFigure());
        ImageView figure4 = makeFigure(player4.getFigure());
        figure1.setTranslateX(920); figure1.setTranslateY(700);
        figure2.setTranslateX(925); figure2.setTranslateY(705);
        figure3.setTranslateX(915); figure3.setTranslateY(700);
        figure4.setTranslateX(920); figure4.setTranslateY(705);

        //player windows
        var w1 = new PlayerWindow();
        var w2 = new PlayerWindow();
        var w3 = new PlayerWindow();
        var w4 = new PlayerWindow();

        var playerList = new Player[numOfPlayers];
        playerList[0] = player1;
        playerList[1] = player2;
        if (player3.isPlaying())
            playerList[2] = player3;
        if (player4.isPlaying())
            playerList[3] = player4;

        var P1W = w1.makeWindow(player1);
        var P2W = w2.makeWindow(player2);
        var P3W = w3.makeWindow(player3);
        var P4W = w4.makeWindow(player4);
        //winner label
        win.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 50));
        win.setTextFill(Color.RED);
        win.setText(win.getText().toUpperCase());
        win.setTranslateX(140); win.setTranslateY(200);

        //chance card
        var chanceCard = chanceCard();
        var chanceSet = new StackPane();
        var chanceText = new Label();
        chanceText.setVisible(false);
        chanceText.setFont(Font.font("Times New Roman", 24));
        chanceSet.setTranslateX(450);
        chanceSet.setTranslateY(400);
        chanceSet.getChildren().addAll(chanceCard, chanceText);

        //game over
        var firework = new ImageView(new Image("fireworks.png"));
        firework.setTranslateX(150); firework.setTranslateY(100);
        firework.setFitHeight(700); firework.setFitWidth(700);

        //mouse click action
        EventHandler<MouseEvent> mouse = mouseEvent -> {
            if (mouseEvent.getSource() == rect){
                par.play();
            }
        };
        //when dice throw is completed
        par.setOnFinished(actionEvent -> {
            int finalRn = randomNum();
            switch (finalRn) {
                case 1 -> root.getChildren().add(c1);
                case 2 -> fillDice(root, set2);
                case 3 -> fillDice(root, set3);
                case 4 -> fillDice(root, set4);
                case 5 -> fillDice(root, set5);
                case 6 -> fillDice(root, set6);
            }
            //move
            int move = finalRn * 100;
            //values of tiles
            tiles = makeTiles();


            translateFigure.setDuration(Duration.millis(2000));
            translateFigure2.setDuration(Duration.millis(2000));
            transY.setDuration(Duration.millis(2000));
            transY2.setDuration(Duration.millis(2000));
            portal.setDuration(Duration.millis(10));
            pauseMove.setDuration(Duration.millis(5000));
            fade.setToValue(0);
            fade.setDuration(Duration.millis(5000));
            fade.stop();
            //console displaying player turn and action
            var console = new TextArea();
            console.setEditable(false);
            console.setMaxHeight(100);
            console.setMaxWidth(200);
            buy.setTop(null);
            buy.setMaxSize(200,200);
            int repeat;
            //this in case, that last player is in prison
            //so it can repeat the loop
            for (repeat = 0; repeat < 2; repeat++) {
                for (Player player : playerList) {
                    repeat = 0;
                    //case when player is defeated
                    if (playerDefeated(player)) break;
                    //case when player is in prison
                    if (player.isInPrison() && turn == player.getPos()) {
                        player.addPrison();
                        updatePlayers(w1, w2, w3, w4, player);
                        nextTurn();
                        if ((player.getPos() == 2 && numOfPlayers == 2) || (player.getPos() == 3 && numOfPlayers == 3)
                                || (player.getPos() == 4 && numOfPlayers == 4))
                            break;
                    }
                    if (turn == player.getPos()) {
                        switch (turn) {
                            case 1 -> setMovesForFigure(figure1);
                            case 2 -> setMovesForFigure(figure2);
                            case 3 -> setMovesForFigure(figure3);
                            case 4 -> setMovesForFigure(figure4);
                        }
                        translateFigure.setByX(0);
                        translateFigure.setByY(0);
                        transY.setByY(0);
                        transY.setByX(0);
                        consolePlayer(console, player, " turn.\n");
                        double nextTile = player.getTile() + finalRn;
                        double rest;

                        translateFigure.setOnFinished(actionEvent2 -> {
                            back.play();
                            root.getChildren().removeAll(set2);
                            root.getChildren().removeAll(set3);
                            root.getChildren().removeAll(set4);
                            root.getChildren().removeAll(set5);
                            root.getChildren().removeAll(set6);
                            root.getChildren().removeAll(c1);
                            switch ((int) nextTile) {
                                case 1 -> {
                                    wood.setCycleCount(4);
                                    wood.play();
                                }
                                case 3, 18 -> villager.play();
                                case 7, 23, 13, 29 -> chestOpen.play();
                                case 9 -> nether.play();
                                case 17 -> ironPick.play();
                                case 21 -> ironSword.play();
                                case 22 -> shield.play();
                                case 24 -> ironArmor.play();
                                case 10 -> ironHoe.play();
                                case 12 -> {
                                    eat.setCycleCount(2);
                                    eat.play();
                                }
                                case 14 -> arrow.play();
                                case 15 -> bow.play();
                                case 16 -> water.play();
                                case 26 -> enchant.play();
                                case 27 -> brewing.play();
                                case 28 -> shulker.play();
                                case 0 -> start.play();
                            }
                            switch (tiles.get(player.getTile())) {
                                case "CHANCE":
                                    chanceCard.setWidth(100);
                                    chanceCard.setHeight(50);
                                    var fadeIn = fadeInChance(chanceCard);
                                    fadeIn.play();
                                    chanceCard.setVisible(false);
                                    var expandChance = expandChance(chanceCard);
                                    fadeIn.setOnFinished(e -> expandChance.play());
                                    var pauseChance = new PauseTransition(Duration.millis(4000));
                                    var fadeChance = fadeChance(chanceCard);
                                    expandChance.setOnFinished(expand -> {
                                        chanceText.setText(chances[chanceNum].getText());
                                        chanceText.setVisible(true);
                                        pauseChance.play();
                                    });
                                    pauseChance.setOnFinished(pause -> fadeChance.play());
                                    fadeChance.setOnFinished(fade -> {
                                        chanceText.setVisible(false);
                                        var text = new Label();
                                        text.setText(chances[chanceNum].getText());
                                        text.setTranslateX(20);
                                        buy.setTop(text);
                                        switch (chances[chanceNum].getAction()) {
                                            case "move" -> {
                                                if (player.getTile() < 9 && player.getTile() > 0) {
                                                    chanceMove.setToX(920 - (player.getTile() + chances[chanceNum].getValue()) * 100);
                                                    chanceMove.setToY(705);
                                                } else if (player.getTile() < 25 && player.getTile() > 16) {
                                                    chanceMove.setToX((player.getTile() + chances[chanceNum].getValue() - 16) * 100 + 20);
                                                    chanceMove.setToY(5);
                                                }
                                                if (player.getTile() < 16 && player.getTile() > 9) {
                                                    chanceMove.setToY(705 - (player.getTile() + chances[chanceNum].getValue() - 9) * 100);
                                                    chanceMove.setToX(20);
                                                } else if (player.getTile() < 32 && player.getTile() > 25) {
                                                    chanceMove.setToY(100 * (player.getTile() + chances[chanceNum].getValue() - 25));
                                                    chanceMove.setToX(920);
                                                }
                                                player.addTile(chances[chanceNum].getValue());
                                                chanceMove.play();
                                                chanceMove.setOnFinished(e -> {
                                                    if ((player1.getOwned().contains(tiles.get(player.getTile())))) {
                                                        if (player.getPos() == 1)
                                                            console.appendText(OWNED);
                                                        if (!player1.isInPrison()) {
                                                            payPlayer(player1, w1, console, player);
                                                            loseMoneyAndCheckIfLose(player1, player2, player3, player4, w1, w2, w3, w4, console, player);
                                                        } else if (player1.isInPrison()) console.appendText("Psst! Player 1 is in prison.");
                                                    } else if ((player2.getOwned().contains(tiles.get(player.getTile())))) {
                                                        if (player.getPos() == 2)
                                                            console.appendText(OWNED);
                                                        if (!player2.isInPrison()) {
                                                            payPlayer(player2, w2, console, player);
                                                            loseMoneyAndCheckIfLose(player1, player2, player3, player4, w1, w2, w3, w4, console, player);
                                                        } else if (player2.isInPrison()) console.appendText("Psst! Player 2 is in prison.");
                                                    } else if ((player3.getOwned().contains(tiles.get(player.getTile())))) {
                                                        if (player.getPos() == 3)
                                                            console.appendText(OWNED);
                                                        if (!player3.isInPrison()) {
                                                            payPlayer(player3, w3, console, player);
                                                            loseMoneyAndCheckIfLose(player1, player2, player3, player4, w1, w2, w3, w4, console, player);
                                                        } else if (player3.isInPrison()) console.appendText("Psst! Player 3 is in prison.");
                                                    } else if ((player4.getOwned().contains(tiles.get(player.getTile())))) {
                                                        if (player.getPos() == 4)
                                                            console.appendText(OWNED);
                                                        if (!player4.isInPrison()) {
                                                            payPlayer(player4, w4, console, player);
                                                            loseMoneyAndCheckIfLose(player1, player2, player3, player4, w1, w2, w3, w4, console, player);
                                                        } else if (player4.isInPrison()) console.appendText("Psst! Player 4 is in prison.");
                                                    } else {
                                                        var text1 = new Label("Do you want to buy \n" + tiles.get(player.getTile()) + " for " + values[player.getTile()] + "?");
                                                        text1.setTranslateX(20);
                                                        var yes1 = new Button("Buy");
                                                        yes1.setTranslateY(30);
                                                        yes1.setTranslateX(70);
                                                        yes1.setPrefSize(60, 40);
                                                        yes1.setOnAction(actionEvent12 -> {
                                                            buyTile(w1, w2, w3, w4, console, player);
                                                            if (player.getAccount() <= 0) {
                                                                playerLose(player1, player2, player3, player4, w1, w2, w3, w4, console, player);
                                                            }
                                                            buy.setTop(null);
                                                        });
                                                        var buySetup = new GridPane();
                                                        buySetup.setMaxSize(200, 70);
                                                        buySetup.addRow(0, text1);
                                                        buySetup.addRow(1, yes1);
                                                        buy.setTop(buySetup);
                                                    }
                                                }); //end of chM.sOF
                                            }
                                            case "moveTo" -> {
                                                if (chances[chanceNum].getValue() == 0) {
                                                    chanceMove.setToX(920);
                                                    chanceMove.setToY(705);
                                                    player.addTile(-player.getTile());
                                                    player.addToAccount(200);
                                                    chanceMove.play();
                                                    chanceMove.setOnFinished(e1 -> {
                                                        switch (player.getPos()) {
                                                            case 1 -> w1.addAccount(player);
                                                            case 2 -> w2.addAccount(player);
                                                            case 3 -> w3.addAccount(player);
                                                            case 4 -> w4.addAccount(player);
                                                        }
                                                    });
                                                } else if (chances[chanceNum].getValue() == 9) {
                                                    if (!player.getExtra().contains(PRISON)) {
                                                        chanceMove.setToX(20);
                                                        chanceMove.setToY(705);
                                                        if (player.getTile() < 9)
                                                            player.addTile(9 - player.getTile());
                                                        else
                                                            player.addTile(-(player.getTile() - 9));
                                                        chanceMove.play();
                                                        chanceMove.setOnFinished(e -> {
                                                            outOfPrison(player1);
                                                            outOfPrison(player2);
                                                            outOfPrison(player3);
                                                            outOfPrison(player4);
                                                            player.toPrison();
                                                            nether.play();
                                                            w1.update(player1);
                                                            w2.update(player2);
                                                            w3.update(player3);
                                                            w4.update(player4);
                                                        });
                                                    } else {
                                                        player.getExtra().remove(PRISON);
                                                        console.appendText("You used your prisonFree chance.");
                                                        updatePlayers(w1, w2, w3, w4, player);
                                                    }
                                                } else if (chances[chanceNum].getValue() == 16) {
                                                    chanceMove.setToX(20);
                                                    chanceMove.setToY(5);
                                                    if (player.getTile() < 16)
                                                        player.addTile(16 - player.getTile());
                                                    else
                                                        player.addTile(-(player.getTile() - 16));
                                                    chanceMove.play();
                                                    chanceMove.setOnFinished(e -> water.play());

                                                } else if (chances[chanceNum].getValue() == 25) {
                                                    chanceMove.setToX(920);
                                                    chanceMove.setToY(5);
                                                    if (player.getTile() < 9)
                                                        player.addTile(9 - player.getTile());
                                                    else
                                                        player.addTile(-(player.getTile() - 9));
                                                    chanceMove.play();
                                                    chanceMove.setOnFinished(e -> {
                                                        portal.setToX(20);
                                                        portal.setToY(705);
                                                        pauseMove.play();
                                                        portalEffect.play();
                                                    });
                                                    pauseMove.setOnFinished(e -> portal.play());
                                                    if (!player.getExtra().contains(PRISON)) {
                                                        outOfPrison(player1);
                                                        outOfPrison(player2);
                                                        outOfPrison(player3);
                                                        outOfPrison(player4);
                                                        player.toPrison();
                                                    }
                                                    portal.setOnFinished(e -> {
                                                        if (player.getExtra().contains(PRISON)){
                                                            player.getExtra().remove(PRISON);
                                                            console.appendText("You used your prisonFree chance.");
                                                        }
                                                        updatePlayers(w1, w2, w3, w4, player);
                                                    });
                                                }
                                            }
                                            case "money" -> {
                                                player.addToAccount(chances[chanceNum].getValue());
                                                if (chances[chanceNum].getValue() > 0)
                                                    switch (player.getPos()) {
                                                        case 1 -> w1.addAccount(player);
                                                        case 2 -> w2.addAccount(player);
                                                        case 3 -> w3.addAccount(player);
                                                        case 4 -> w4.addAccount(player);
                                                    }
                                                else if (chances[chanceNum].getValue() < 0) {
                                                    loseMoneyAndCheckIfLose(player1, player2, player3, player4, w1, w2, w3, w4, console, player);
                                                }
                                            }
                                            case PRISON -> {
                                                player.addExtra(PRISON);
                                                updatePlayers(w1, w2, w3, w4, player);
                                            }
                                            case "tax" -> {
                                                player.addExtra("tax");
                                                updatePlayers(w1, w2, w3, w4, player);
                                            }
                                        }
                                        chanceNum++;
                                        if (chanceNum == 12)
                                            chanceNum = 0;
                                        fadeChance.stop();
                                    });
                                    break;
                                case "TAX":
                                    if (!player.getExtra().contains("tax")) {
                                        player.takeFromAccount(values[player.getTile()]);
                                        console.appendText("\nPlayer " + player.getPos() + " paid tax: " + values[player.getTile()] + ".\n");
                                        loseMoneyAndCheckIfLose(player1, player2, player3, player4, w1, w2, w3, w4, console, player);
                                    } else {
                                        player.getExtra().remove("tax");
                                        console.appendText("You used your taxFree chance.");
                                        updatePlayers(w1, w2, w3, w4, player);
                                    }
                                    break;
                                case "JAIL":
                                    console.appendText("It's hot here. Uhh...");
                                    break;
                                case "FREE":
                                    console.appendText("Enjoy your stay!");
                                    break;
                                case "START":
                                    console.appendText("You got 200.");
                                    break;
                                default:
                                    if ((player1.getOwned().contains(tiles.get(player.getTile())))) {
                                        if (player.getPos() == 1)
                                            console.appendText(OWNED);
                                        if (!player1.isInPrison()) {
                                            payPlayer(player1, w1, console, player);
                                            loseMoneyAndCheckIfLose(player1, player2, player3, player4, w1, w2, w3, w4, console, player);
                                        } else if (player1.isInPrison()) console.appendText("Psst! Player 1 is in prison.");
                                    } else if ((player2.getOwned().contains(tiles.get(player.getTile())))) {
                                        if (player.getPos() == 2)
                                            console.appendText(OWNED);
                                        if (!player2.isInPrison()) {
                                            payPlayer(player2, w2, console, player);
                                            loseMoneyAndCheckIfLose(player1, player2, player3, player4, w1, w2, w3, w4, console, player);
                                        } else if (player2.isInPrison()) console.appendText("Psst! Player 2 is in prison.");
                                    } else if ((player3.getOwned().contains(tiles.get(player.getTile())))) {
                                        if (player.getPos() == 3)
                                            console.appendText(OWNED);
                                        if (!player3.isInPrison()) {
                                            payPlayer(player3, w3, console, player);
                                            loseMoneyAndCheckIfLose(player1, player2, player3, player4, w1, w2, w3, w4, console, player);
                                        } else if (player3.isInPrison()) console.appendText("Psst! Player 3 is in prison.");
                                    } else if ((player4.getOwned().contains(tiles.get(player.getTile())))) {
                                        if (player.getPos() == 4)
                                            console.appendText(OWNED);
                                        if (!player4.isInPrison()) {
                                            payPlayer(player4, w4, console, player);
                                            loseMoneyAndCheckIfLose(player1, player2, player3, player4, w1, w2, w3, w4, console, player);
                                        } else if (player4.isInPrison()) console.appendText("Psst! Player 4 is in prison.");
                                    } else {
                                        var text = new Label("Do you want to buy \n" + tiles.get(player.getTile()) + " for " + values[player.getTile()] + "?");
                                        text.setTranslateX(20);
                                        var yes = new Button("Buy");
                                        yes.setTranslateY(30);
                                        yes.setTranslateX(70);
                                        yes.setPrefSize(60, 40);
                                        yes.setOnAction(actionEvent12 -> {
                                            buyTile(w1, w2, w3, w4, console, player);
                                            if (player.getAccount() <= 0) {
                                                playerLose(player1, player2, player3, player4, w1, w2, w3, w4, console, player);
                                            }
                                            buy.setTop(null);
                                        });
                                        var buySetup = new GridPane();
                                        buySetup.addRow(0, text);
                                        buySetup.addRow(1, yes);
                                        buy.setTop(buySetup);
                                    }
                                    break;
                            }
                            actionEvent.consume();
                            actionEvent2.consume();
                        });

                        transY.setOnFinished(translateFigure.getOnFinished());

                        if (player.getTile() >= 0 && player.getTile() < 9) {
                            if (nextTile > 9) {
                                rest = ((nextTile - 9) * 100);
                                translateFigure.setByX(-(move - rest));
                                transY.setByY(-((nextTile - 9) * 100));
                                translateFigure.play();
                                translateFigure.setOnFinished(actionEvent13 -> transY.play());
                                actionEvent.consume();
                            } else {
                                translateFigure.setByX(-move);
                                translateFigure.play();
                            }
                        }
                        if (player.getTile() >= 9 && player.getTile() < 16) {
                            if (nextTile > 16) {
                                rest = ((nextTile - 16) * 100);
                                translateFigure.setByY(-(move - ((nextTile - 16) * 100)));
                                transY.setByX(rest);
                                translateFigure.play();
                                translateFigure.setOnFinished(actionEvent13 -> transY.play());
                                actionEvent.consume();
                            } else {
                                transY.setByY(-move);
                                transY.play();
                            }
                        }
                        if (player.getTile() >= 16 && player.getTile() < 25) {
                            if (nextTile > 25) {
                                rest = ((nextTile - 25) * 100);
                                translateFigure.setByX(move - ((nextTile - 25) * 100));
                                transY.setByY(rest);
                                translateFigure.play();
                                translateFigure.setOnFinished(actionEvent15 -> transY.play());
                            } else {
                                translateFigure.setByX(move);
                                translateFigure.play();
                                if (nextTile == 25) {
                                    translateFigure.setOnFinished(actionEvent14 -> {
                                        portal.setToX(20);
                                        portal.setToY(705);
                                        pauseMove.play();
                                        pauseMove.setOnFinished(e -> portal.play());
                                        player.addTile(-16);
                                        portalEffect.play();
                                        if (!player.getExtra().contains(PRISON)) {
                                            outOfPrison(player1);
                                            outOfPrison(player2);
                                            outOfPrison(player3);
                                            outOfPrison(player4);
                                            player.toPrison();
                                        }
                                        back.play();
                                        root.getChildren().removeAll(set2);
                                        root.getChildren().removeAll(set3);
                                        root.getChildren().removeAll(set4);
                                        root.getChildren().removeAll(set5);
                                        root.getChildren().removeAll(set6);
                                        root.getChildren().removeAll(c1);
                                        portal.setOnFinished(e1 -> {
                                            if (player.getExtra().contains(PRISON)){
                                                player.getExtra().remove(PRISON);
                                                console.appendText("You used your prisonFree chance.");
                                            }
                                            updatePlayers(w1, w2, w3, w4, player);
                                        });
                                    });
                                }
                            }
                        }
                        if (player.getTile() >= 25 && player.getTile() < 32) {
                            if (nextTile > 32) {
                                rest = ((nextTile - 32) * 100);
                                translateFigure.setByY(move - ((nextTile - 32) * 100));
                                transY.setByX(-rest);
                                translateFigure.play();
                                translateFigure.setOnFinished(actionEvent1 -> transY.play());
                            } else {
                                transY.setByY(move);
                                transY.play();
                            }
                        }
                        player.addTile(finalRn);
                        if (player.getTile() >= 32) {
                            player.addToAccount(200);
                            console.appendText("Player " + player.getPos() + " has crossed Start.\n");
                            player.addTile(-32);
                            switch (player.getPos()) {
                                case 1 -> w1.addAccount(player);
                                case 2 -> w2.addAccount(player);
                                case 3 -> w3.addAccount(player);
                                case 4 -> w4.addAccount(player);
                            }
                        }
                        actionEvent.consume();
                        buy.setCenter(console);
                    }//end if turn
                    repeat = 2;
                }//end for player
            }//end for repeat

            musicStop();
            console.setTranslateY(150);
            nextTurn(); //next player
        });

        portal.setOnFinished(actionEvent -> {portal.stop(); actionEvent.consume();});

        //actions
        rect.addEventHandler(MouseEvent.MOUSE_CLICKED, mouse);
        root.getChildren().addAll(rect);

        //board
        var graphics = new Board();
        var board = graphics.makeBoard();

        //right side
        right.setTop(buy);
        right.setCenter(playerWindows);
        playerWindows.addRow(0, P1W);
        playerWindows.addRow(1, P2W);
        playerWindows.setVgap(20);
        playerWindows.setTranslateY(200);

        //layout
        //center of game = board and figure
        // + later thing
        center.getChildren().addAll(board);
        if (player4.isPlaying()) {
            center.getChildren().add(figure4);
            playerWindows.addRow(3, P4W);
        }
        if (player3.isPlaying()) {
            center.getChildren().add(figure3);
            playerWindows.addRow(2, P3W);
        }
        center.getChildren().add(figure2);
        center.getChildren().add(figure1);
        center.getChildren().add(chanceSet);
        center.getChildren().add(root);

        //game setup
        border.setCenter(center);
        border.setRight(right);
        return border;
    }

    private static void loseMoneyAndCheckIfLose(Player player1, Player player2, Player player3, Player player4, PlayerWindow w1, PlayerWindow w2, PlayerWindow w3, PlayerWindow w4, TextArea console, Player player) {
        loseMoney(w1, w2, w3, w4, player);
        if (player.getAccount() <= 0)
            playerLose(player1, player2, player3, player4, w1, w2, w3, w4, console, player);
    }

    private static void playerLose(Player player1, Player player2, Player player3, Player player4, PlayerWindow w1, PlayerWindow w2, PlayerWindow w3, PlayerWindow w4, TextArea console, Player player) {
        player.lost();
        consolePlayer(console, player, LOST);
        updatePlayers(w1, w2, w3, w4, player);
        findWinner(player1, player2, player3, player4);
    }

    private static boolean playerDefeated(Player player) {
        if (player.isDefeated() && turn == player.getPos()) {
            nextTurn();
            return (player.getPos() == 2 && numOfPlayers == 2) || (player.getPos() == 3 && numOfPlayers == 3)
                    || (player.getPos() == 4 && numOfPlayers == 4);
        }
        return false;
    }

    private static void consolePlayer(TextArea console, Player player, String s) {
        console.setText("Player " + player.getPos() + s);
    }

    private static void loseMoney(PlayerWindow w1, PlayerWindow w2, PlayerWindow w3, PlayerWindow w4, Player player) {
        switch (player.getPos()) {
            case 1 -> w1.removeAccount(player);
            case 2 -> w2.removeAccount(player);
            case 3 -> w3.removeAccount(player);
            case 4 -> w4.removeAccount(player);
        }
    }

    private static void updatePlayers(PlayerWindow w1, PlayerWindow w2, PlayerWindow w3, PlayerWindow w4, Player player) {
        switch (player.getPos()) {
            case 1 -> w1.update(player);
            case 2 -> w2.update(player);
            case 3 -> w3.update(player);
            case 4 -> w4.update(player);
        }
    }

    private static void buyTile(PlayerWindow w1, PlayerWindow w2, PlayerWindow w3, PlayerWindow w4, TextArea console, Player player) {
        player.addOwned(tiles.get(player.getTile()));
        player.takeFromAccount(values[player.getTile()]);
        console.appendText("\nPlayer " + player.getPos() + " bought:\n" + tiles.get(player.getTile()));
        switch (player.getPos()) {
            case 1 -> {
                w1.update(player);
                w1.removeAccount(player);
            }
            case 2 -> {
                w2.update(player);
                w2.removeAccount(player);
            }
            case 3 -> {
                w3.update(player);
                w3.removeAccount(player);
            }
            case 4 -> {
                w4.update(player);
                w4.removeAccount(player);
            }
        }
    }

    private static void outOfPrison(Player player) {
        if (player.isInPrison()) {
            if (player.getPrisonCount() == 1) {
                player.addPrison(); player.addPrison();
            } else if (player.getPrisonCount() == 2) player.addPrison();
        }
    }

    private static void findWinner(Player player1, Player player2, Player player3, Player player4) {
        fade.play();
        loser.play();
        //find winner
        fade.setOnFinished(e2 -> {
            //find winner
            switch (numOfPlayers) {
                case 2 -> playerWinner(player1, player2);
                case 3 -> playerWinner(player1, player2, player3);
                case 4 -> playerWinner(player1, player2, player3, player4);
            }
        });
    }

    private static void payPlayer(Player player1, PlayerWindow w1, TextArea console, Player player) {
        player1.addToAccount(values[player.getTile()] / 2);
        w1.addAccount(player1);
        player.takeFromAccount(values[player.getTile()] / 2);
        w1.update(player1);
        console.appendText("Player " + player.getPos() + " paid Player " + player1.getPos() + ": " + (values[player.getTile()] / 2) + ".\n");
    }

    private static FadeTransition blink(ImageView firework, Group center) {
        var blink = new FadeTransition();
        blink.setNode(firework);
        blink.setDuration(Duration.millis(2000));
        blink.setToValue(0);
        blink.setCycleCount(9);
        blink.setAutoReverse(true);
        rect.setVisible(false);
        var endOfGame = new ImageView(new Image("end.png"));
        endOfGame.setTranslateX(100);
        endOfGame.setTranslateY(250);
        endOfGame.setFitWidth(800);
        endOfGame.setFitHeight(300);
        endOfGame.setVisible(false);
        center.getChildren().add(endOfGame);
        blink.setOnFinished(e3 -> {
            endOfGame.setVisible(true);
            var appear = new FadeTransition(Duration.millis(4000));
            appear.setToValue(0);
            appear.setCycleCount(Animation.INDEFINITE);
            appear.setAutoReverse(true);
            appear.setNode(endOfGame);
            appear.play();
            firework.setVisible(false);
            win.setVisible(false);
        });
        return blink;
    }

    private static void playerWinner(Player player1, Player player2){
        var firework = new ImageView(new Image("fireworks.png"));
        firework.setTranslateX(150); firework.setTranslateY(100);
        firework.setFitHeight(700); firework.setFitWidth(700);
        if (player1.getAccount() <= 0){
            win.setText(P2WON);
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
        else if (player2.getAccount() <= 0){
            win.setText(P1WON);
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
    }

    private static void playerWinner(Player player1, Player player2, Player player3){
        var firework = new ImageView(new Image("fireworks.png"));
        firework.setTranslateX(150); firework.setTranslateY(100);
        firework.setFitHeight(700); firework.setFitWidth(700);
        if (player1.getAccount() <= 0 && player2.getAccount() <= 0){
            win.setText(P3WON);
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
        else if (player1.getAccount() <= 0 && player3.getAccount() <= 0){
            win.setText(P2WON);
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
        else if (player2.getAccount() <= 0 && player3.getAccount() <= 0){
            win.setText(P1WON);
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
    }

    private static void playerWinner(Player player1, Player player2, Player player3, Player player4){
        var firework = new ImageView(new Image("fireworks.png"));
        firework.setTranslateX(150); firework.setTranslateY(100);
        firework.setFitHeight(700); firework.setFitWidth(700);
        if (player1.getAccount() <= 0 && player2.getAccount() <= 0 && player3.getAccount() <= 0){
            win.setText(P4WON);
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
        else if (player1.getAccount() <= 0 && player2.getAccount() <= 0 && player4.getAccount() <= 0){
            win.setText(P3WON);
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
        else if (player1.getAccount() <= 0 && player3.getAccount() <= 0 && player4.getAccount() <= 0){
            win.setText(P2WON);
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
        else if (player2.getAccount() <= 0 && player3.getAccount() <= 0 && player4.getAccount() <= 0){
            win.setText(P1WON);
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
    }

}