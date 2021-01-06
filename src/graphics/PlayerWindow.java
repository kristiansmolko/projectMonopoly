package graphics;

import extra.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class PlayerWindow {
    private Label money;
    private TextArea owns;
    private Timeline timer;

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
        timer = new Timeline(
                new KeyFrame(Duration.millis(1), e -> money.setTextFill(Color.GREEN)),
                new KeyFrame(Duration.millis(2), e -> money.setTextFill(Color.BLACK))
        );
        timer.setCycleCount(200);
        timer.play();
    }

    public void removeAccount(Player player){
        money.setText("Account: " + player.getAccount());
        timer = new Timeline(
                new KeyFrame(Duration.millis(2), e -> money.setTextFill(Color.RED)),
                new KeyFrame(Duration.millis(3), e -> money.setTextFill(Color.BLACK))
        );
        timer.setCycleCount(200);
        timer.play();
    }
}
