package graphics;

import extra.Player;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class PlayerWindow {
    private Label money;
    private TextArea owns;

    public BorderPane makeWindow(Player player){
        BorderPane window = new BorderPane();
        GridPane center = new GridPane();
        ImageView img = new ImageView(player.getFigure());
        img.setFitWidth(30); img.setFitHeight(60);
        img.setTranslateX(-10); img.setTranslateY(10);
        window.setLeft(img);
        Label name = new Label("extra.Player " + player.getPos());
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
        money.setText("Account: " + player.getAccount());
        owns.setText("");
        if (!player.getOwned().isEmpty()){
            for (String own : player.getOwned()){
                owns.appendText(own + "\n");
            }
        }
    }
}
