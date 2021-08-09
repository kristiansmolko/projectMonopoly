package graphics;

import extra.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;

import static graphics.Graphics.noJailPane;
import static graphics.Graphics.noTaxPane;


public class PlayerWindow {
    private Label money;
    private final ImageView prison = new ImageView(new Image("jaildoor.png"));
    private final StackPane notax = noTaxPane();
    private final StackPane noprison = noJailPane();
    private TextArea owns;
    private final GridPane center = new GridPane();
    public static Media coinSound = new Media(new File("resources/coins.mp3").toURI().toString());
    ImageView moneyImg = new ImageView(new Image("dirt.png"));
    public static MediaPlayer coins = new MediaPlayer(coinSound);

    public BorderPane makeWindow(Player player){
        var window = new BorderPane();
        var img = new ImageView(player.getFigure());
        img.setFitWidth(40); img.setFitHeight(60);
        img.setTranslateX(-10); img.setTranslateY(10);
        window.setLeft(img);
        var name = new Label(player.getName());
        name.setMaxSize(120,20);
        money = new Label("Account: " + player.getAccount());
        money.setMaxSize(120,20);
        prison.setFitWidth(25); prison.setFitHeight(25);
        prison.setTranslateX(-70);
        prison.setVisible(false);
        notax.setTranslateX(-70);
        notax.setVisible(false);
        noprison.setTranslateX(-70);
        noprison.setVisible(false);
        owns = new TextArea();
        owns.setEditable(false);
        owns.setMaxSize(120,40);
        moneyImg.setFitHeight(20); moneyImg.setFitWidth(20);
        moneyImg.setTranslateX(-50);
        center.addRow(0, name, notax, noprison, prison);
        center.addRow(1, money, moneyImg);
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
        prison.setVisible(player.isInPrison());
        noprison.setVisible(player.getExtra().contains("prison"));
        notax.setVisible(player.getExtra().contains("tax"));
    }

    public void addAccount(Player player){
        money.setText("Account: " + player.getAccount());
        if (player.getAccount() >= 1000)
            moneyImg.setTranslateX(-45);
        else if (player.getAccount() < 100 && player.getAccount() > 0)
            moneyImg.setTranslateX(-57);
        else if (player.getAccount() == 0)
            moneyImg.setTranslateX(-63);
        else
            moneyImg.setTranslateX(-50);

        var timer1 = new Timeline(
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
        if (player.getAccount() >= 1000)
            moneyImg.setTranslateX(-46);
        else if (player.getAccount() < 100 && player.getAccount() > 0)
            moneyImg.setTranslateX(-57);
        else if (player.getAccount() == 0)
            moneyImg.setTranslateX(-63);
        else if (player.getAccount() < 0 && (player.getAccount() > -100))
            moneyImg.setTranslateX(-52);
        else if (player.getAccount() <= -100)
            moneyImg.setTranslateX(-45);
        else
            moneyImg.setTranslateX(-50);

        var timer = new Timeline(
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
