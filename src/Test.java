/*
Kristian Smolko

 */

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
import java.util.Optional;
import java.util.Random;


public class Test extends Application{
    private int turn = 1;
    private int numOfPlayers;
    private ArrayList<String> tiles = new ArrayList<>();
    private final int[] values = new int[] {200, 60, 0, 60, 100, 150, 100, 0, 100, 120, 0, 140, 0, 140, 160, 150, 180, 0, 180, 200, 0,
            220, 0, 220, 240, 150, 260, 260, 0, 280, 0, 300, 300, 0, 320, 150, 0, 350, 200, 400};

    @Override
    public void start(Stage stage) throws Exception {
        //layouts
        BorderPane root = new BorderPane();
        BorderPane border = new BorderPane();
        Group center = new Group();
        BorderPane buy = new BorderPane();
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
                player1.setFigure("figure.png");
                player2.setFigure("figure2.png");
                player3.setFigure("figure3.png");break;
            case 4 :
                player1.setFigure("figure.png");
                player2.setFigure("figure2.png");
                player3.setFigure("figure3.png");
                player4.setFigure("figure4.png");
                break;
            default :
                player1.setFigure("figure.png");
                player2.setFigure("figure2.png");
        }
        //figures
        ImageView figure1 = makeFigure(player1.getFigure());
        ImageView figure2 = makeFigure(player2.getFigure());
        ImageView figure3 = makeFigure(player3.getFigure());
        ImageView figure4 = makeFigure(player4.getFigure());
        figure1.setTranslateX(880);
        figure1.setTranslateY(705);
        figure2.setTranslateX(890);
        figure2.setTranslateY(700);
        figure3.setTranslateX(900);
        figure3.setTranslateY(695);
        figure4.setTranslateX(910);
        figure4.setTranslateY(690);
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

            int move = (finalRn * 85) + 10;
            int moveUp = (finalRn * 65) + 10;

            //replace later
            tiles = makeTiles();

            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//       ALGORITH FOR MOVING DON'T FREAKING TOUCH IT, DON'T EVEN LOOK AT IT, I WARN YOU!
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            translateFigure.setDuration(Duration.millis(2000));
            translateFigure2.setDuration(Duration.millis(2000));
            transY.setDuration(Duration.millis(2000));
            transY2.setDuration(Duration.millis(2000));
            pauseMove.setDuration(Duration.millis(500));

