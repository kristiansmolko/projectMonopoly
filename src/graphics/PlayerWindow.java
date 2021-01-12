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
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.File;


public class PlayerWindow {
    private Label money;
    ImageView prison = new ImageView(new Image("jaildoor.png"));
    StackPane notax = noTax();
    StackPane noprison = noPrison();
    private TextArea owns;
    private GridPane center = new GridPane();
    public static Media coinSound = new Media(new File("resources/coins.mp3").toURI().toString());
    public static MediaPlayer coins = new MediaPlayer(coinSound);

    public BorderPane makeWindow(Player player){
        BorderPane window = new BorderPane();
        ImageView img = new ImageView(player.getFigure());
        img.setFitWidth(40); img.setFitHeight(60);
        img.setTranslateX(-10); img.setTranslateY(10);
        window.setLeft(img);
        Label name = new Label("Player " + player.getPos());
        name.setMaxSize(120,20);
        money = new Label("Account: " + player.getAccount());
        money.setMaxSize(120,20);
        prison.setFitWidth(25); prison.setFitHeight(25);
        prison.setTranslateX(-70);
        prison.setVisible(false);
        notax.setTranslateX(-70);
        notax.setVisible(true);
        noprison.setTranslateX(-70);
        noprison.setVisible(false);
        owns = new TextArea();
        owns.setEditable(false);
        owns.setMaxSize(120,40);
        center.addRow(0, name, notax, noprison, prison);
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
        prison.setVisible(player.isInPrison());
        noprison.setVisible(player.getExtra().contains("prison"));
        notax.setVisible(player.getExtra().contains("tax"));
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

    private StackPane noPrison(){
        StackPane stack = new StackPane();
        ImageView noprison = new ImageView(new Image("jaildoor.png"));
        ImageView remove = new ImageView(new Image("remove.png"));
        noprison.setFitWidth(25); noprison.setFitHeight(25);
        remove.setFitWidth(25); remove.setFitHeight(25);
        stack.getChildren().addAll(noprison, remove);
        return stack;
    }

    private StackPane noTax(){
        StackPane stack = new StackPane();
        ImageView noprison = new ImageView(new Image("villager.png"));
        noprison.setFitWidth(25); noprison.setFitHeight(25);
        Label tax = new Label("TAX");
        tax.setFont(Font.font(10));
        tax.setTranslateY(-5);
        Line line = new Line(-10,10,10,-10);
        line.setStroke(Color.RED);
        line.setStrokeWidth(2);
        stack.getChildren().addAll(noprison, tax, line);
        return stack;
    }
}
