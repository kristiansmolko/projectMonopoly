package extra;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
    private static final Font font = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 16);

    public static BorderPane showRules(){
        BorderPane root = new BorderPane();
        GridPane rules = new GridPane();
        rules.setVgap(5);
        rules.setTranslateX(160);
        rules.setTranslateY(80);
        Image image = new Image("book.png");
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        root.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
        Label rule = new Label("RULES");
        rule.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 30));
        rule.setTranslateX(350);
        rule.setTranslateY(50);

        Label welcome = new Label("Welcome and before we start, we want to thank you for playing");
        welcome.setFont(font);
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

        rules.addRow(0, welcome);
        rules.addRow(1, firstRow);
        rules.addRow(2, row2);
        rules.addRow(3, makeStart());
        rules.addRow(4, makeChance());
        rules.addRow(5, makeVill());
        rules.addRow(6, makeFree());
        rules.addRow(7, makeJail());
        rules.addRow(8, makePortal());
        rules.addRow(9, row9);
        rules.addRow(10, row10);
        rules.addRow(11, makeWindow());
        rules.addRow(12, new Label(""));
        rules.addRow(13, row12);
        rules.addRow(14, last);
        root.setTop(rule);
        root.setCenter(rules);
        return root;
    }

    public static BorderPane showRules2(){
        BorderPane root = new BorderPane();
        GridPane rules = new GridPane();
        rules.setVgap(5);
        rules.setTranslateX(160);
        rules.setTranslateY(80);
        Image image = new Image("book.png");
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        root.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
        Label firstRow = new Label("Prison: you can get there only through portal or chance");
        firstRow.setFont(font);
        Label row2 = new Label("Only one can be in prison");
        row2.setFont(font);
        Label row3 = new Label("You won't get or lose money when in prison");
        row3.setFont(font);
        Label row4 = new Label("Free: you don't have to do anything here, just relax!");
        row4.setFont(font);
        Label row5 = new Label("");

        Label rule = new Label("RULES");
        rule.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 30));
        rule.setTranslateX(350);
        rule.setTranslateY(50);

        rules.addRow(0, firstRow);
        rules.addRow(1, row2);
        rules.addRow(2, row3);
        rules.addRow(3, row4);
        root.setTop(rule);
        root.setCenter(rules);
        return root;
    }

    private static GridPane makeStart(){
        GridPane start = new GridPane();
        Label startLabel = new Label("You 'spawn' on this tile and every time you cross it, you gain money");
        startLabel.setFont(font);
        StackPane tile = new StackPane();
        ImageView img = new ImageView("start.png");
        img.setFitWidth(50); img.setFitHeight(50);
        Text text = new Text("START");
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setRotate(45);
        tile.getChildren().addAll(img, text);
        start.addRow(0, tile, startLabel);
        return start;
    }

    private static GridPane makeChance(){
        GridPane chance = new GridPane();
        Label chanceText = new Label("You will find here mysteries, that can help you advance or fall");
        chanceText.setFont(font);
        StackPane tile = new StackPane();
        ImageView img = new ImageView("chest.jpg");
        img.setFitWidth(50); img.setFitHeight(50);
        Text text = new Text("Chance");
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setRotate(45);
        tile.getChildren().addAll(img, text);
        chance.addRow(0, tile, chanceText);
        return chance;
    }

    private static GridPane makeVill(){
        GridPane villager = new GridPane();
        Label villText = new Label("This greedy creature wants your money. Beware!");
        villText.setFont(font);
        StackPane tile = new StackPane();
        ImageView img = new ImageView("villager.png");
        img.setFitWidth(50); img.setFitHeight(50);
        Text text = new Text("TAX");
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setTranslateY(-10);
        tile.getChildren().addAll(img, text);
        villager.addRow(0, tile, villText);
        return villager;
    }

    private static GridPane makeFree(){
        GridPane free = new GridPane();
        Label freeText = new Label("You can rest here, just enjoy this warm water");
        freeText.setFont(font);
        StackPane tile = new StackPane();
        ImageView img = new ImageView("water.jpg");
        img.setFitWidth(50); img.setFitHeight(50);
        Text text = new Text("FREE");
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setRotate(45);
        tile.getChildren().addAll(img, text);
        free.addRow(0, tile, freeText);
        return free;
    }

    private static GridPane makeJail(){
        GridPane jail = new GridPane();
        Label jailText = new Label("You will be trapped here for 3 rounds!");
        jailText.setFont(font);
        StackPane tile = new StackPane();
        ImageView img = new ImageView("nether.png");
        img.setFitHeight(50); img.setFitWidth(50);
        tile.getChildren().addAll(img);
        jail.addRow(0, tile, jailText);
        return jail;
    }

    private static GridPane makePortal(){
        GridPane portal = new GridPane();
        Label portalText = new Label("Using some weird magic, this will take you to another tile");
        portalText.setFont(font);
        StackPane tile = new StackPane();
        ImageView img = new ImageView("portal.png");
        img.setFitHeight(50); img.setFitWidth(50);
        tile.getChildren().addAll(img);
        portal.addRow(0, tile, portalText);
        return portal;
    }

    private static BorderPane makeWindow(){
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