            TextArea console = new TextArea();
            console.setEditable(false);
            console.setMaxHeight(100);
            console.setMaxWidth(200);
            Label text;
            Button yes;
            buy.setTop(null);
            buy.setCenter(null);
            buy.setMaxSize(200,200);
            //turns
            if (turn == 1) {  //find out who is on turn
                translateFigure.stop();
                transY.stop();
                translateFigure.setNode(figure1);
                transY.setNode(figure1);
                translateFigure.setByX(0);
                translateFigure.setByY(0);
                transY.setByY(0);
                transY.setByX(0);
                pauseMove.setDuration(Duration.millis(500));
                console.setText("Player " + player1.getPos() + " turn.\n");
                double nextTile = player1.getTile() + finalRn;
                double rest;
                if (player1.getTile() >= 0 && player1.getTile() < 10){
                    if (nextTile > 10){
                        rest = ((nextTile-10) * 85 + 10);
                        translateFigure.setByX(-(move - rest));
                        transY.setByY(- (((nextTile - 10) * 65 + 10)));
                        translateFigure.play();
                        translateFigure.setOnFinished(actionEvent13 -> transY.play());
                        actionEvent.consume();
                    }
                    else{
                        if (nextTile == 10)
                            translateFigure.setByX(-(move + 20));
                        else
                            translateFigure.setByX(-move);
                        translateFigure.play();
                    }
                }
                if (player1.getTile() >= 10 && player1.getTile() < 20){
                    if (nextTile > 20){
                        rest = ((nextTile-20) * 85 + 10);
                        translateFigure.setByY(-(moveUp - ((nextTile-20) * 65) + 10));
                        transY.setByX(rest);
                        translateFigure.play();
                        translateFigure.setOnFinished(actionEvent13 -> transY.play());
                        actionEvent.consume();
                    }
                    else{
                        if (nextTile == 20)
                            transY.setByY(-(moveUp + 20));
                        transY.setByY(-moveUp);
                        transY.play();
                    }
                }
                if (player1.getTile() >= 20 && player1.getTile() < 30){
                    if (nextTile > 30){
                        rest = ((nextTile-30) * 65 + 10);
                        translateFigure.setByX(move - (((nextTile-30) * 85) + 10) + 20);
                        transY.setByY(rest);
                        translateFigure.play();
                        translateFigure.setOnFinished(actionEvent15 -> transY.play());
                    }
                    else {
                        if (player1.getTile() == 30)
                            translateFigure.setByX(move + 20);
                        translateFigure.setByX(move);
                        translateFigure.play();
                    }
                }
                if (player1.getTile() >= 30 && player1.getTile() < 40){
                    if (nextTile > 40){
                        rest = ((nextTile - 40) * 85 + 10);
                        translateFigure.setByY(moveUp - (((nextTile-40) * 65) + 10));
                        transY.setByX(-rest);
                        translateFigure.play();
                        translateFigure.setOnFinished(actionEvent1 -> transY.play());
                    }
                    else{
                        transY.setByY(moveUp);
                        transY.play();
                    }
                }
                player1.addTile(finalRn);
                if (player1.getTile() >= 40) {
                    player1.setAccount(player1.getAccount() + 200);
                    console.appendText("Player " + player1.getPos() + " has crossed Start\n");
                    player1.addTile(-40);
                }
                actionEvent.consume();
                if (tiles.get(player1.getTile()).equals("CHANCE")){
                    console.setText("Player " + player1.getPos() + " turn.\n");
                    /* here comes chance */
                }
                else if (tiles.get(player1.getTile()).equals("TAX")){
                    player1.setAccount(player1.getAccount() - values[player1.getTile()]);
                    console.appendText("\nPlayer " + player1.getPos() + " paid tax: " + values[player1.getTile()] + "\n");
                }
                else if (tiles.get(player1.getTile()).equals("JAIL")){
                    console.setText("Player " + player1.getPos() + " turn.\n");
                }
                else if (tiles.get(player1.getTile()).equals("PORTAL")){
                    //add portal later
                }
                else{
                    text = new Label("Do you want to buy \n" + tiles.get(player1.getTile()) + " for " + values[player1.getTile()] + "?");
                    yes = new Button("Buy");
                    yes.setOnAction(actionEvent12 -> {
                        player1.addOwned(tiles.get(player1.getTile()));
                        player1.setAccount(player1.getAccount() - values[player1.getTile()]);
                        console.appendText("\nPlayer " + player1.getPos() + " bought:\n" + tiles.get(player1.getTile()));
                    });
                    buy.setTop(text);
                    buy.setCenter(yes);
                }
                //buy.setBottom(new Label(player1.getAccount() + " " + player1.getOwned() + "\n" + player1.getTile()));
                console.appendText(tiles.get(player1.getTile()) + " " + figure1.getTranslateX() + "\n" + figure1.getTranslateY());
                buy.setBottom(console);
                border.setRight(buy);
            }
            else if (turn == 2) {
                translateFigure2.stop();
                transY2.stop();
                translateFigure2.setNode(figure2);
                transY2.setNode(figure2);
                translateFigure2.setByX(0);
                translateFigure2.setByY(0);
                transY2.setByY(0);
                transY2.setByX(0);
                pauseMove.setDuration(Duration.millis(500));
                console.setText("Player " + player2.getPos() + " turn.\n");
                double nextTile = player2.getTile() + finalRn;
                double rest;
                if (player2.getTile() >= 0 && player2.getTile() < 10){
                    if (nextTile > 10){
                        rest = ((nextTile-10) * 85);
                        translateFigure2.setByX(-(move - rest));
                        transY2.setByY(- (((nextTile - 10) * 65)));
                        translateFigure2.play();
                        translateFigure2.setOnFinished(actionEvent1 -> transY2.play());
                    }
                    else{
                        if (nextTile == 10)
                            translateFigure2.setByX(-(move ));
                        else
                            translateFigure2.setByX(-move);
                        translateFigure2.play();
                    }
                }
                if (player2.getTile() >= 10 && player2.getTile() < 20){
                    if (nextTile > 20){
                        rest = ((nextTile-20) * 85) ;
                        translateFigure2.setByY(-(moveUp - (((nextTile-20) * 65)) ));
                        transY2.setByX(rest);
                        translateFigure2.play();
                        translateFigure2.setOnFinished(actionEvent1 -> transY2.play());
                    }
                    else{
                        if (nextTile == 20)
                            transY2.setByY(-(moveUp + 20));
                        transY2.setByY(-moveUp);
                        transY2.play();
                    }
                }
                if (player2.getTile() >= 20 && player2.getTile() < 30){
                    if (nextTile > 30){
                        rest = ((nextTile-30) * 85 );
                        translateFigure2.setByX((move - rest) + 20);
                        transY2.setByY(((nextTile-30) * 65) );
                        translateFigure2.play();
                        translateFigure2.setOnFinished(actionEvent1 -> transY2.play());
                    }
                    else{
                        if (player2.getTile() == 30)
                            translateFigure2.setByX(move + 20);
                        translateFigure2.setByX(move);
                        translateFigure2.play();
                    }
                }
                if (player2.getTile() >= 30 && player2.getTile() < 40){
                    if (nextTile > 40){
                        rest = ((nextTile - 40) * 85) ;
                        translateFigure2.setByY((moveUp - (((nextTile-40) * 65))) );
                        transY2.setByX(-rest);
                        translateFigure2.play();
                        translateFigure2.setOnFinished(actionEvent1 -> transY2.play());
                    }
                    else{
                        transY2.setByY(moveUp + 20);
                        transY2.play();
                    }
                }
                player2.addTile(finalRn);
                if (player2.getTile() >= 40) {
                    player2.addTile(-40);
                    player2.setAccount(player2.getAccount() + 200);
                    console.appendText("Player " + player2.getPos() + " crossed Start\n");
                }
                actionEvent.consume();
                if (tiles.get(player2.getTile()).equals("CHANCE")){
                    /* here comes chance */
                }
                else if (tiles.get(player2.getTile()).equals("TAX")){
                    player2.setAccount(player2.getAccount() - values[player2.getTile()]);
                    console.appendText("Player " + player2.getPos() + " paid tax: " + values[player2.getTile()] + "\n");
                }
                else if (tiles.get(player2.getTile()).equals("JAIL")){
                    console.setText("Player " + player2.getPos() + " turn.\n");
                }
                else if (tiles.get(player2.getTile()).equals("PORTAL")){
                }
                else{
                    text = new Label("Do you want to buy \n" + tiles.get(player2.getTile()) + " for " + values[player2.getTile()] + "?");
                    yes = new Button("Buy");
                    yes.setOnAction(actionEvent12 -> {
                        player2.addOwned(tiles.get(player2.getTile()));
                        player2.setAccount(player2.getAccount() - values[player2.getTile()]);
                        console.appendText("\nPlayer " + player2.getPos() + " bought:\n" + tiles.get(player2.getTile()));
                    });
                    buy.setTop(text);
                    buy.setCenter(yes);
                }
                console.appendText(tiles.get(player2.getTile()) + " " + figure2.getTranslateX() + "\n" + figure2.getTranslateY());
                buy.setBottom(console);
            }
            else if (turn == 3) {
                translateFigure2.stop();
                transY2.stop();
                translateFigure2.setNode(figure3);
                transY2.setNode(figure3);
                translateFigure2.setByX(0);
                translateFigure2.setByY(0);
                transY2.setByY(0);
                transY2.setByX(0);
                pauseMove.setDuration(Duration.millis(500));
                double nextTile = player3.getTile() + finalRn;
                double rest;
                if (player3.getTile() >= 0 && player3.getTile() < 10){
                    if (nextTile > 10){
                        rest = ((nextTile-10) * 50) + 70;
                        translateFigure2.setByX(-(move - rest));
                        transY2.setByY(-(((nextTile - 10) * 50) + 60));
                        translateFigure2.play();
                        translateFigure2.setOnFinished(actionEvent1 -> transY2.play());
                    }
                    else{
                        if (player3.getTile() == 0)
                            translateFigure2.setByX(-(move + 30));
                        else
                            translateFigure2.setByX(-move);
                        translateFigure2.play();
                    }
                }
                if (player3.getTile() >= 10 && player3.getTile() < 20){
                    if (nextTile > 20){
                        rest = ((nextTile-20) * 50) + 70;
                        translateFigure2.setByY(-(moveUp - (((nextTile-20) * 50) + 60)));
                        transY2.setByX(rest);
                        translateFigure2.play();
                        translateFigure2.setOnFinished(actionEvent1 -> transY2.play());
                    }
                    else{
                        transY2.setByY(-moveUp);
                        transY2.play();
                    }
                }
                if (player3.getTile() >= 20 && player3.getTile() < 30){
                    if (nextTile > 30){
                        rest = ((nextTile-30) * 50) + 70;
                        translateFigure2.setByX((move - rest));
                        transY2.setByY(((nextTile-30) * 50) + 60);
                        translateFigure2.play();
                        translateFigure2.setOnFinished(actionEvent1 -> transY2.play());
                    }
                    else{
                        translateFigure2.setByX(move);
                        translateFigure2.play();
                    }
                }
                if (player3.getTile() >= 30 && player3.getTile() < 40){
                    if (nextTile > 40){
                        rest = ((nextTile - 40) * 50) + 70;
                        translateFigure2.setByY((moveUp - (((nextTile-40) * 50 ) + 60)));
                        transY2.setByX(-rest);
                        translateFigure2.play();
                        translateFigure2.setOnFinished(actionEvent1 -> transY2.play());
                    }
                    else{
                        transY2.setByY(moveUp);
                        transY2.play();
                    }
                }
                player3.addTile(finalRn);
                if (player3.getTile() >= 40)
                    player3.addTile(-40);
                actionEvent.consume();
            }
            else if (turn == 4){
                translateFigure.stop();
                transY.stop();
                translateFigure.setNode(figure4);
                transY.setNode(figure4);
                translateFigure.setByX(0);
                translateFigure.setByY(0);
                transY.setByY(0);
                transY.setByX(0);
                pauseMove.setDuration(Duration.millis(500));
                double nextTile = player4.getTile() + finalRn;
                double rest = 0;
                if (player4.getTile() >= 0 && player4.getTile() < 10){
                    if (nextTile > 10){
                        rest = ((nextTile-10) * 50) + 70;
                        translateFigure.setByX(-(move - rest));
                        transY.setByY(- (((nextTile - 10) * 50) + 60));
                        translateFigure.play();
                        translateFigure.setOnFinished(actionEvent13 -> transY.play());
                        actionEvent.consume();
                    }
                    else{
                        if (player1.getTile() == 0)
                            translateFigure.setByX(-(move + 10));
                        else
                            translateFigure.setByX(-move);
                        translateFigure.play();
                    }
                }
                if (player4.getTile() >= 10 && player4.getTile() < 20){
                    if (nextTile > 20){
                        rest = ((nextTile-20) * 50) + 70;
                        translateFigure.setByY(-(moveUp - ((nextTile-20) * 50) + 60));
                        transY.setByX(rest);
                        translateFigure.play();
                        translateFigure.setOnFinished(actionEvent13 -> transY.play());
                        actionEvent.consume();
                    }
                    else{
                        transY.setByY(-moveUp);
                        transY.play();
                    }
                }
                if (player4.getTile() >= 20 && player4.getTile() < 30){
                    if (nextTile > 30){
                        rest = ((nextTile-30) * 50) + 60;
                        translateFigure.setByX(move - (((nextTile-30) * 50 ) + 70));
                        transY.setByY(rest);
                        translateFigure.play();
                        translateFigure.setOnFinished(actionEvent15 -> transY.play());
                    }
                    else if (player1.getTile() >= 20){
                        translateFigure.setByX(move);
                        translateFigure.play();
                    }
                }
                if (player4.getTile() >= 30 && player4.getTile() < 40){
                    if (nextTile > 40){
                        rest = ((nextTile - 40) * 50) + 70;
                        translateFigure.setByY((moveUp - (((nextTile-40) * 50 ) + 60)));
                        transY.setByX(-rest);
                        translateFigure.play();
                        translateFigure.setOnFinished(actionEvent1 -> transY.play());
                    }
                    else{
                        transY.setByY(moveUp);
                        transY.play();
                    }
                }
                player4.addTile(finalRn);
                if (player4.getTile() >= 40) {
                    player4.addTile(-40);
                }
                actionEvent.consume();
            }

