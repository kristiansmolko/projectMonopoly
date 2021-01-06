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

import static extra.Rules.showRules;

public class Main extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Alert startGame = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType game = new ButtonType("Start");
        ButtonType rules = new ButtonType("Rules");
        ButtonType quit = new ButtonType("Quit");
        startGame.getButtonTypes().setAll(game, rules, quit);
        Optional<ButtonType> result = startGame.showAndWait();
        if (result.get() == game){
            BorderPane root = Game.createGame();
            Scene scene = new Scene(root, 1200, 800);
            stage.setScene(scene);
            stage.show();
        }
        else if (result.get() == rules){
            BorderPane root = new BorderPane();
            root.setCenter(showRules());
            Button gameStart = new Button("Start game");
            root.setBottom(gameStart);
            gameStart.setOnAction(e -> {
                BorderPane monopoly = Game.createGame();
                Scene scene = new Scene(monopoly, 1200, 800);
                stage.setScene(scene);
                stage.show();
            });
            Scene scene = new Scene(root, 800, 800);
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
