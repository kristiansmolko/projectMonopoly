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
            BorderPane buttons = new BorderPane();
            Button gameStart = new Button("Start game");
            buttons.setLeft(gameStart);
            gameStart.setOnAction(e -> {
                BorderPane monopoly = Game.createGame();
                Scene scene = new Scene(monopoly, 1200, 800);
                stage.setScene(scene);
                stage.show();
            });
            Button firstPage = new Button("Next page");
            Button secondPage = new Button("Previous page");
            firstPage.setOnAction(e -> {
                BorderPane root1 = new BorderPane();
                root1.setCenter(showRules2());
                BorderPane moreButtons = new BorderPane();
                moreButtons.setLeft(secondPage);
                Button thirdPage = new Button("Next page");
                moreButtons.setRight(thirdPage);
                thirdPage.setOnAction(e1 -> {
                    BorderPane root2 = new BorderPane();
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
                BorderPane root2 = new BorderPane();
                root2.setCenter(showRules());
                buttons.setRight(firstPage);
                firstPage.setText("Next page");
                root2.setBottom(buttons);
                stage.setScene(new Scene(root2, 800, 800));
            });
            buttons.setRight(firstPage);
            root.setBottom(buttons);
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
