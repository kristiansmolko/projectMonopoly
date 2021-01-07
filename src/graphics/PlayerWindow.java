package graphics;

import extra.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;


public class PlayerWindow {
    private Label money;
    private TextArea owns;
    public static Media coinSound = new Media(new File("resources/coins.mp3").toURI().toString());
    public static MediaPlayer coins = new MediaPlayer(coinSound);

    public BorderPane makeWindow(Player player){
        BorderPane window = new BorderPane();
        GridPane center = new GridPane();
        ImageView img = new ImageView(player.getFigure());
        img.setFitWidth(40); img.setFitHeight(60);
        img.setTranslateX(-10); img.setTranslateY(10);
        window.setLeft(img);
        Label name = new Label("Player " + player.getPos());
        name.setMaxSize(120,20);
        money = new Label("Account: " + player.getAccount());
        money.setMaxSize(120,20);
        owns = new TextArea();
        owns.setEditable(false);
        owns.setMaxSize(120,40);
        center.addRow(0, name);
        center.addRow(1, money);
        center.addRow(2, owns);
        window.setCenter(center);
        window.setTranslateX(20);
        return window;
    }

    public void update(Player player){
        owns.setText("");
        if (!player.getOwned().isEmpty()){
            for (String own : player.getOwned()){
                owns.appendText(own + "\n");
            }
        }
    }

    public void addAccount(Player player){
        money.setText("Account: " + player.getAccount());
        Timeline timer1 = new Timeline(
                new KeyFrame(Duration.millis(2), e -> money.setTextFill(Color.LIMEGREEN)),
                new KeyFrame(Duration.millis(3), e -> money.setTextFill(Color.BLACK))
        );
        timer1.setCycleCount(400);
        timer1.play();
        coins.play();
        coins.setStopTime(Duration.millis(1000));
        timer1.setOnFinished(e -> {
            if (player.getAccount() <= 100) {
                money.setTextFill(Color.RED);
            }
        });
    }

    public void removeAccount(Player player){
        money.setText("Account: " + player.getAccount());
        Timeline timer = new Timeline(
                new KeyFrame(Duration.millis(2), e -> money.setTextFill(Color.RED)),
                new KeyFrame(Duration.millis(3), e -> money.setTextFill(Color.BLACK))
        );
        timer.setCycleCount(400);
        timer.play();
        coins.play();
        coins.setStopTime(Duration.millis(1000));
        timer.setOnFinished(e -> {
            if (player.getAccount() <= 100) {
                money.setTextFill(Color.RED);
            }
        });
    }
}
