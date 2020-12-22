/*
Kristian Smolko

 */

import extra.Player;
import graphics.Board;
import graphics.PlayerWindow;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class Test extends Application{
    private int turn = 1;
    private int numOfPlayers;
    private ArrayList<String> tiles = new ArrayList<>();
    private final int[] values = new int[] {200, 60, 60, 100, 80, 100, 100, 0, 120, 0, 140, 140, 160, 0, 180, 200,
            0, 220, 100, 220, 240, 260, 260, 0, 280, 0, 300, 300, 320, 0, 350, 400};

    @Override
    public void start(Stage stage) throws Exception {
        //layouts
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
        PauseTransition pauseMove = new PauseTransition();
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
            case 3 :
                player1.setFigure("diamond steve.png");
                player2.setFigure("creeper.png");
                player3.setFigure("steve.png");break;
            case 4 :
                player1.setFigure("diamond steve.png");
                player2.setFigure("creeper.png");
                player3.setFigure("steve.png");
                player4.setFigure("zombified piglin.png");
                break;
            default :
                player1.setFigure("diamond steve.png");
                player2.setFigure("creeper.png");
        }
        //figures
        ImageView figure1 = makeFigure(player1.getFigure());
        ImageView figure2 = makeFigure(player2.getFigure());
        ImageView figure3 = makeFigure(player3.getFigure());
        ImageView figure4 = makeFigure(player4.getFigure());
        figure1.setTranslateX(920); figure1.setTranslateY(705);
        figure2.setTranslateX(925); figure2.setTranslateY(710);
        figure3.setTranslateX(915); figure3.setTranslateY(705);
        figure4.setTranslateX(920); figure4.setTranslateY(710);

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
            portal.setDuration(Duration.millis(5000));
            pauseMove.setDuration(Duration.millis(500));
            //console displaying player turn and action
            TextArea console = new TextArea();
            console.setEditable(false);
            console.setMaxHeight(100);
            console.setMaxWidth(200);
            Label text;
            Button yes;
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

            for (Player player : playerList){
                if (turn == player.getPos()) {
                    if (turn == 1) {
                        translateFigure.setNode(figure1);
                        transY.setNode(figure1);
                        portal.setNode(figure1);
                    } else if (turn == 2) {
                        translateFigure.setNode(figure2);
                        transY.setNode(figure2);
                        portal.setNode(figure2);
                    } else if (turn == 3) {
                        translateFigure.setNode(figure3);
                        transY.setNode(figure3);
                        portal.setNode(figure3);
                    } else if (turn == 4) {
                        translateFigure.setNode(figure4);
                        transY.setNode(figure4);
                        portal.setNode(figure4);
                    }

                    translateFigure.setByX(0);
                    translateFigure.setByY(0);
                    transY.setByY(0);
                    transY.setByX(0);
                    console.setText("Player " + player.getPos() + " turn.\n");
                    double nextTile = player.getTile() + finalRn;
                    double rest;
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
                                    pauseMove.play();
                                    portal.setToX(20);
                                    portal.setToY(705);
                                    portal.play();
                                    player.addTile(-16);
                                    player.toPrison();
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
                            case 1:
                                w1.update(player);
                                break;
                            case 2:
                                w2.update(player);
                                break;
                            case 3:
                                w3.update(player);
                                break;
                            case 4:
                                w4.update(player);
                                break;
                        }
                    }
                    actionEvent.consume();
                    if (tiles.get(player.getTile()).equals("CHANCE")) {
                        console.setText("Player " + player.getPos() + " turn.\n");
                        /* here comes chance */
                    } else if (tiles.get(player.getTile()).equals("TAX")) {
                        player.takeFromAccount(values[player.getTile()]);
                        console.appendText("\nPlayer " + player.getPos() + " paid tax: " + values[player.getTile()] + "\n");
                        switch (player.getPos()) {
                            case 1:
                                w1.update(player);
                                break;
                            case 2:
                                w2.update(player);
                                break;
                            case 3:
                                w3.update(player);
                                break;
                            case 4:
                                w4.update(player);
                                break;
                        }
                    } else if (tiles.get(player.getTile()).equals("JAIL")) {
                        console.setText("Player " + player.getPos() + " turn.\n");
                        if (player.isInPrison()) {
                            console.setText("You are in prison! Remaining rounds: " + (3 - player.getPrisonCount()));
                        }
                    } else {
                        if (player1.getOwned().contains(tiles.get(player.getTile()))) {
                            player1.addToAccount(values[player.getTile()] / 2);
                            player.takeFromAccount(values[player.getTile()] / 2);
                            w1.update(player1);
                            switch (player.getPos()) {
                                case 2:
                                    w2.update(player);
                                    break;
                                case 3:
                                    w3.update(player);
                                    break;
                                case 4:
                                    w4.update(player);
                                    break;
                            }
                            console.appendText("Player " + player.getPos() + " paid Player " + player1.getPos() + ": " + (values[player.getTile()] / 2) + "\n");
                        } else if (player2.getOwned().contains(tiles.get(player.getTile()))) {
                            player2.addToAccount(values[player.getTile()] / 2);
                            player.takeFromAccount(values[player.getTile()] / 2);
                            w2.update(player2);
                            switch (player.getPos()) {
                                case 1:
                                    w1.update(player);
                                    break;
                                case 3:
                                    w3.update(player);
                                    break;
                                case 4:
                                    w4.update(player);
                                    break;
                            }
                            console.appendText("Player " + player.getPos() + " paid Player " + player2.getPos() + ": " + (values[player.getTile()] / 2) + "\n");
                        } else if (player3.getOwned().contains(tiles.get(player.getTile()))) {
                            player3.addToAccount(values[player.getTile()] / 2);
                            player.takeFromAccount(values[player.getTile()] / 2);
                            w3.update(player3);
                            switch (player.getPos()) {
                                case 1:
                                    w1.update(player);
                                    break;
                                case 2:
                                    w2.update(player);
                                    break;
                                case 4:
                                    w4.update(player);
                                    break;
                            }
                            console.appendText("Player " + player.getPos() + " paid Player " + player3.getPos() + ": " + (values[player.getTile()] / 2) + "\n");
                        } else if (player4.getOwned().contains(tiles.get(player.getTile()))) {
                            player4.addToAccount(values[player.getTile()] / 2);
                            player1.takeFromAccount(values[player.getTile()] / 2);
                            w4.update(player4);
                            switch (player.getPos()) {
                                case 1:
                                    w1.update(player);
                                    break;
                                case 2:
                                    w2.update(player);
                                    break;
                                case 3:
                                    w3.update(player);
                                    break;
                            }
                            console.appendText("Player " + player.getPos() + " paid Player " + player4.getPos() + ": " + (values[player.getTile()] / 2) + "\n");
                        } else {
                            text = new Label("Do you want to buy \n" + tiles.get(player.getTile()) + " for " + values[player.getTile()] + "?");
                            text.setTranslateX(20);
                            yes = new Button("Buy");
                            yes.setTranslateY(30);
                            yes.setPrefSize(60, 40);
                            yes.setOnAction(actionEvent12 -> {
                                player.addOwned(tiles.get(player.getTile()));
                                player.takeFromAccount(values[player.getTile()]);
                                console.appendText("\nPlayer " + player.getPos() + " bought:\n" + tiles.get(player.getTile()));
                                switch (player.getPos()) {
                                    case 1:
                                        w1.update(player);
                                        break;
                                    case 2:
                                        w2.update(player);
                                        break;
                                    case 3:
                                        w3.update(player);
                                        break;
                                    case 4:
                                        w4.update(player);
                                        break;
                                }
                                buy.setTop(null);
                                buy.setCenter(null);
                            });
                            buy.setTop(text);
                            buy.setCenter(yes);
                        }
                    }
                    //buy.setBottom(new Label(player1.getAccount() + " " + player1.getOwned() + "\n" + player1.getTile()));
                    console.appendText(tiles.get(player.getTile()));
                    buy.setBottom(console);
                }
            }


            console.setTranslateY(150);
            nextTurn(); //next player
            if (player1.isInPrison()){
                player1.addPrison();
                nextTurn();
            }
            if (player2.isInPrison()){
                player2.addPrison();
                nextTurn();
            }
            if (player3.isInPrison()){
                player3.addPrison();
                nextTurn();
            }
            if (player4.isInPrison()){
                player4.addPrison();
                nextTurn();
            }
        });

        //when move with figure is completed
        translateFigure.setOnFinished(actionEvent -> {
            back.play();
            root.getChildren().removeAll(set2);
            root.getChildren().removeAll(set3);
            root.getChildren().removeAll(set4);
            root.getChildren().removeAll(set5);
            root.getChildren().removeAll(set6);
            root.getChildren().removeAll(c1);
            //translateFigure.stop();
            actionEvent.consume();
        });

        translateFigure2.setOnFinished(actionEvent -> {
            back.play();
            root.getChildren().removeAll(set2);
            root.getChildren().removeAll(set3);
            root.getChildren().removeAll(set4);
            root.getChildren().removeAll(set5);
            root.getChildren().removeAll(set6);
            root.getChildren().removeAll(c1);
            //translateFigure2.stop();
            actionEvent.consume();
        });

        transY2.setOnFinished(actionEvent -> {
            back.play();
            root.getChildren().removeAll(set2);
            root.getChildren().removeAll(set3);
            root.getChildren().removeAll(set4);
            root.getChildren().removeAll(set5);
            root.getChildren().removeAll(set6);
            root.getChildren().removeAll(c1);
            //transY2.stop();
            actionEvent.consume();
        });

        transY.setOnFinished(actionEvent -> {
            back.play();
            root.getChildren().removeAll(set2);
            root.getChildren().removeAll(set3);
            root.getChildren().removeAll(set4);
            root.getChildren().removeAll(set5);
            root.getChildren().removeAll(set6);
            root.getChildren().removeAll(c1);
            //transY.stop();
            actionEvent.consume();
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

        Scene scene = new Scene(border, 1200,800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private int randomNum(){
        Random rnd = new Random();
        return (rnd.nextInt(6)+1);
    }

    private Circle get1(Rectangle rect, int x){
        Circle c = new Circle();
        c.setCenterX(rect.getX() + x + (rect.getWidth()/2));
        c.setCenterY(rect.getY() + (rect.getHeight()/2));
        c.setRadius(8);
        c.setFill(Color.BLACK);
        return c;
    }

    private Circle[] get2(Rectangle rect, int x){
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

    private Circle[] get3(Rectangle rect, int x) {
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

    private Circle[] get4(Rectangle rect, int x) {
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

    private Circle[] get5(Rectangle rect, int x) {
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

    private Circle[] get6(Rectangle rect, int x){
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

    private ParallelTransition parallel(Rectangle rect, int x){
        TranslateTransition translate = new TranslateTransition(Duration.millis(500));
        translate.setByX(x);
        translate.setDuration(Duration.millis(1000));
        RotateTransition rotate = new RotateTransition();
        rotate.setByAngle(360);
        rotate.setCycleCount(2);
        return new ParallelTransition(rect, translate, rotate);
    }

    private Rectangle makeDice(){
        Rectangle rect = new Rectangle(120,500,50,50);
        Stop[] stops = new Stop[] {new Stop(0,Color.BLUE), new Stop(1, Color.AQUAMARINE)};
        LinearGradient linear = new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE, stops);
        rect.setFill(linear);
        rect.setStroke(Color.WHITE);
        rect.setArcWidth(15);
        rect.setArcHeight(15);
        return rect;
    }

    private TranslateTransition moveBack(Rectangle rect, int x){
        TranslateTransition back = new TranslateTransition();
        back.setByX(-x);
        back.setDuration(Duration.millis(10));
        back.setNode(rect);
        return back;
    }

    private ImageView makeFigure(Image img){
        ImageView iw = new ImageView(img);
        iw.setFitWidth(50);
        iw.setFitHeight(90);
        return iw;
    }

    private Alert makeDialog(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Players");
        alert.setHeaderText("Choose how many players");
        alert.setContentText("Choose");
        return alert;
    }

    private void nextTurn(){
        if (turn == numOfPlayers)
            turn = 1;
        else
            turn -= -1;
    }

    private ArrayList<String> makeTiles(){
        String[] tiles = new String[] {"START", "Wood", "Stick", "TAX", "Crafting Table", "Stone Pickaxe",
                "Cobblestone", "CHANCE", "Iron Ore", "JAIL", "Iron Hoe", "Wheat", "Carrot", "CHANCE", "Arrow",
                "Bow", "FREE", "Iron Pickaxe", "TAX", "Gold Bar", "Redstone", "Iron Sword", "Shield", "CHANCE",
                "Iron Armor", "PORTAL", "Enchanting Table", "Brew Stand", "Shulker Box", "CHANCE", "Diamond", "Emerald"};
        return new ArrayList<>(Arrays.asList(tiles));
    }
}