            nextTurn(); //next player
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
            translateFigure.stop();
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
            translateFigure2.stop();
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
            transY2.stop();
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
            transY.stop();
            actionEvent.consume();
        });

        //actions
        rect.addEventHandler(MouseEvent.MOUSE_CLICKED, mouse);
        root.getChildren().addAll(rect);

        //board
        Label lbl = makeBoard();

        //layout
        //center of game = board and figure
        // + later thing
        center.getChildren().addAll(lbl);
        if (player4.isPlaying())
            center.getChildren().add(figure4);
        if (player3.isPlaying())
            center.getChildren().add(figure3);
        center.getChildren().add(figure2);
        center.getChildren().add(figure1);
        center.getChildren().add(root);

        //game setup
        border.setCenter(center);

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

    private Label makeBoard(){
        Image board = new Image("monopoly2.png");
        ImageView boardView = new ImageView(board);
        boardView.setFitHeight(800);
        boardView.setFitWidth(1000);
        Label lbl = new Label();
        lbl.setGraphic(boardView);
        lbl.setMaxHeight(800);
        lbl.setMaxWidth(1000);
        return lbl;
    }

    private ImageView makeFigure(Image img){
        ImageView iw = new ImageView(img);
        iw.setFitWidth(70);
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
        ArrayList<String> array = new ArrayList<>();
        array.add("START");
        array.add("Dirt House");
        array.add("CHANCE");
        array.add("Hole in the ground");
        array.add("TAX");
        array.add("EXTRA");
        array.add("Cobble mine");
        array.add("CHANCE");
        array.add("Crafting Table");
        array.add("Wooden House");
        array.add("JAIL");
        array.add("Wheat Farm");
        array.add("CHANCE");
        array.add("Melon Farm");
        array.add("Pumpkin Farm");
        array.add("EXTRA");
        array.add("Sponge");
        array.add("CHANCE");
        array.add("Cauldron");
        array.add("Enchanting Table");
        array.add("FREE");
        array.add("Coal Mine");
        array.add("CHANCE");
        array.add("Iron Mine");
        array.add("Gold Mine");
        array.add("EXTRA");
        array.add("Jungle Temple");
        array.add("Desert Temple");
        array.add("CHANCE");
        array.add("Village");
        array.add("PORTAL");
        array.add("Sheep Farm");
        array.add("Pig Farm");
        array.add("CHANCE");
        array.add("Cow Farm");
        array.add("EXTRA");
        array.add("CHANCE");
        array.add("Diamond Farm");
        array.add("TAX");
        array.add("Emerald Farm");
        return array;
    }
}
