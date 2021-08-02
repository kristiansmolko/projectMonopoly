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
    private static final Chance[] chances = createChances();
    private static int turn = 1;
    private static int chanceNum = 0;
    private static int numOfPlayers;
    private static Rectangle rect = makeDice();
    private static Label win = new Label();
    private static Group center;
    private static ArrayList<String> tiles = new ArrayList<>();
    private static final int[] values = new int[] {200, 60, 60, 100, 80, 100, 100, 0, 120, 0, 140, 140, 160, 0, 180, 200,
            0, 220, 100, 220, 240, 260, 260, 0, 280, 0, 300, 300, 320, 0, 350, 400};
    private static MediaPlayer wood, villager, chestOpen, nether, ironHoe, eat, arrow, bow, water, ironPick, ironSword, shield, ironArmor,
            portalEffect, enchant, brewing, shulker, start, fanfare, loser;

    public static Circle get1(Rectangle rect, int x){
        var c = new Circle();
        c.setCenterX(rect.getX() + x + (rect.getWidth()/2));
        c.setCenterY(rect.getY() + (rect.getHeight()/2));
        c.setRadius(8);
        c.setFill(Color.BLACK);
        return c;
    }

    public static Circle[] get2(Rectangle rect, int x){
        var c1 = new Circle();
        var c2 = new Circle();
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
        var c1 = new Circle();
        var c2 = new Circle();
        var c3 = new Circle();
        var set = new Circle[] {c1,c2,c3};
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
        var c1 = new Circle();
        var c2 = new Circle();
        var c3 = new Circle();
        var c4 = new Circle();
        var set = new Circle[] {c1,c2,c3,c4};
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
        var c1 = new Circle();
        var c2 = new Circle();
        var c3 = new Circle();
        var c4 = new Circle();
        var c5 = new Circle();
        var set = new Circle[] {c1,c2,c3,c4,c5};
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
        var c1 = new Circle();
        var c2 = new Circle();
        var c3 = new Circle();
        var c4 = new Circle();
        var c5 = new Circle();
        var c6 = new Circle();
        var set = new Circle[] {c1,c2,c3,c4,c5,c6};
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
        var CHANCE = "CHANCE";
        var tiles = new String[] {"START", "Wood", "Stick", "TAX", "Crafting Table", "Stone Pickaxe",
                "Cobblestone", CHANCE, "Iron Ore", "JAIL", "Iron Hoe", "Wheat", "Carrot", CHANCE, "Arrow",
                "Bow", "FREE", "Iron Pickaxe", "TAX", "Gold Bar", "Redstone", "Iron Sword", "Shield", CHANCE,
                "Iron Armor", "PORTAL", "Enchanting", "Brew Stand", "Shulker Box", CHANCE, "Diamond", "Emerald"};
        return new ArrayList<>(Arrays.asList(tiles));
    }

    public static Alert makeDialog(){
        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Players");
        alert.setHeaderText("Choose how many players");
        alert.setContentText("Choose");
        return alert;
    }

    public static Rectangle makeDice(){
        var rect = new Rectangle(120,500,50,50);
        var stops = new Stop[] {new Stop(0,Color.BLUE), new Stop(1, Color.AQUAMARINE)};
        var linear = new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE, stops);
        rect.setFill(linear);
        rect.setStroke(Color.WHITE);
        rect.setArcWidth(15);
        rect.setArcHeight(15);
        return rect;
    }

    public static TranslateTransition moveBack(Rectangle rect, int x){
        var back = new TranslateTransition();
        back.setByX(-x);
        back.setDuration(Duration.millis(10));
        back.setNode(rect);
        return back;
    }

    public static ImageView makeFigure(Image img){
        var iw = new ImageView(img);
        iw.setFitWidth(50);
        iw.setFitHeight(100);
        return iw;
    }

    public static int randomNum(){
        Random rnd = new Random();
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

    private static Chance[] createChances(){
        Random rnd = new Random();
        var moveTo = "moveTo";
        var money = "money";
        var chances = new Chance[12];
        var getMoveToExtra = new Chance("Move to the FREE square.", moveTo, 16);
        var getloseMoney150 = new Chance("You lost 150.", money, -150);
        var getMove3Squares = new Chance("Move 3 squares back!", "move", -3);
        var getwinMoney150 = new Chance("You won 150.", money, 150);
        var loseMoney50 = new Chance("You lost 50.", money, -50);
        var getwinMoney350 = new Chance("You won 350.", money, 350);
        var start = new Chance("Move to start and pick up 200.", moveTo, 0);
        var getMoveToPortal = new Chance("Move to the portal.", moveTo, 25);
        var getMoveToJail = new Chance("You got yourself to jail.", moveTo, 9);
        var getMovetonearestSquare = new Chance("Move to the nearest square.", "move", 1);
        var taxFree = new Chance("Next time you come to tax \n you don't have to pay it!", "tax", 0);
        var getOutofJail = new Chance("Get out of jail card.","prison",0);
        ArrayList<Chance> allChance = new ArrayList<>();
        allChance.add(getloseMoney150); allChance.add(getOutofJail);
        allChance.add(loseMoney50); allChance.add(getMovetonearestSquare);
        allChance.add(start); allChance.add(getMoveToJail);
        allChance.add(getMoveToExtra); allChance.add(taxFree);
        allChance.add(getwinMoney150); allChance.add(getwinMoney350);
        allChance.add(getMoveToPortal); allChance.add(getMove3Squares);
        for (var i = 0; i < chances.length; i++){
            var number = rnd.nextInt(allChance.size());
            chances[i] = allChance.get(number);
            allChance.remove(number);
        }
        return chances;
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

    public static BorderPane createGame(ArrayList<String> figures){
        BorderPane root = new BorderPane();
        BorderPane border = new BorderPane();
        center = new Group();
        BorderPane buy = new BorderPane();
        BorderPane right = new BorderPane();
        GridPane playerWindows = new GridPane();
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
        FadeTransition fade = new FadeTransition();
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

/*
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

        ArrayList<String> available = new ArrayList<>();
        available.add("diamond steve.png");
        available.add("creeper.png");
        available.add("steve.png");
        available.add("zombified piglin.png");

        ChoiceDialog<String> player1Choose = new ChoiceDialog<>(available.get(0), available);
        player1Choose.setTitle("Player 1 figure");
        player1Choose.setHeaderText("Player 1, choose your figure");
        Optional<String> resultForP1 = player1Choose.showAndWait();
        if (resultForP1.isEmpty())
            System.exit(0);
        else
            available.remove(resultForP1.get());

        ChoiceDialog<String> player2Choose = new ChoiceDialog<>(available.get(0), available);
        player2Choose.setTitle("Player 2 figure");
        player2Choose.setHeaderText("Player 2, choose your figure");
        Optional<String> resultForP2 = player2Choose.showAndWait();
        if (resultForP2.isEmpty())
            System.exit(0);
        else
            available.remove(resultForP2.get());
        ChoiceDialog<String> player3Choose = new ChoiceDialog<>(available.get(0), available);
        player3Choose.setTitle("Player 3 figure");
        player3Choose.setHeaderText("Player 3, choose your figure");
        Optional<String> resultForP3 = Optional.empty();
        ChoiceDialog<String> player4Choose = new ChoiceDialog<>(available.get(0), available);
        player4Choose.setTitle("Player 4 figure");
        player4Choose.setHeaderText("Player 4, choose your figure");
        Optional<String> resultForP4 = Optional.empty();

        if (numOfPlayers == 3){
            resultForP3 = player3Choose.showAndWait();
            if (resultForP3.isEmpty())
                System.exit(0);
            else
                available.remove(resultForP3.get());
        }
        if (numOfPlayers == 4){
            resultForP3 = player3Choose.showAndWait();
            if (resultForP3.isEmpty())
                System.exit(0);
            else
                available.remove(resultForP3.get());

            player4Choose = new ChoiceDialog<>(available.get(0), available);
            player4Choose.setTitle("Player 4 figure");
            player4Choose.setHeaderText("Player 4, choose your figure");
            resultForP4 = player4Choose.showAndWait();
            if (resultForP4.isEmpty())
                System.exit(0);
            else
                available.remove(resultForP4.get());
        }*/

        //set new players
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Player player3 = new Player(3);
        Player player4 = new Player(4);
        numOfPlayers = figures.size();
        //add figures to players
        switch (numOfPlayers) {
            case 3 -> {
                player1.setFigure(figures.get(0));
                player2.setFigure(figures.get(1));
                player3.setFigure(figures.get(2));
                }
//            case 4 -> {
//                player1.setFigure(resultForP1.get());
//                player2.setFigure(resultForP2.get());
//                player3.setFigure(resultForP3.get());
//                player4.setFigure(resultForP4.get());
//            }
            default -> {
                player1.setFigure(figures.get(0));
                player2.setFigure(figures.get(1));
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

       Player[] playerList = new Player[numOfPlayers];
        playerList[0] = player1;
        playerList[1] = player2;
        if (player3.isPlaying())
            playerList[2] = player3;
        if (player4.isPlaying())
            playerList[3] = player4;

        BorderPane P1W = w1.makeWindow(player1);
        BorderPane P2W = w2.makeWindow(player2);
        BorderPane P3W = w3.makeWindow(player3);
        BorderPane P4W = w4.makeWindow(player4);
        //winner label
        win.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 50));
        win.setTextFill(Color.RED);
        win.setText(win.getText().toUpperCase());
        win.setTranslateX(140); win.setTranslateY(200);

        //game over
        ImageView firework = new ImageView(new Image("fireworks.png"));
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
            fade.setToValue(0);
            fade.setDuration(Duration.millis(5000));
            fade.stop();
            //console displaying player turn and action
            TextArea console = new TextArea();
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
                    if (player.isDefeated() && turn == player.getPos()) {
                        nextTurn();
                        if (player.getPos() == 2 && numOfPlayers == 2)
                            break;
                        else if (player.getPos() == 3 && numOfPlayers == 3)
                            break;
                        else if (player.getPos() == 4 && numOfPlayers == 4)
                            break;
                    }
                    //case when player is in prison
                    if (player.isInPrison() && turn == player.getPos()) {
                        player.addPrison();
                        switch (player.getPos()) {
                            case 1 -> w1.update(player1);
                            case 2 -> w2.update(player2);
                            case 3 -> w3.update(player3);
                            case 4 -> w4.update(player4);
                        }
                        nextTurn();
                        if (player.getPos() == 2 && numOfPlayers == 2)
                            break;
                        else if (player.getPos() == 3 && numOfPlayers == 3)
                            break;
                        else if (player.getPos() == 4 && numOfPlayers == 4)
                            break;
                    }
                    if (turn == player.getPos()) {
                        if (turn == 1) {
                            translateFigure.setNode(figure1);
                            transY.setNode(figure1);
                            portal.setNode(figure1);
                            chanceMove.setNode(figure1);
                            fade.setNode(figure1);
                        } else if (turn == 2) {
                            translateFigure.setNode(figure2);
                            transY.setNode(figure2);
                            portal.setNode(figure2);
                            chanceMove.setNode(figure2);
                            fade.setNode(figure2);
                        } else if (turn == 3) {
                            translateFigure.setNode(figure3);
                            transY.setNode(figure3);
                            portal.setNode(figure3);
                            chanceMove.setNode(figure3);
                            fade.setNode(figure3);
                        } else if (turn == 4) {
                            translateFigure.setNode(figure4);
                            transY.setNode(figure4);
                            portal.setNode(figure4);
                            chanceMove.setNode(figure4);
                            fade.setNode(figure4);
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
                                    Label text = new Label();
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
                                                        console.appendText("You own this tile.");
                                                    else if (!player1.isInPrison()) {
                                                        player1.addToAccount(values[player.getTile()] / 2);
                                                        w1.addAccount(player1);
                                                        player.takeFromAccount(values[player.getTile()] / 2);
                                                        w1.update(player1);
                                                        console.appendText("Player " + player.getPos() + " paid Player " + player1.getPos() + ": " + (values[player.getTile()] / 2) + ".\n");
                                                        switch (player.getPos()) {
                                                            case 2 -> w2.removeAccount(player);
                                                            case 3 -> w3.removeAccount(player);
                                                            case 4 -> w4.removeAccount(player);
                                                        }
                                                        if (player.getAccount() <= 0) {
                                                            player.lost();
                                                            console.setText("Player " + player.getPos() + " has lost!");
                                                            switch (player.getPos()) {
                                                                case 2 -> w2.update(player2);
                                                                case 3 -> w3.update(player3);
                                                                case 4 -> w4.update(player4);
                                                            }
                                                            fade.play();
                                                            loser.play();
                                                            //find winner
                                                            fade.setOnFinished(e2 -> {
                                                                //find winner
                                                                switch(numOfPlayers){
                                                                    case 2 -> playerWinner(player1, player2);
                                                                    case 3 -> playerWinner(player1, player2, player3);
                                                                    case 4 -> playerWinner(player1, player2, player3, player4);
                                                                }
                                                            });
                                                        }
                                                   } if (player1.isInPrison()) console.appendText("Psst! Player 1 is in prison.");
                                                } else if ((player2.getOwned().contains(tiles.get(player.getTile())))) {
                                                    if (player.getPos() == 2)
                                                        console.appendText("You own this tile.");
                                                    else if (!player2.isInPrison()) {
                                                        player2.addToAccount(values[player.getTile()] / 2);
                                                        w2.addAccount(player2);
                                                        player.takeFromAccount(values[player.getTile()] / 2);
                                                        w2.update(player2);
                                                        console.appendText("Player " + player.getPos() + " paid Player " + player2.getPos() + ": " + (values[player.getTile()] / 2) + ".\n");
                                                        switch (player.getPos()) {
                                                            case 1 -> w1.removeAccount(player);
                                                            case 3 -> w3.removeAccount(player);
                                                            case 4 -> w4.removeAccount(player);
                                                        }
                                                        if (player.getAccount() <= 0) {
                                                            player.lost();
                                                            console.setText("Player " + player.getPos() + " has lost!");
                                                            switch (player.getPos()) {
                                                                case 1 -> w1.update(player1);
                                                                case 3 -> w3.update(player3);
                                                                case 4 -> w4.update(player4);
                                                            }
                                                            fade.play();
                                                            loser.play();
                                                            //find winner
                                                            fade.setOnFinished(e2 -> {
                                                                //find winner
                                                                switch(numOfPlayers){
                                                                    case 2 -> playerWinner(player1, player2);
                                                                    case 3 -> playerWinner(player1, player2, player3);
                                                                    case 4 -> playerWinner(player1, player2, player3, player4);
                                                                }
                                                            });
                                                        }
                                                    } if (player2.isInPrison()) console.appendText("Psst! Player 2 is in prison.");
                                                } else if ((player3.getOwned().contains(tiles.get(player.getTile())))) {
                                                    if (player.getPos() == 3)
                                                        console.appendText("You own this tile.");
                                                    else if (!player3.isInPrison()) {
                                                        player3.addToAccount(values[player.getTile()] / 2);
                                                        w3.addAccount(player3);
                                                        player.takeFromAccount(values[player.getTile()] / 2);
                                                        w3.update(player3);
                                                        console.appendText("Player " + player.getPos() + " paid Player " + player3.getPos() + ": " + (values[player.getTile()] / 2) + ".\n");
                                                        switch (player.getPos()) {
                                                            case 1 -> w1.removeAccount(player);
                                                            case 2 -> w2.removeAccount(player);
                                                            case 4 -> w4.removeAccount(player);
                                                        }
                                                        if (player.getAccount() <= 0) {
                                                            player.lost();
                                                            console.setText("Player " + player.getPos() + " has lost!");
                                                            switch (player.getPos()) {
                                                                case 1 -> w1.update(player1);
                                                                case 2 -> w2.update(player2);
                                                                case 4 -> w4.update(player4);
                                                            }
                                                            fade.play();
                                                            loser.play();
                                                            //find winner
                                                            fade.setOnFinished(e2 -> {
                                                                //find winner
                                                                switch(numOfPlayers){
                                                                    case 2 -> playerWinner(player1, player2);
                                                                    case 3 -> playerWinner(player1, player2, player3);
                                                                    case 4 -> playerWinner(player1, player2, player3, player4);
                                                                }
                                                            });
                                                        }
                                                      } if (player3.isInPrison()) console.appendText("Psst! Player 3 is in prison.");
                                                } else if ((player4.getOwned().contains(tiles.get(player.getTile())))) {
                                                    if (player.getPos() == 4)
                                                        console.appendText("You own this tile.");
                                                    else if (!player4.isInPrison()) {
                                                        player4.addToAccount(values[player.getTile()] / 2);
                                                        w4.addAccount(player4);
                                                        player.takeFromAccount(values[player.getTile()] / 2);
                                                        w4.update(player4);
                                                        console.appendText("Player " + player.getPos() + " paid Player " + player4.getPos() + ": " + (values[player.getTile()] / 2) + ".\n");
                                                        switch (player.getPos()) {
                                                            case 1 -> w1.removeAccount(player);
                                                            case 2 -> w2.removeAccount(player);
                                                            case 3 -> w3.removeAccount(player);
                                                        }
                                                        if (player.getAccount() <= 0) {
                                                            player.lost();
                                                            console.setText("Player " + player.getPos() + " has lost!");
                                                            switch (player.getPos()) {
                                                                case 1 -> w1.update(player1);
                                                                case 2 -> w2.update(player2);
                                                                case 3 -> w3.update(player3);
                                                            }
                                                            fade.play();
                                                            loser.play();
                                                            //find winner
                                                            fade.setOnFinished(e2 -> {
                                                                //find winner
                                                                switch(numOfPlayers){
                                                                    case 2 -> playerWinner(player1, player2);
                                                                    case 3 -> playerWinner(player1, player2, player3);
                                                                    case 4 -> playerWinner(player1, player2, player3, player4);
                                                                }
                                                            });
                                                        }
                                                      } if (player4.isInPrison()) console.appendText("Psst! Player 4 is in prison.");
                                                } else {
                                                    Label text1 = new Label("Do you want to buy \n" + tiles.get(player.getTile()) + " for " + values[player.getTile()] + "?");
                                                    text1.setTranslateX(20);
                                                    Button yes1 = new Button("Buy");
                                                    yes1.setTranslateY(30);
                                                    yes1.setTranslateX(70);
                                                    yes1.setPrefSize(60, 40);
                                                    yes1.setOnAction(actionEvent12 -> {
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
                                                        if (player.getAccount() <= 0) {
                                                            player.lost();
                                                            console.setText("Player " + player.getPos() + " has lost!");
                                                            switch (player.getPos()) {
                                                                case 1 -> w1.update(player1);
                                                                case 2 -> w2.update(player2);
                                                                case 3 -> w3.update(player3);
                                                                case 4 -> w4.update(player4);
                                                            }
                                                            fade.play();
                                                            loser.play();
                                                            //find winner
                                                            fade.setOnFinished(e2 -> {
                                                                //find winner
                                                                switch(numOfPlayers){
                                                                    case 2 -> playerWinner(player1, player2);
                                                                    case 3 -> playerWinner(player1, player2, player3);
                                                                    case 4 -> playerWinner(player1, player2, player3, player4);
                                                                }
                                                            });
                                                        }
                                                        buy.setTop(null);
                                                    });
                                                    GridPane buySetup = new GridPane();
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
                                                        w1.update(player1);
                                                        w2.update(player2);
                                                        w3.update(player3);
                                                        w4.update(player4);
                                                    });
                                                } else {
                                                    player.getExtra().remove("prison");
                                                    console.appendText("You used your prisonFree chance.");
                                                    switch (player.getPos()) {
                                                        case 1 -> w1.update(player);
                                                        case 2 -> w2.update(player);
                                                        case 3 -> w3.update(player);
                                                        case 4 -> w4.update(player);
                                                    }
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
                                                    player.toPrison();
                                                }
                                                portal.setOnFinished(e -> {
                                                    if (player.getExtra().contains("prison")){
                                                        player.getExtra().remove("prison");
                                                        console.appendText("You used your prisonFree chance.");
                                                    }
                                                     w1.update(player1);
                                                     w2.update(player2);
                                                     w3.update(player3);
                                                     w4.update(player4);
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
                                                switch (player.getPos()) {
                                                    case 1 -> w1.removeAccount(player);
                                                    case 2 -> w2.removeAccount(player);
                                                    case 3 -> w3.removeAccount(player);
                                                    case 4 -> w4.removeAccount(player);
                                                }
                                                if (player.getAccount() <= 0) {
                                                    player.lost();
                                                    console.setText("Player " + player.getPos() + " has lost!");
                                                    switch (player.getPos()) {
                                                        case 1 -> w1.update(player1);
                                                        case 2 -> w2.update(player2);
                                                        case 3 -> w3.update(player3);
                                                        case 4 -> w4.update(player4);
                                                    }
                                                    fade.play();
                                                    loser.play();
                                                    //find winner
                                                    fade.setOnFinished(e -> {
                                                        //find winner
                                                        switch(numOfPlayers){
                                                            case 2 -> playerWinner(player1, player2);
                                                            case 3 -> playerWinner(player1, player2, player3);
                                                            case 4 -> playerWinner(player1, player2, player3, player4);
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                        case "prison" -> {
                                            player.addExtra("prison");
                                            switch (player.getPos()) {
                                                case 1 -> w1.update(player1);
                                                case 2 -> w2.update(player2);
                                                case 3 -> w3.update(player3);
                                                case 4 -> w4.update(player4);
                                            }
                                        }
                                        case "tax" -> {
                                            player.addExtra("tax");
                                            switch (player.getPos()) {
                                                case 1 -> w1.update(player1);
                                                case 2 -> w2.update(player2);
                                                case 3 -> w3.update(player3);
                                                case 4 -> w4.update(player4);
                                            }
                                        }
                                    }
                                    chanceNum++;
                                    if (chanceNum == 12)
                                        chanceNum = 0;
                                    break;
                                case "TAX":
                                    if (!player.getExtra().contains("tax")) {
                                        player.takeFromAccount(values[player.getTile()]);
                                        console.appendText("\nPlayer " + player.getPos() + " paid tax: " + values[player.getTile()] + ".\n");
                                        switch (player.getPos()) {
                                            case 1 -> w1.removeAccount(player);
                                            case 2 -> w2.removeAccount(player);
                                            case 3 -> w3.removeAccount(player);
                                            case 4 -> w4.removeAccount(player);
                                        }
                                        if (player.getAccount() <= 0) {
                                            player.lost();
                                            console.setText("Player " + player.getPos() + " has lost!");
                                            switch (player.getPos()) {
                                                case 1 -> w1.update(player1);
                                                case 2 -> w2.update(player2);
                                                case 3 -> w3.update(player3);
                                                case 4 -> w4.update(player4);
                                            }
                                            fade.play();
                                            loser.play();
                                            //find winner
                                            fade.setOnFinished(e -> {
                                                //find winner
                                                switch(numOfPlayers){
                                                    case 2 -> playerWinner(player1, player2);
                                                    case 3 -> playerWinner(player1, player2, player3);
                                                    case 4 -> playerWinner(player1, player2, player3, player4);
                                                }
                                            });
                                        }
                                    } else {
                                        player.getExtra().remove("tax");
                                        console.appendText("You used your taxFree chance.");
                                        switch (player.getPos()) {
                                            case 1 -> w1.update(player);
                                            case 2 -> w2.update(player);
                                            case 3 -> w3.update(player);
                                            case 4 -> w4.update(player);
                                        }
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
                                            console.appendText("You own this tile.");
                                        else if (!player1.isInPrison()) {
                                            player1.addToAccount(values[player.getTile()] / 2);
                                            w1.addAccount(player1);
                                            player.takeFromAccount(values[player.getTile()] / 2);
                                            w1.update(player1);
                                            console.appendText("Player " + player.getPos() + " paid Player " + player1.getPos() + ": " + (values[player.getTile()] / 2) + ".\n");
                                            switch (player.getPos()) {
                                                case 2 -> w2.removeAccount(player);
                                                case 3 -> w3.removeAccount(player);
                                                case 4 -> w4.removeAccount(player);
                                            }
                                            if (player.getAccount() <= 0) {
                                                player.lost();
                                                console.setText("Player " + player.getPos() + " has lost!");
                                                switch (player.getPos()) {
                                                    case 2 -> w2.update(player2);
                                                    case 3 -> w3.update(player3);
                                                    case 4 -> w4.update(player4);
                                                }
                                                fade.play();
                                                loser.play();
                                                //find winner
                                                fade.setOnFinished(e -> {
                                                    //find winner
                                                    switch(numOfPlayers){
                                                        case 2 -> playerWinner(player1, player2);
                                                        case 3 -> playerWinner(player1, player2, player3);
                                                        case 4 -> playerWinner(player1, player2, player3, player4);
                                                    }
                                                });
                                            }
                                        } if (player1.isInPrison()) console.appendText("Psst! Player 1 is in prison.");
                                    } else if ((player2.getOwned().contains(tiles.get(player.getTile())))) {
                                        if (player.getPos() == 2)
                                            console.appendText("You own this tile.");
                                        else if (!player2.isInPrison()) {
                                            player2.addToAccount(values[player.getTile()] / 2);
                                            w2.addAccount(player2);
                                            player.takeFromAccount(values[player.getTile()] / 2);
                                            w2.update(player2);
                                            console.appendText("Player " + player.getPos() + " paid Player " + player2.getPos() + ": " + (values[player.getTile()] / 2) + ".\n");
                                            switch (player.getPos()) {
                                                case 1 -> w1.removeAccount(player);
                                                case 3 -> w3.removeAccount(player);
                                                case 4 -> w4.removeAccount(player);
                                            }
                                            if (player.getAccount() <= 0) {
                                                player.lost();
                                                console.setText("Player " + player.getPos() + " has lost!");
                                                switch (player.getPos()) {
                                                    case 1 -> w1.update(player1);
                                                    case 3 -> w3.update(player3);
                                                    case 4 -> w4.update(player4);
                                                }
                                                fade.play();
                                                loser.play();
                                                //find winner
                                                fade.setOnFinished(e -> {
                                                    //find winner
                                                    switch(numOfPlayers){
                                                        case 2 -> playerWinner(player1, player2);
                                                        case 3 -> playerWinner(player1, player2, player3);
                                                        case 4 -> playerWinner(player1, player2, player3, player4);
                                                    }
                                                });
                                            }
                                         } if (player2.isInPrison()) console.appendText("Psst! Player 2 is in prison.");
                                    } else if ((player3.getOwned().contains(tiles.get(player.getTile())))) {
                                        if (player.getPos() == 3)
                                            console.appendText("You own this tile.");
                                        else if (!player3.isInPrison()) {
                                            player3.addToAccount(values[player.getTile()] / 2);
                                            w3.addAccount(player3);
                                            player.takeFromAccount(values[player.getTile()] / 2);
                                            w3.update(player3);
                                            console.appendText("Player " + player.getPos() + " paid Player " + player3.getPos() + ": " + (values[player.getTile()] / 2) + ".\n");
                                            switch (player.getPos()) {
                                                case 1 -> w1.removeAccount(player);
                                                case 2 -> w2.removeAccount(player);
                                                case 4 -> w4.removeAccount(player);
                                            }
                                            if (player.getAccount() <= 0) {
                                                player.lost();
                                                console.setText("Player " + player.getPos() + " has lost!");
                                                switch (player.getPos()) {
                                                    case 1 -> w1.update(player1);
                                                    case 2 -> w2.update(player2);
                                                    case 4 -> w4.update(player4);
                                                }
                                                fade.play();
                                                loser.play();
                                                //find winner
                                                fade.setOnFinished(e -> {
                                                    //find winner
                                                    switch(numOfPlayers){
                                                        case 2 -> playerWinner(player1, player2);
                                                        case 3 -> playerWinner(player1, player2, player3);
                                                        case 4 -> playerWinner(player1, player2, player3, player4);
                                                    }
                                                });
                                            }
                                        } if (player3.isInPrison()) console.appendText("Psst! Player 3 is in prison.");
                                    } else if ((player4.getOwned().contains(tiles.get(player.getTile())))) {
                                        if (player.getPos() == 4)
                                            console.appendText("You own this tile.");
                                        else if (!player4.isInPrison()) {
                                            player4.addToAccount(values[player.getTile()] / 2);
                                            w4.addAccount(player4);
                                            player.takeFromAccount(values[player.getTile()] / 2);
                                            w4.update(player4);
                                            console.appendText("Player " + player.getPos() + " paid Player " + player4.getPos() + ": " + (values[player.getTile()] / 2) + ".\n");
                                            switch (player.getPos()) {
                                                case 1 -> w1.removeAccount(player);
                                                case 2 -> w2.removeAccount(player);
                                                case 3 -> w3.removeAccount(player);
                                            }
                                            if (player.getAccount() <= 0) {
                                                player.lost();
                                                console.setText("Player " + player.getPos() + " has lost!");
                                                switch (player.getPos()) {
                                                    case 1 -> w1.update(player1);
                                                    case 2 -> w2.update(player2);
                                                    case 3 -> w3.update(player3);
                                                }
                                                fade.play();
                                                loser.play();
                                                //find winner
                                                fade.setOnFinished(e -> {
                                                    //find winner
                                                    switch(numOfPlayers){
                                                        case 2 -> playerWinner(player1, player2);
                                                        case 3 -> playerWinner(player1, player2, player3);
                                                        case 4 -> playerWinner(player1, player2, player3, player4);
                                                    }
                                                });
                                            }
                                         } if (player4.isInPrison()) console.appendText("Psst! Player 4 is in prison.");
                                    } else {
                                        text = new Label("Do you want to buy \n" + tiles.get(player.getTile()) + " for " + values[player.getTile()] + "?");
                                        text.setTranslateX(20);
                                        Button yes = new Button("Buy");
                                        yes.setTranslateY(30);
                                        yes.setTranslateX(70);
                                        yes.setPrefSize(60, 40);
                                        yes.setOnAction(actionEvent12 -> {
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
                                            if (player.getAccount() <= 0) {
                                                player.lost();
                                                console.setText("Player " + player.getPos() + " has lost!");
                                                switch (player.getPos()) {
                                                    case 1 -> w1.update(player1);
                                                    case 2 -> w2.update(player2);
                                                    case 3 -> w3.update(player3);
                                                    case 4 -> w4.update(player4);
                                                }
                                                fade.play();
                                                loser.play();
                                                fade.setOnFinished(e -> {
                                                    //find winner
                                                    switch(numOfPlayers){
                                                        case 2 -> playerWinner(player1, player2);
                                                        case 3 -> playerWinner(player1, player2, player3);
                                                        case 4 -> playerWinner(player1, player2, player3, player4);
                                                    }
                                                });
                                            }
                                            buy.setTop(null);
                                        });
                                        GridPane buySetup = new GridPane();
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
                                        portal.setToX(20);
                                        portal.setToY(705);
                                        pauseMove.play();
                                        pauseMove.setOnFinished(e -> portal.play());
                                        player.addTile(-16);
                                        portalEffect.play();
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
                                            back.play();
                                            root.getChildren().removeAll(set2);
                                            root.getChildren().removeAll(set3);
                                            root.getChildren().removeAll(set4);
                                            root.getChildren().removeAll(set5);
                                            root.getChildren().removeAll(set6);
                                            root.getChildren().removeAll(c1);
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
                                            if (player.getExtra().contains("prison")){
                                                player.getExtra().remove("prison");
                                                console.appendText("You used your prisonFree chance.");
                                            }
                                            w1.update(player1);
                                            w2.update(player2);
                                            w3.update(player3);
                                            w4.update(player4);
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

    private static FadeTransition blink(ImageView firework, Group center){
        FadeTransition blink = new FadeTransition();
        blink.setNode(firework);
        blink.setDuration(Duration.millis(2000));
        blink.setToValue(0);
        blink.setCycleCount(9);
        blink.setAutoReverse(true);
        rect.setVisible(false);
        ImageView endOfGame = new ImageView(new Image("end.png"));
        endOfGame.setTranslateX(100); endOfGame.setTranslateY(250);
        endOfGame.setFitWidth(800); endOfGame.setFitHeight(300);
        endOfGame.setVisible(false);
        center.getChildren().add(endOfGame);
        blink.setOnFinished(e3 -> {
            endOfGame.setVisible(true);
            FadeTransition appear = new FadeTransition(Duration.millis(4000));
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
        ImageView firework = new ImageView(new Image("fireworks.png"));
        firework.setTranslateX(150); firework.setTranslateY(100);
        firework.setFitHeight(700); firework.setFitWidth(700);
        if (player1.getAccount() <= 0){
            win.setText("Player 2 has WON this game!");
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
        else if (player2.getAccount() <= 0){
            win.setText("Player 1 has WON this game!");
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
    }

    private static void playerWinner(Player player1, Player player2, Player player3){
        ImageView firework = new ImageView(new Image("fireworks.png"));
        firework.setTranslateX(150); firework.setTranslateY(100);
        firework.setFitHeight(700); firework.setFitWidth(700);
        if (player1.getAccount() <= 0 && player2.getAccount() <= 0){
            win.setText("Player 3 has WON this game!");
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
        else if (player1.getAccount() <= 0 && player3.getAccount() <= 0){
            win.setText("Player 2 has WON this game!");
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
        else if (player2.getAccount() <= 0 && player3.getAccount() <= 0){
            win.setText("Player 1 has WON this game!");
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
    }

    private static void playerWinner(Player player1, Player player2, Player player3, Player player4){
        ImageView firework = new ImageView(new Image("fireworks.png"));
        firework.setTranslateX(150); firework.setTranslateY(100);
        firework.setFitHeight(700); firework.setFitWidth(700);
        if (player1.getAccount() <= 0 && player2.getAccount() <= 0 && player3.getAccount() <= 0){
            win.setText("Player 4 has WON this game!");
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
        else if (player1.getAccount() <= 0 && player2.getAccount() <= 0 && player4.getAccount() <= 0){
            win.setText("Player 3 has WON this game!");
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
        else if (player1.getAccount() <= 0 && player3.getAccount() <= 0 && player4.getAccount() <= 0){
            win.setText("Player 2 has WON this game!");
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
        else if (player2.getAccount() <= 0 && player3.getAccount() <= 0 && player4.getAccount() <= 0){
            win.setText("Player 1 has WON this game!");
            center.getChildren().add(win);
            turn = 0;
            fanfare.play();
            center.getChildren().add(firework);
            FadeTransition blink = blink(firework, center);
            blink.play();
        }
    }

}