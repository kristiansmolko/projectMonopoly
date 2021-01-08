import extra.Player;
import graphics.Board;
import graphics.PlayerWindow;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class Game {
    private static Chance[] chances = createChances();
    private static int turn = 1;
    private static int chanceNum = 0;
    private static int numOfPlayers;
    private static ArrayList<String> tiles = new ArrayList<>();
    private static final int[] values = new int[] {200, 60, 60, 100, 80, 100, 100, 0, 120, 0, 140, 140, 160, 0, 180, 200,
            0, 220, 100, 220, 240, 260, 260, 0, 280, 0, 300, 300, 320, 0, 350, 400};
    private static MediaPlayer wood, villager, chestOpen, nether, ironHoe, eat, arrow, bow, water, ironPick, shield, ironArmor,
            portalEffect, enchant, brewing, shulker, start;

    public static Circle get1(Rectangle rect, int x){
        Circle c = new Circle();
        c.setCenterX(rect.getX() + x + (rect.getWidth()/2));
        c.setCenterY(rect.getY() + (rect.getHeight()/2));
        c.setRadius(8);
        c.setFill(Color.BLACK);
        return c;
    }

    public static Circle[] get2(Rectangle rect, int x){
        Circle c1 = new Circle();
        Circle c2 = new Circle();
        c1.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 10);
        c1.setCenterY(rect.getY() + (rect.getHeight()/2) - 10);
        c1.setRadius(8);
        c1.setFill(Color.BLACK);
        c2.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 10);
        c2.setCenterY(rect.getY() + (rect.getHeight()/2) + 10);
        c2.setRadius(8);
        c2.setFill(Color.BLACK);
        return new Circle[] {c1,c2};
    }

    public static Circle[] get3(Rectangle rect, int x) {
        Circle c1 = new Circle();
        Circle c2 = new Circle();
        Circle c3 = new Circle();
        Circle[] set = new Circle[] {c1,c2,c3};
        c1.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 15);
        c1.setCenterY(rect.getY() + (rect.getHeight()/2) - 15);
        c2.setCenterX(rect.getX() + x + (rect.getWidth()/2));
        c2.setCenterY(rect.getY() + (rect.getHeight()/2));
        c3.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 15);
        c3.setCenterY(rect.getY() + (rect.getHeight()/2) + 15);
        for (Circle c : set){
            c.setFill(Color.BLACK);
            c.setRadius(6);
        }
        return set;
    }

    public static Circle[] get4(Rectangle rect, int x) {
        Circle c1 = new Circle();
        Circle c2 = new Circle();
        Circle c3 = new Circle();
        Circle c4 = new Circle();
        Circle[] set = new Circle[] {c1,c2,c3,c4};
        c1.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 13);
        c1.setCenterY(rect.getY() + (rect.getHeight()/2) - 13);
        c2.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 13);
        c2.setCenterY(rect.getY() + (rect.getHeight()/2) + 13);
        c3.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 13);
        c3.setCenterY(rect.getY() + (rect.getHeight()/2) + 13);
        c4.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 13);
        c4.setCenterY(rect.getY() + (rect.getHeight()/2) - 13);
        for (Circle c : set){
            c.setFill(Color.BLACK);
            c.setRadius(6);
        }
        return set;
    }

    public static Circle[] get5(Rectangle rect, int x) {
        Circle c1 = new Circle();
        Circle c2 = new Circle();
        Circle c3 = new Circle();
        Circle c4 = new Circle();
        Circle c5 = new Circle();
        Circle[] set = new Circle[] {c1,c2,c3,c4,c5};
        c1.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 14);
        c1.setCenterY(rect.getY() + (rect.getHeight()/2) - 14);
        c2.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 14);
        c2.setCenterY(rect.getY() + (rect.getHeight()/2) + 14);
        c3.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 14);
        c3.setCenterY(rect.getY() + (rect.getHeight()/2) + 14);
        c4.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 14);
        c4.setCenterY(rect.getY() + (rect.getHeight()/2) - 14);
        c5.setCenterX(rect.getX() + x + (rect.getWidth()/2));
        c5.setCenterY(rect.getY() + (rect.getHeight()/2));
        for (Circle c : set){
            c.setFill(Color.BLACK);
            c.setRadius(6);
        }
        return set;
    }

    public static Circle[] get6(Rectangle rect, int x){
        Circle c1 = new Circle();
        Circle c2 = new Circle();
        Circle c3 = new Circle();
        Circle c4 = new Circle();
        Circle c5 = new Circle();
        Circle c6 = new Circle();
        Circle[] set = new Circle[] {c1,c2,c3,c4,c5,c6};
        c1.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 15);
        c1.setCenterY(rect.getY() + (rect.getHeight()/2) - 15);
        c2.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 15);
        c2.setCenterY(rect.getY() + (rect.getHeight()/2));
        c3.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 15);
        c3.setCenterY(rect.getY() + (rect.getHeight()/2) + 15);
        c4.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 15);
        c4.setCenterY(rect.getY() + (rect.getHeight()/2) - 15);
        c5.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 15);
        c5.setCenterY(rect.getY() + (rect.getHeight()/2));
        c6.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 15);
        c6.setCenterY(rect.getY() + (rect.getHeight()/2) + 15);
        for (Circle c : set){
            c.setFill(Color.BLACK);
            c.setRadius(5);
        }
        return set;
    }

    public static ArrayList<String> makeTiles(){
        String[] tiles = new String[] {"START", "Wood", "Stick", "TAX", "Crafting Table", "Stone Pickaxe",
                "Cobblestone", "CHANCE", "Iron Ore", "JAIL", "Iron Hoe", "Wheat", "Carrot", "CHANCE", "Arrow",
                "Bow", "FREE", "Iron Pickaxe", "TAX", "Gold Bar", "Redstone", "Iron Sword", "Shield", "CHANCE",
                "Iron Armor", "PORTAL", "Enchanting Table", "Brew Stand", "Shulker Box", "CHANCE", "Diamond", "Emerald"};
        return new ArrayList<>(Arrays.asList(tiles));
    }

    public static Alert makeDialog(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Players");
        alert.setHeaderText("Choose how many players");
        alert.setContentText("Choose");
        return alert;
    }

    public static Rectangle makeDice(){
        Rectangle rect = new Rectangle(120,500,50,50);
        Stop[] stops = new Stop[] {new Stop(0,Color.BLUE), new Stop(1, Color.AQUAMARINE)};
        LinearGradient linear = new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE, stops);
        rect.setFill(linear);
        rect.setStroke(Color.WHITE);
        rect.setArcWidth(15);
        rect.setArcHeight(15);
        return rect;
    }

    public static TranslateTransition moveBack(Rectangle rect, int x){
        TranslateTransition back = new TranslateTransition();
        back.setByX(-x);
        back.setDuration(Duration.millis(10));
        back.setNode(rect);
        return back;
    }

    public static ImageView makeFigure(Image img){
        ImageView iw = new ImageView(img);
        iw.setFitWidth(50);
        iw.setFitHeight(100);
        return iw;
    }

    public static int randomNum(){
        Random rnd = new Random();
        return (rnd.nextInt(6)+1);
    }

    public static ParallelTransition parallel(Rectangle rect, int x){
        TranslateTransition translate = new TranslateTransition(Duration.millis(500));
        translate.setByX(x);
        translate.setDuration(Duration.millis(1000));
        RotateTransition rotate = new RotateTransition();
        rotate.setByAngle(360);
        rotate.setCycleCount(2);
        return new ParallelTransition(rect, translate, rotate);
    }

    private static Chance[] createChances(){
        Chance[] chances = new Chance[12];
        Chance getMoveToExtra = new Chance("Move to the nearest EXTRA square. When you pass through START pick up $2000", "moveTo", 16);
        Chance getloseMoney150 = new Chance("You lost $150", "money", -150);
        Chance getMove3Squares = new Chance("Move 3 squares back!", "move", -3);
        Chance getwinMoney150 = new Chance("You won $150", "money", 150);
        Chance loseMoney50 = new Chance("You lost $50", "money", -50);
        Chance getwinMoney350 = new Chance("You won $350", "money", 350);
        Chance start = new Chance("Move to start and pick up $2000", "moveTo", 0);
        Chance getMoveToPortal = new Chance("Move to the portal", "moveTo", 25);
        Chance getMoveToJail = new Chance("You got yourself to jail. Move to jail, you won't get through start and you won't get $2000", "moveTo", 9);
        Chance getMovetonearestSquare = new Chance("Move to the nearest square", "move", 1);
        Chance taxFree = new Chance("Next time you come to tax, you don't have to pay it!", "tax", 0);
        Chance getOutofJail = new Chance("Get out of jail free","prison",0);
        chances[1] = getloseMoney150;
        chances[2] = getOutofJail;
        chances[10] = loseMoney50;
        chances[11] = getMoveToJail;
        chances[6] = getMoveToExtra;
        chances[3] = start;
        chances[7] = getMove3Squares;
        chances[8] = taxFree;
        chances[9] = getwinMoney150;
        chances[4] = getMovetonearestSquare;
        chances[5] = getMoveToPortal;
        chances[0] = getwinMoney350;
        return chances;
    }

    private static void makeSounds(){
        Media woodSound = new Media(new File("resources/wood1.mp3").toURI().toString());
        wood = new MediaPlayer(woodSound);
        Media villagerSound = new Media(new File("resources/idle2.mp3").toURI().toString());
        villager = new MediaPlayer(villagerSound);
        Media chanceSound = new Media(new File("resources/open.mp3").toURI().toString());
        chestOpen = new MediaPlayer(chanceSound);
        Media netherSound = new Media(new File("resources/moan2.mp3").toURI().toString());
        nether = new MediaPlayer(netherSound);
        Media ironHoeSound = new Media(new File("resources/till3.mp3").toURI().toString());
        ironHoe = new MediaPlayer(ironHoeSound);
        Media eatSound = new Media(new File("resources/eat1.mp3").toURI().toString());
        eat = new MediaPlayer(eatSound);
        Media arrowSound = new Media(new File("resources/bowhit4.mp3").toURI().toString());
        arrow = new MediaPlayer(arrowSound);
        Media bowSound = new Media(new File("resources/bow.mp3").toURI().toString());
        bow = new MediaPlayer(bowSound);
        Media waterSound = new Media(new File("resources/splash2.mp3").toURI().toString());
        water = new MediaPlayer(waterSound);
        Media equipmentSound = new Media(new File("resources/anvil_use.mp3").toURI().toString());
        ironPick = new MediaPlayer(equipmentSound);
        Media shieldSound = new Media(new File("resources/block5.mp3").toURI().toString());
        shield = new MediaPlayer(shieldSound);
        Media armorSound = new Media(new File("resources/equip_iron2.mp3").toURI().toString());
        ironArmor = new MediaPlayer(armorSound);
        Media portalSound = new Media(new File("resources/travel.mp3").toURI().toString());
        portalEffect = new MediaPlayer(portalSound);
        Media enchanting = new Media(new File("resources/levelup.mp3").toURI().toString());
        enchant = new MediaPlayer(enchanting);
        Media brewingSound = new Media(new File("resources/drink.mp3").toURI().toString());
        brewing = new MediaPlayer(brewingSound);
        Media shulkerOpen = new Media(new File("resources/open_shulker.mp3").toURI().toString());
        shulker = new MediaPlayer(shulkerOpen);
        Media startSound = new Media(new File("resources/grass6.mp3").toURI().toString());
        start = new MediaPlayer(startSound);
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
        shield.stop();
        ironArmor.stop();
        portalEffect.stop();
        enchant.stop();
        brewing.stop();
        start.stop();
    }

    private static void nextTurn(){
        if (turn == numOfPlayers)
            turn = 1;
        else
            turn -= -1;
    }

    public static BorderPane createGame(){
        BorderPane root = new BorderPane();
        BorderPane border = new BorderPane();
        Group center = new Group();
        BorderPane buy = new BorderPane();
        BorderPane right = new BorderPane();
        GridPane playerWindows = new GridPane();
        //dice creating
        Rectangle rect = makeDice();
        int x = 250; //move
        //moves
        TranslateTransition back = moveBack(rect, x);
        ParallelTransition par = parallel(rect, x);
        TranslateTransition translateFigure = new TranslateTransition();
        TranslateTransition translateFigure2 = new TranslateTransition();
        TranslateTransition transY = new TranslateTransition();
        TranslateTransition transY2 = new TranslateTransition();
        TranslateTransition portal = new TranslateTransition();
        TranslateTransition chanceMove = new TranslateTransition();
        PauseTransition pauseMove = new PauseTransition();
        //creating sounds
        makeSounds();
        //dots in dice
        Circle c1 = get1(rect, x);
        Circle[] set2 = get2(rect, x);
        Circle[] set3 = get3(rect, x);
        Circle[] set4 = get4(rect, x);
        Circle[] set5 = get5(rect, x);
        Circle[] set6 = get6(rect, x);
        //dialog window
        Alert alert = makeDialog();
        ButtonType b1 = new ButtonType("Two");
        ButtonType b2 = new ButtonType("Three");
        ButtonType b3 = new ButtonType("Four");
        ButtonType can = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(b1, b2, b3, can);
        //get number of players
        Optional<ButtonType> result = alert.showAndWait();
        //button actions
        if (result.get() == b1)
            numOfPlayers = 2;
        else if (result.get() == b2)
            numOfPlayers = 3;
        else if (result.get() == b3)
            numOfPlayers = 4;
        else if (result.get() == can)
            System.exit(0);
        //set new players
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Player player3 = new Player(3);
        Player player4 = new Player(4);
        //add figures to players
        switch (numOfPlayers) {
            case 3 -> {
                player1.setFigure("diamond steve.png");
                player2.setFigure("creeper.png");
                player3.setFigure("steve.png");
            }
            case 4 -> {
                player1.setFigure("diamond steve.png");
                player2.setFigure("creeper.png");
                player3.setFigure("steve.png");
                player4.setFigure("zombified piglin.png");
            }
            default -> {
                player1.setFigure("diamond steve.png");
                player2.setFigure("creeper.png");
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
        PlayerWindow w1 = new PlayerWindow();
        PlayerWindow w2 = new PlayerWindow();
        PlayerWindow w3 = new PlayerWindow();
        PlayerWindow w4 = new PlayerWindow();


        BorderPane P1W = w1.makeWindow(player1);
        BorderPane P2W = w2.makeWindow(player2);
        BorderPane P3W = w3.makeWindow(player3);
        BorderPane P4W = w4.makeWindow(player4);
        //mouse click action
        EventHandler<MouseEvent> mouse = mouseEvent -> {
            if (mouseEvent.getSource() == rect){
                par.play();
            }
        };
        //when dice throw is completed
        par.setOnFinished(actionEvent -> {
            int finalRn = randomNum();
            if (finalRn == 1)
                root.getChildren().add(c1);
            else if (finalRn == 2)
                for (Circle setC : set2)
                    root.getChildren().addAll(setC);
            else if (finalRn == 3)
                for (Circle setC : set3)
                    root.getChildren().addAll(setC);
            else if (finalRn == 4)
                for (Circle setC : set4)
                    root.getChildren().addAll(setC);
            else if (finalRn == 5)
                for (Circle setC : set5)
                    root.getChildren().addAll(setC);
            else if (finalRn == 6)
                for (Circle setC : set6)
                    root.getChildren().addAll(setC);
            //move
            int move = finalRn * 100;
            //values of tiles
            tiles = makeTiles();

            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//       ALGORITH FOR MOVING DON'T FREAKING TOUCH IT, DON'T EVEN LOOK AT IT, I WARN YOU!
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            translateFigure.setDuration(Duration.millis(2000));
            translateFigure2.setDuration(Duration.millis(2000));
            transY.setDuration(Duration.millis(2000));
            transY2.setDuration(Duration.millis(2000));
            portal.setDuration(Duration.millis(10));
            pauseMove.setDuration(Duration.millis(5000));
            //console displaying player turn and action
            TextArea console = new TextArea();
            console.setEditable(false);
            console.setMaxHeight(100);
            console.setMaxWidth(200);
            //Label text = new Label();
            //Button yes = new Button();
            buy.setTop(null);
            buy.setCenter(null);
            buy.setMaxSize(200,200);
            Player[] playerList = new Player[numOfPlayers];
            playerList[0] = player1;
            playerList[1] = player2;
            if (player3.isPlaying())
                playerList[2] = player3;
            if (player4.isPlaying())
                playerList[3] = player4;

            Label win = new Label();
            win.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 50));
            win.setTextFill(Color.RED);
            win.setTranslateX(50); win.setTranslateY(200);
            //find winner
            if (numOfPlayers == 2){
                if (player1.getAccount() <= 0){
                    win.setText("Player 2 has WON this game!");
                    center.getChildren().add(win);
                    turn = 0;
                }
                else if (player2.getAccount() <= 0){
                    win.setText("Player 1 has WON this game!");
                    center.getChildren().add(win);
                    turn = 0;
                }
            }
            else if (numOfPlayers == 3){
                if (player1.getAccount() <= 0 && player2.getAccount() <= 0){
                    win.setText("Player 3 has WON this game!");
                    center.getChildren().add(win);
                    turn = 0;
                }
                else if (player1.getAccount() <= 0 && player3.getAccount() <= 0){
                    win.setText("Player 2 has WON this game!");
                    center.getChildren().add(win);
                    turn = 0;
                }
                else if (player2.getAccount() <= 0 && player3.getAccount() <= 0){
                    win.setText("Player 1 has WON this game!");
                    center.getChildren().add(win);
                    turn = 0;
                }
            }
            else if (numOfPlayers == 4){
                if (player1.getAccount() <= 0 && player2.getAccount() <= 0 && player3.getAccount() <= 0){
                    win.setText("Player 4 has WON this game!");
                    center.getChildren().add(win);
                    turn = 0;
                }
                else if (player1.getAccount() <= 0 && player2.getAccount() <= 0 && player4.getAccount() <= 0){
                    win.setText("Player 3 has WON this game!");
                    center.getChildren().add(win);
                    turn = 0;
                }
                else if (player1.getAccount() <= 0 && player3.getAccount() <= 0 && player4.getAccount() <= 0){
                    win.setText("Player 2 has WON this game!");
                    center.getChildren().add(win);
                    turn = 0;
                }
                else if (player2.getAccount() <= 0 && player3.getAccount() <= 0 && player4.getAccount() <= 0){
                    win.setText("Player 1 has WON this game!");
                    center.getChildren().add(win);
                    turn = 0;
                }
            }

            for (Player player : playerList){
                //case when player has no money
                if (player.getAccount() <= 0 && turn == player.getPos()){
                    player.isDefeated();
                    switch(player.getPos()){
                        case 1 -> w1.update(player1);
                        case 2 -> w2.update(player2);
                        case 3 -> w3.update(player3);
                        case 4 -> w4.update(player4);
                    }
                    nextTurn();
                }
                //case when player is in prison
                if (player.isInPrison() && turn == player.getPos()){
                    player.addPrison();
                    switch(player.getPos()){
                        case 1 -> w1.update(player1);
                        case 2 -> w2.update(player2);
                        case 3 -> w3.update(player3);
                        case 4 -> w4.update(player4);
                    }
                    nextTurn();
                }
                if (turn == player.getPos()) {
                    if (turn == 1) {
                        translateFigure.setNode(figure1);
                        transY.setNode(figure1);
                        portal.setNode(figure1);
                        chanceMove.setNode(figure1);
                    } else if (turn == 2) {
                        translateFigure.setNode(figure2);
                        transY.setNode(figure2);
                        portal.setNode(figure2);
                        chanceMove.setNode(figure2);
                    } else if (turn == 3) {
                        translateFigure.setNode(figure3);
                        transY.setNode(figure3);
                        portal.setNode(figure3);
                        chanceMove.setNode(figure3);
                    } else if (turn == 4) {
                        translateFigure.setNode(figure4);
                        transY.setNode(figure4);
                        portal.setNode(figure4);
                        chanceMove.setNode(figure4);
                    }
                    translateFigure.setByX(0);
                    translateFigure.setByY(0);
                    transY.setByY(0);
                    transY.setByX(0);
                    console.setText("Player " + player.getPos() + " turn.\n");
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
                        switch ((int) nextTile){
                            case 1 -> {
                                wood.setCycleCount(4);
                                wood.play();
                            }
                            case 3, 18 -> villager.play();
                            case 7, 23, 13, 29 -> chestOpen.play();
                            case 9 -> nether.play();
                            case 17 -> ironPick.play();
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
                                switch (chances[chanceNum].getAction()) {
                                    case "move" -> {
                                        console.appendText(chances[chanceNum].getText());
                                        if (player.getTile() < 9 && player.getTile() > 0)
                                            chanceMove.setToX(920 - (player.getTile() + chances[chanceNum].getValue()) * 100);
                                        else if (player.getTile() < 25 && player.getTile() > 16)
                                            chanceMove.setToX((player.getTile() + chances[chanceNum].getValue() - 16) * 100 + 20);
                                        if (player.getTile() < 16 && player.getTile() > 9)
                                            chanceMove.setToY(705 - (player.getTile() + chances[chanceNum].getValue() - 9) * 100);
                                        else if (player.getTile() < 32 && player.getTile() > 25)
                                            chanceMove.setToY(100 * (player.getTile() + chances[chanceNum].getValue() - 25));
                                        player.addTile(chances[chanceNum].getValue());
                                        chanceMove.play();
                                        chanceMove.setOnFinished(e -> {
                                            if ((player1.getOwned().contains(tiles.get(player.getTile()))) && player1.isInPrison()) {
                                                player1.addToAccount(values[player.getTile()] / 2);
                                                w1.addAccount(player1);
                                                player.takeFromAccount(values[player.getTile()] / 2);
                                                w1.update(player1);
                                                switch (player.getPos()) {
                                                    case 2 -> w2.removeAccount(player);
                                                    case 3 -> w3.removeAccount(player);
                                                    case 4 -> w4.removeAccount(player);
                                                }
                                                console.appendText("Player " + player.getPos() + " paid Player " + player1.getPos() + ": " + (values[player.getTile()] / 2) + "\n");
                                            } else if ((player2.getOwned().contains(tiles.get(player.getTile()))) && !player2.isInPrison()) {
                                                player2.addToAccount(values[player.getTile()] / 2);
                                                w2.addAccount(player2);
                                                player.takeFromAccount(values[player.getTile()] / 2);
                                                w2.update(player2);
                                                switch (player.getPos()) {
                                                    case 1 -> w1.removeAccount(player);
                                                    case 3 -> w3.removeAccount(player);
                                                    case 4 -> w4.removeAccount(player);
                                                }
                                                console.appendText("Player " + player.getPos() + " paid Player " + player2.getPos() + ": " + (values[player.getTile()] / 2) + "\n");
                                            } else if ((player3.getOwned().contains(tiles.get(player.getTile()))) && !player3.isInPrison()) {
                                                player3.addToAccount(values[player.getTile()] / 2);
                                                w3.addAccount(player3);
                                                player.takeFromAccount(values[player.getTile()] / 2);
                                                w3.update(player3);
                                                switch (player.getPos()) {
                                                    case 1 -> w1.removeAccount(player);
                                                    case 2 -> w2.removeAccount(player);
                                                    case 4 -> w4.removeAccount(player);
                                                }
                                                console.appendText("Player " + player.getPos() + " paid Player " + player3.getPos() + ": " + (values[player.getTile()] / 2) + "\n");
                                            } else if ((player4.getOwned().contains(tiles.get(player.getTile()))) && !player4.isInPrison()) {
                                                player4.addToAccount(values[player.getTile()] / 2);
                                                w4.addAccount(player4);
                                                player1.takeFromAccount(values[player.getTile()] / 2);
                                                w4.update(player4);
                                                switch (player.getPos()) {
                                                    case 1 -> w1.removeAccount(player);
                                                    case 2 -> w2.removeAccount(player);
                                                    case 3 -> w3.removeAccount(player);
                                                }
                                                console.appendText("Player " + player.getPos() + " paid Player " + player4.getPos() + ": " + (values[player.getTile()] / 2) + "\n");
                                            } else {
                                                Label text1 = new Label("Do you want to buy \n" + tiles.get(player.getTile()) + " for " + values[player.getTile()] + "?");
                                                text1.setTranslateX(20);
                                                Button yes1 = new Button("Buy");
                                                yes1.setTranslateY(30);
                                                yes1.setPrefSize(60, 40);
                                                yes1.setOnAction(actionEvent12 -> {
                                                    player.addOwned(tiles.get(player.getTile()));
                                                    player.takeFromAccount(values[player.getTile()]);
                                                    console.appendText("\nPlayer " + player.getPos() + " bought:\n" + tiles.get(player.getTile()));
                                                    switch (player.getPos()) {
                                                        case 1 -> {w1.update(player); w1.removeAccount(player);}
                                                        case 2 -> {w2.update(player); w2.removeAccount(player);}
                                                        case 3 -> {w3.update(player); w3.removeAccount(player);}
                                                        case 4 -> {w4.update(player); w4.removeAccount(player);}
                                                    }
                                                    buy.setTop(null);
                                                    buy.setCenter(null);
                                                });
                                                buy.setTop(text1);
                                                buy.setCenter(yes1);
                                            }
                                        }); //end of chM.sOF
                                    }
                                    case "moveTo" -> {
                                        console.appendText(chances[chanceNum].getText());
                                        if (chances[chanceNum].getValue() == 0) {
                                            chanceMove.setToX(920);
                                            chanceMove.setToY(705);
                                            if (player.getTile() < 16)
                                                player.addTile(-player.getTile());
                                            else {
                                                player.addTile(-player.getTile());
                                                player.addToAccount(200);
                                                switch (player.getPos()) {
                                                    case 1 -> w1.addAccount(player);
                                                    case 2 -> w2.addAccount(player);
                                                    case 3 -> w3.addAccount(player);
                                                    case 4 -> w4.addAccount(player);
                                                }
                                            }
                                            chanceMove.play();
                                        } else if (chances[chanceNum].getValue() == 9) {
                                            if (!player.getExtra().contains("prison")) {
                                                chanceMove.setToX(20);
                                                chanceMove.setToY(705);
                                                if (player.getTile() < 9)
                                                    player.addTile(9 - player.getTile());
                                                else
                                                    player.addTile(-(player.getTile() - 9));
                                                chanceMove.play();
                                                chanceMove.setOnFinished(e -> {
                                                    if (player1.isInPrison()) {
                                                        switch (player1.getPrisonCount()) {
                                                            case 1 -> {
                                                                player1.addPrison();
                                                                player1.addPrison();
                                                            }
                                                            case 2 -> player1.addPrison();
                                                        }
                                                    }
                                                    if (player2.isInPrison()) {
                                                        switch (player2.getPrisonCount()) {
                                                            case 1 -> {
                                                                player2.addPrison();
                                                                player2.addPrison();
                                                            }
                                                            case 2 -> player2.addPrison();
                                                        }
                                                    }
                                                    if (player3.isInPrison()) {
                                                        switch (player3.getPrisonCount()) {
                                                            case 1 -> {
                                                                player3.addPrison();
                                                                player3.addPrison();
                                                            }
                                                            case 2 -> player3.addPrison();
                                                        }
                                                    }
                                                    if (player4.isInPrison()) {
                                                        switch (player4.getPrisonCount()) {
                                                            case 1 -> {
                                                                player4.addPrison();
                                                                player4.addPrison();
                                                            }
                                                            case 2 -> player4.addPrison();
                                                        }
                                                    }
                                                    player.toPrison();
                                                    nether.play();
                                                });
                                            } else player.getExtra().remove("prison");
                                        } else if (chances[chanceNum].getValue() == 16) {
                                            chanceMove.setToX(20);
                                            chanceMove.setToY(5);
                                            if (player.getTile() < 16)
                                                player.addTile(16 - player.getTile());
                                            else
                                                player.addTile(-(player.getTile() - 16));
                                            chanceMove.play();
                                            chanceMove.setOnFinished(e -> {
                                                water.play();
                                            });

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
                                            portal.setOnFinished(e -> {
                                                player.toPrison();
                                                if (player1.isInPrison()){
                                                    switch (player1.getPrisonCount()){
                                                        case 1 -> {
                                                            player1.addPrison();
                                                            player1.addPrison();
                                                        }
                                                        case 2 -> player1.addPrison();
                                                    }
                                                }
                                                if (player2.isInPrison()){
                                                    switch (player2.getPrisonCount()){
                                                        case 1 -> {
                                                            player2.addPrison();
                                                            player2.addPrison();
                                                        }
                                                        case 2 -> player2.addPrison();
                                                    }
                                                }
                                                if (player3.isInPrison()){
                                                    switch (player3.getPrisonCount()){
                                                        case 1 -> {
                                                            player3.addPrison();
                                                            player3.addPrison();
                                                        }
                                                        case 2 -> player3.addPrison();
                                                    }
                                                }
                                                if (player4.isInPrison()){
                                                    switch (player4.getPrisonCount()){
                                                        case 1 -> {
                                                            player4.addPrison();
                                                            player4.addPrison();
                                                        }
                                                        case 2 -> player4.addPrison();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                    case "money" -> {
                                        console.appendText(chances[chanceNum].getText());
                                        player.addToAccount(chances[chanceNum].getValue());
                                        if (chances[chanceNum].getValue() > 0)
                                            switch (player.getPos()) {
                                                case 1 -> w1.addAccount(player);
                                                case 2 -> w2.addAccount(player);
                                                case 3 -> w3.addAccount(player);
                                                case 4 -> w4.addAccount(player);
                                            }
                                        else if (chances[chanceNum].getValue() < 0)
                                            switch (player.getPos()) {
                                                case 1 -> w1.removeAccount(player);
                                                case 2 -> w2.removeAccount(player);
                                                case 3 -> w3.removeAccount(player);
                                                case 4 -> w4.removeAccount(player);
                                            }
                                    }
                                    case "prison" -> player.addExtra("prison");
                                    case "tax" -> player.addExtra("tax");
                                }
                                chanceNum++;
                                if (chanceNum == 12)
                                    chanceNum = 0;
                                break;
                            case "TAX":
                                if (!player.getExtra().contains("tax")) {
                                    player.takeFromAccount(values[player.getTile()]);
                                    console.appendText("\nPlayer " + player.getPos() + " paid tax: " + values[player.getTile()] + "\n");
                                    switch (player.getPos()) {
                                        case 1 -> w1.removeAccount(player);
                                        case 2 -> w2.removeAccount(player);
                                        case 3 -> w3.removeAccount(player);
                                        case 4 -> w4.removeAccount(player);
                                    }
                                } else player.getExtra().remove("tax");
                                break;
                            case "JAIL":
                                console.appendText("It's hot here. Uhh...");
                                break;
                            case "FREE":
                                console.appendText("Enjoy your stay!");
                                break;
                            default:
                                if ((player1.getOwned().contains(tiles.get(player.getTile()))) && !player1.isInPrison()) {
                                    player1.addToAccount(values[player.getTile()] / 2);
                                    w1.addAccount(player1);
                                    player.takeFromAccount(values[player.getTile()] / 2);
                                    w1.update(player1);
                                    switch (player.getPos()) {
                                        case 2 -> w2.removeAccount(player);
                                        case 3 -> w3.removeAccount(player);
                                        case 4 -> w4.removeAccount(player);
                                    }
                                    console.appendText("Player " + player.getPos() + " paid Player " + player1.getPos() + ": " + (values[player.getTile()] / 2) + "\n");
                                } else if ((player2.getOwned().contains(tiles.get(player.getTile()))) && !player2.isInPrison()) {
                                    player2.addToAccount(values[player.getTile()] / 2);
                                    w2.addAccount(player2);
                                    player.takeFromAccount(values[player.getTile()] / 2);
                                    w2.update(player2);
                                    switch (player.getPos()) {
                                        case 1 -> w1.removeAccount(player);
                                        case 3 -> w3.removeAccount(player);
                                        case 4 -> w4.removeAccount(player);
                                    }
                                    console.appendText("Player " + player.getPos() + " paid Player " + player2.getPos() + ": " + (values[player.getTile()] / 2) + "\n");
                                } else if ((player3.getOwned().contains(tiles.get(player.getTile()))) && !player3.isInPrison()) {
                                    player3.addToAccount(values[player.getTile()] / 2);
                                    w3.addAccount(player3);
                                    player.takeFromAccount(values[player.getTile()] / 2);
                                    w3.update(player3);
                                    switch (player.getPos()) {
                                        case 1 -> w1.removeAccount(player);
                                        case 2 -> w2.removeAccount(player);
                                        case 4 -> w4.removeAccount(player);
                                    }
                                    console.appendText("Player " + player.getPos() + " paid Player " + player3.getPos() + ": " + (values[player.getTile()] / 2) + "\n");
                                } else if ((player4.getOwned().contains(tiles.get(player.getTile()))) && !player4.isInPrison()) {
                                    player4.addToAccount(values[player.getTile()] / 2);
                                    w4.addAccount(player4);
                                    player1.takeFromAccount(values[player.getTile()] / 2);
                                    w4.update(player4);
                                    switch (player.getPos()) {
                                        case 1 -> w1.removeAccount(player);
                                        case 2 -> w2.removeAccount(player);
                                        case 3 -> w3.removeAccount(player);
                                    }
                                    console.appendText("Player " + player.getPos() + " paid Player " + player4.getPos() + ": " + (values[player.getTile()] / 2) + "\n");
                                } else {
                                    Label text = new Label("Do you want to buy \n" + tiles.get(player.getTile()) + " for " + values[player.getTile()] + "?");
                                    text.setTranslateX(20);
                                    Button yes = new Button("Buy");
                                    yes.setTranslateY(30);
                                    yes.setPrefSize(60, 40);
                                    yes.setOnAction(actionEvent12 -> {
                                        player.addOwned(tiles.get(player.getTile()));
                                        player.takeFromAccount(values[player.getTile()]);
                                        console.appendText("\nPlayer " + player.getPos() + " bought:\n" + tiles.get(player.getTile()));
                                        switch (player.getPos()) {
                                            case 1 -> {w1.update(player); w1.removeAccount(player);}
                                            case 2 -> {w2.update(player); w2.removeAccount(player);}
                                            case 3 -> {w3.update(player); w3.removeAccount(player);}
                                            case 4 -> {w4.update(player); w4.removeAccount(player);}
                                        }
                                        buy.setTop(null);
                                        buy.setCenter(null);
                                    });
                                    buy.setTop(text);
                                    buy.setCenter(yes);
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
                            transY.setByY(-(((nextTile - 9) * 100)));
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
                            translateFigure.setByX(move - (((nextTile - 25) * 100)));
                            transY.setByY(rest);
                            translateFigure.play();
                            translateFigure.setOnFinished(actionEvent15 -> transY.play());
                        } else {
                            translateFigure.setByX(move);
                            translateFigure.play();
                            if (nextTile == 25) {
                                translateFigure.setOnFinished(actionEvent14 -> {
                                    if (!player.getExtra().contains("prison")) {
                                        if (player1.isInPrison()) {
                                            switch (player1.getPrisonCount()) {
                                                case 1 -> {
                                                    player1.addPrison();
                                                    player1.addPrison();
                                                }
                                                case 2 -> player1.addPrison();
                                            }
                                        }
                                        if (player2.isInPrison()) {
                                            switch (player2.getPrisonCount()) {
                                                case 1 -> {
                                                    player2.addPrison();
                                                    player2.addPrison();
                                                }
                                                case 2 -> player2.addPrison();
                                            }
                                        }
                                        if (player3.isInPrison()) {
                                            switch (player3.getPrisonCount()) {
                                                case 1 -> {
                                                    player3.addPrison();
                                                    player3.addPrison();
                                                }
                                                case 2 -> player3.addPrison();
                                            }
                                        }
                                        if (player4.isInPrison()) {
                                            switch (player4.getPrisonCount()) {
                                                case 1 -> {
                                                    player4.addPrison();
                                                    player4.addPrison();
                                                }
                                                case 2 -> player4.addPrison();
                                            }
                                        }
                                        pauseMove.play();
                                        portal.setToX(20);
                                        portal.setToY(705);
                                        portal.play();
                                        player.addTile(-16);
                                        player.toPrison();
                                        portalEffect.play();
                                    }else player.getExtra().remove("prison");
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
                        console.appendText("Player " + player.getPos() + " has crossed Start\n");
                        player.addTile(-32);
                        switch (player.getPos()) {
                            case 1 -> w1.addAccount(player);
                            case 2 -> w2.addAccount(player);
                            case 3 -> w3.addAccount(player);
                            case 4 -> w4.addAccount(player);
                        }
                    }
                    actionEvent.consume();
                    buy.setBottom(console);
                }
            }
            musicStop();
            console.setTranslateY(150);
            nextTurn(); //next player
        });

        portal.setOnFinished(actionEvent -> {portal.stop(); actionEvent.consume();});

        //actions
        rect.addEventHandler(MouseEvent.MOUSE_CLICKED, mouse);
        root.getChildren().addAll(rect);

        //board
        Board graphics = new Board();
        BorderPane board = graphics.makeBoard();

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
        center.getChildren().add(root);

        //game setup
        border.setCenter(center);
        border.setRight(right);
        return border;
    }
}
