import extra.Player;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

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

        var rules = new Button("Rules");

        var quit = new Button("Quit");

        var buttons = new GridPane();
        buttons.setVgap(40);
        buttons.addRow(0, startGame);
        buttons.addRow(1, rules);
        buttons.addRow(2, quit);

        startPane.setCenter(buttons);

        return startPane;
    }

    private Button startGame(Stage stage){
        var start = new Button("Start");
        start.setOnAction(e -> {
            var scene = new Scene(playerChoose(stage), 1200, 800);
            stage.setScene(scene);
            stage.show();
        });
        return start;
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
        buttons.setHgap(50);
        buttons.addRow(0, players2, players3, players4);

        root.setCenter(buttons);

        return root;
    }

    private Button playerImageButton(int number){
        var button = new Button();
        var imageView = new ImageView(getClass().getResource(number + "player.png").toExternalForm());
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        button.setGraphic(imageView);
        button.setMaxWidth(150);
        button.setMaxHeight(150);
        return button;
    }

    private void figureChoose(Stage stage){
        var root = new BorderPane();

        available.add("diamond steve");
        available.add("creeper");
        available.add("steve");
        available.add("zombified piglin");

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

        figure.setTop(playerFigure());
        figure.setCenter(img);
        figure.setBottom(buttons);

        var another = new Button("Next");
        another.setTranslateX(1100);
        another.setMaxWidth(50);
        another.setMaxHeight(50);
        another.setOnAction(e -> {
            if (current != numOfPlayers) {
                figures.add(available.get(picture)+".png");
                available.remove(picture);
                picture = 0;
                next();
                figure.setTop(playerFigure());
                img.setImage(new Image(available.get(picture) + ".png"));
                root.setCenter(figure);
                if (current == numOfPlayers) another.setText("Play");
            } else {
                figures.add(available.get(picture)+".png");
                System.out.println(figures.size());
                stage.setScene(new Scene(Game.createGame(figures), 1200, 800));
                stage.show();
            }
        });

        root.setCenter(figure);
        root.setBottom(another);

        var scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    private Button chevron(String direction){
        var button = new Button();
        var imageView = new ImageView(getClass().getResource("chevron_" + direction + ".png").toExternalForm());
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        button.setGraphic(imageView);
        return button;
    }

    private Label playerFigure(){
        var label = new Label();
        label.setTranslateX(470);
        label.setMaxHeight(50);
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
