import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

import static extra.Rules.*;

public class Start {
    private int current = 1;
    private int picture = 0;
    private int numOfPlayers = 2;
    private final ArrayList<String> available = new ArrayList<>();
    private final ArrayList<String> figures = new ArrayList<>();

    public Start(Stage stage){
        var scene = new Scene(start(stage), 1200, 800);
        stage.setScene(scene);
        stage.setTitle("MinePoly");
        stage.show();
    }

    public BorderPane start(Stage stage){
        var startPane = new BorderPane();

        var startGame = startGame(stage);

        var rules = rulesShow(stage);

        var quit = quit();

        var buttons = new GridPane();
        buttons.setAlignment(Pos.CENTER);
        buttons.setVgap(100);
        buttons.addRow(0, startGame);
        buttons.addRow(1, rules);
        buttons.addRow(2, quit);

        startPane.setCenter(buttons);

        startPane.setBackground(background());

        return startPane;
    }

    private Background background(){
        var backgroundImage = new Image("background.jpg");
        var bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        return new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize));
    }

    private Button startGame(Stage stage){
        var start = new Button("Start");
        start.setFont(Font.font("Times New Roman", 50));
        start.setStyle("-fx-background-radius: 1em;");
        start.setPrefHeight(100);
        start.setPrefWidth(200);
        start.setOnAction(e -> {
            var scene = new Scene(playerChoose(stage), 1200, 800);
            stage.setScene(scene);
            stage.show();
        });
        return start;
    }

    private Button rulesShow(Stage stage){
        var rules = new Button("Rules");
        rules.setFont(Font.font("Times New Roman", 50));
        rules.setStyle("-fx-background-radius: 1em;");
        rules.setPrefHeight(100);
        rules.setPrefWidth(200);
        rules.setOnAction(e -> rules(stage));
        return rules;
    }

    private Button quit(){
        var quit = new Button("Quit");
        quit.setFont(Font.font("Times New Roman", 50));
        quit.setStyle("-fx-background-radius: 1em;");
        quit.setPrefHeight(100);
        quit.setPrefWidth(200);
        quit.setOnAction(e -> System.exit(0));
        return quit;
    }

    private void rules(Stage stage){
        String NEXT_PAGE = "Next page";
        var root = new BorderPane();
        root.setCenter(showRules());
        var buttons = new BorderPane();
        var gameStart = new Button("Start game");
        buttons.setLeft(gameStart);
        gameStart.setOnAction(e -> {
            BorderPane monopoly = playerChoose(stage);
            var scene = new Scene(monopoly, 1200, 800);
            stage.setScene(scene);
            stage.show();
        });
        var firstPage = new Button(NEXT_PAGE);
        var secondPage = new Button("Previous page");
        firstPage.setOnAction(e -> {
            var root1 = new BorderPane();
            root1.setCenter(showRules2());
            var moreButtons = new BorderPane();
            moreButtons.setLeft(secondPage);
            var thirdPage = new Button(NEXT_PAGE);
            moreButtons.setRight(thirdPage);
            thirdPage.setOnAction(e1 -> {
                var root2 = new BorderPane();
                root2.setCenter(showRules3());
                buttons.setRight(firstPage);
                firstPage.setText("Previous page");
                root2.setBottom(buttons);
                stage.setScene(new Scene(root2, 800, 800));
            });
            buttons.setRight(moreButtons);
            root1.setBottom(buttons);
            stage.setScene(new Scene(root1, 800, 800));
        });
        secondPage.setOnAction(e -> {
            var root2 = new BorderPane();
            root2.setCenter(showRules());
            buttons.setRight(firstPage);
            firstPage.setText(NEXT_PAGE);
            root2.setBottom(buttons);
            stage.setScene(new Scene(root2, 800, 800));
        });
        buttons.setRight(firstPage);
        root.setBottom(buttons);
        var scene = new Scene(root, 800, 800);
        stage.setScene(scene);
        stage.show();
    }

    private BorderPane playerChoose(Stage stage){
        var root = new BorderPane();

        current = 1;

        var players2 = playerImageButton(2);
        players2.setOnAction(e -> {
            figureChoose(stage);
            numOfPlayers = 2;
        });
        var players3 = playerImageButton(3);
        players3.setOnAction(e -> {
            figureChoose(stage);
            numOfPlayers = 3;
        });
        var players4 = playerImageButton(4);
        players4.setOnAction(e -> {
            figureChoose(stage);
            numOfPlayers = 4;
        });

        var buttons = new GridPane();
        buttons.setAlignment(Pos.CENTER);
        buttons.setHgap(50);
        buttons.addRow(0, players2, players3, players4);

        root.setCenter(buttons);
        root.setBackground(background());

        return root;
    }

    private Button playerImageButton(int number){
        var button = new Button();
        var imageView = new ImageView(getClass().getResource(number + "player.jpg").toExternalForm());
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);
        button.setGraphic(imageView);
        button.setMaxWidth(250);
        button.setMaxHeight(250);
        return button;
    }

    private void figureChoose(Stage stage){
        var root = new BorderPane();

        initFigureList();

        var figure = new BorderPane();

        var buttons = new GridPane();

        var left = chevron("left");
        var right = chevron("right");

        picture = 0;
        var image = new Image(available.get(picture) + ".png");
        var img = new ImageView(image);
        img.setFitWidth(300);
        img.setFitHeight(400);

        left.setOnAction(e -> {
            previousImage(available);
            img.setImage(new Image(available.get(picture) + ".png"));
        });

        right.setOnAction(e -> {
            nextImage(available);
            img.setImage(new Image(available.get(picture) + ".png"));
        });

        buttons.addRow(0, left, right);
        buttons.setTranslateX(500);
        buttons.setHgap(50);

        var another = new Button("Next");
        another.setTranslateX(700);
        another.setPrefWidth(100);
        another.setPrefHeight(100);
        another.setStyle("-fx-background-radius: 1em;");
        another.setFont(Font.font("Times New Roman", 25));
        another.setOnAction(e -> {
            if (current != numOfPlayers) {
                figures.add(available.get(picture)+".png");
                available.remove(picture);
                picture = 0;
                next();
                figure.setTop(figureTop(another));
                img.setImage(new Image(available.get(picture) + ".png"));
                root.setCenter(figure);
                if (current == numOfPlayers) another.setText("Play");
            } else {
                figures.add(available.get(picture)+".png");
                stage.setScene(new Scene(Game.createGame(figures), 1200, 800));
                stage.show();
            }
        });

        figure.setTop(figureTop(another));
        figure.setCenter(img);
        figure.setBottom(buttons);

        root.setCenter(figure);
        root.setBackground(background());

        var scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    private void initFigureList() {
        available.add("diamond steve");
        available.add("creeper");
        available.add("steve");
        available.add("zombified piglin");
    }

    private GridPane figureTop(Button another){
        var root = new GridPane();
        root.setTranslateY(50);
        root.addRow(0, playerFigure(), another);
        return root;
    }

    private Button chevron(String direction){
        var button = new Button();
        var imageView = new ImageView(getClass().getResource("chevron_" + direction + ".jpg").toExternalForm());
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        button.setGraphic(imageView);
        return button;
    }

    private Label playerFigure(){
        var label = new Label();
        label.setTranslateX(470);
        label.setMaxHeight(100);
        label.setMaxWidth(400);
        label.setText("Player's " + current + " figure");
        label.setFont(Font.font("Times New Roman", 40));
        return label;
    }

    private void next(){
        current -= -1;
    }

    private void previousImage(ArrayList<String> available){
        if(picture - 1 < 0) picture = available.size() - 1;
        else picture -= 1;
    }

    private void nextImage(ArrayList<String> available){
        if(picture + 1 == available.size()) picture = 0;
        else picture -= -1;
    }
}