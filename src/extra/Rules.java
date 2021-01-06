package extra;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Rules {
    private static Font font = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 16);
    public static BorderPane showRules(){
        BorderPane root = new BorderPane();
        GridPane rules = new GridPane();
        rules.setVgap(5);
        rules.setTranslateX(160);
        rules.setTranslateY(80);
        Image image = new Image("book.png");
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        root.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
        Label welcome = new Label("Welcome and before we start, we want to thank you for playing");
        welcome.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 20));
        welcome.setTranslateX(130);
        welcome.setTranslateY(50);
        Label firstRow = new Label("First of all you have to choose how many players will play");
        firstRow.setFont(font);
        Label row2 = new Label("To familiarize with the board (there are multiple tiles): ");
        row2.setFont(font);
        Label row9 = new Label("Any other tile on board you can buy, but if someone already owns it:");
        row9.setFont(font);
        Label row10 = new Label("YOU have to pay! With your money. Where can you see your money?");
        row10.setFont(font);
        Label row12 = new Label("What happens if you have to pay, but don't have the required money?");
        row12.setFont(font);
        Label last = new Label("YOU LOST! (but it was sure fun, so why not play again?");
        last.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20));
        last.setTextFill(Color.RED);

        rules.addRow(0, firstRow);
        rules.addRow(1, row2);
        rules.addRow(2, makeStart());
        rules.addRow(3, makeChance());
        rules.addRow(4, makeVill());
        rules.addRow(5, makeFree());
        rules.addRow(6, makeJail());
        rules.addRow(7, makePortal());
        rules.addRow(8, row9);
        rules.addRow(9, row10);
        rules.addRow(10, makeWindow());
        rules.addRow(11, row12);
        rules.addRow(12, last);
        root.setTop(welcome);
        root.setCenter(rules);
        return root;
    }

    private static BorderPane makeStart(){
        BorderPane start = new BorderPane();
        Label startLabel = new Label("You 'spawn' on this tile and every time you cross it, you gain money");
        startLabel.setFont(font);
        StackPane tile = new StackPane();
        ImageView img = new ImageView("start.png");
        img.setFitWidth(50); img.setFitHeight(50);
        Text text = new Text("START");
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setRotate(45);
        tile.getChildren().addAll(img, text);
        start.setLeft(tile);
        start.setCenter(startLabel);
        return start;
    }

    private static BorderPane makeChance(){
        BorderPane chance = new BorderPane();
        Label chanceText = new Label("You will find here mysteries, that can help you advance or fall");
        chanceText.setFont(font);
        StackPane tile = new StackPane();
        ImageView img = new ImageView("chest.jpg");
        img.setFitWidth(50); img.setFitHeight(50);
        Text text = new Text("Chance");
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setRotate(45);
        tile.getChildren().addAll(img, text);
        chance.setLeft(tile);
        chance.setCenter(chanceText);
        return chance;
    }

    private static BorderPane makeVill(){
        BorderPane villager = new BorderPane();
        Label villText = new Label("This greedy creature wants your money. Beware!");
        villText.setFont(font);
        StackPane tile = new StackPane();
        ImageView img = new ImageView("villager.png");
        img.setFitWidth(50); img.setFitHeight(50);
        Text text = new Text("TAX");
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setTranslateY(-10);
        tile.getChildren().addAll(img, text);
        villager.setLeft(tile);
        villager.setCenter(villText);
        return villager;
    }

    private static BorderPane makeFree(){
        BorderPane free = new BorderPane();
        Label freeText = new Label("You can rest here, just enjoy this warm water");
        freeText.setFont(font);
        StackPane tile = new StackPane();
        ImageView img = new ImageView("water.jpg");
        img.setFitWidth(50); img.setFitHeight(50);
        Text text = new Text("FREE");
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setRotate(45);
        tile.getChildren().addAll(img, text);
        free.setLeft(tile);
        free.setCenter(freeText);
        return free;
    }

    private static BorderPane makeJail(){
        BorderPane jail = new BorderPane();
        Label jailText = new Label("You will be trapped here for 3 rounds!");
        jailText.setFont(font);
        StackPane tile = new StackPane();
        ImageView img = new ImageView("nether.png");
        img.setFitHeight(50); img.setFitWidth(50);
        tile.getChildren().addAll(img);
        jail.setLeft(tile);
        jail.setCenter(jailText);
        return jail;
    }

    private static BorderPane makePortal(){
        BorderPane portal = new BorderPane();
        Label portalText = new Label("Using some weird magic, this will take you to another tile");
        portalText.setFont(font);
        StackPane tile = new StackPane();
        ImageView img = new ImageView("portal.png");
        img.setFitHeight(50); img.setFitWidth(50);
        tile.getChildren().addAll(img);
        portal.setLeft(tile);
        portal.setCenter(portalText);
        return portal;
    }

    public static BorderPane makeWindow(){
        BorderPane root = new BorderPane();
        BorderPane window = new BorderPane();
        GridPane center = new GridPane();
        ImageView img = new ImageView(new Image("diamond steve.png"));
        img.setFitWidth(30); img.setFitHeight(60);
        img.setTranslateX(-10); img.setTranslateY(10);
        window.setLeft(img);
        Label name = new Label("Player 1");
        name.setMaxSize(120,20);
        Label money = new Label("Account: 500");
        money.setMaxSize(120,20);
        TextArea owns = new TextArea();
        owns.setEditable(false);
        owns.setMaxSize(65,40);
        owns.setMinHeight(0);
        center.addRow(0, name);
        center.addRow(1, money);
        center.addRow(2, owns);
        window.setCenter(center);
        Label windowLabel = new Label("Here you can see your figure, money and what do you own");
        windowLabel.setFont(font);
        root.setLeft(window);
        root.setCenter(windowLabel);
        return root;
    }
}
