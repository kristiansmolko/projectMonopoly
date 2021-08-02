/*
Kristian Smolko

 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Optional;

import static extra.Rules.*;

public class Main extends Application{
    private static final String NEXT_PAGE = "Next page";

    @Override
    public void start(Stage stage) throws Exception {
        var startGame = new Alert(Alert.AlertType.CONFIRMATION);
        startGame.setTitle("Monopoly Minecraft");
        startGame.setHeaderText("Welcome!");
        var game = new ButtonType("Start");
        var rules = new ButtonType("Rules");
        var quit = new ButtonType("Quit");
        startGame.getButtonTypes().setAll(game, rules, quit);
        Optional<ButtonType> result = startGame.showAndWait();
        if (result.get() == game){
            BorderPane root = Game.createGame();
            var scene = new Scene(root, 1200, 800);
            stage.setScene(scene);
            stage.show();
        }
        else if (result.get() == rules){
            var root = new BorderPane();
            root.setCenter(showRules());
            var buttons = new BorderPane();
            var gameStart = new Button("Start game");
            buttons.setLeft(gameStart);
            gameStart.setOnAction(e -> {
                BorderPane monopoly = Game.createGame();
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
        else if (result.get() == quit){
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
