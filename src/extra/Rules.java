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
        rules.setTranslateY(60);
        Image image = new Image("book.png");
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        root.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
        Label rule = new Label("RULES");
        rule.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 30));
        rule.setTranslateX(350);
        rule.setTranslateY(50);

        Label welcome = new Label("Welcome and before we start, we want to thank you for playing.");
        welcome.setFont(font);
        Label firstRow = new Label("First of all you have to choose how many players will play.");
        firstRow.setFont(font);
        Label secondRow = new Label("You can't choose figures or who will go first.");
        secondRow.setFont(font);
        Label row2 = new Label("To familiarize with the board (there are multiple tiles): ");
        row2.setFont(font);
        Label startLabel = new Label("Start: you start here with 500 money.");
        startLabel.setFont(font);
        Label row8 = new Label("You move with your figure as you roll the dice.");
        row8.setFont(font);
        Label row7 = new Label("Other tiles: if noone owns it, you can buy it.");
        row7.setFont(font);
        Label row9 = new Label("If you stand on tile someone owns, you pay half the price.");
        row9.setFont(font);

        rules.addRow(0, welcome);
        rules.addRow(1, firstRow);
        rules.addRow(2, secondRow);
        rules.addRow(3, new Label(""));
        rules.addRow(4, row2);
        rules.addRow(5, new Label(""));
        rules.addRow(6, makeStart());
        rules.addRow(7, makeChance());
        rules.addRow(8, makeVill());
        rules.addRow(9, makeFree());
        rules.addRow(10, makeJail());
        rules.addRow(11, makePortal());
        rules.addRow(12, new Label(""));
        rules.addRow(13, row7);
        rules.addRow(14, new Label(""));
        rules.addRow(15, startLabel);
        rules.addRow(16, row8);
        rules.addRow(17, row9);
        root.setTop(rule);
        root.setCenter(rules);
        return root;
    }

    public static BorderPane showRules2(){
        BorderPane root = new BorderPane();
        GridPane rules = new GridPane();
        rules.setVgap(5);
        rules.setTranslateX(160);
        rules.setTranslateY(60);
        Image image = new Image("book.png");
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        root.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));

        Label firstRow = new Label("Prison: you can get there only through portal or chance.");
        firstRow.setFont(font);
        Label row2 = new Label("Only one can be in prison. If someone else goes to prison, you're free!");
        row2.setFont(font);
        Label row3 = new Label("You won't get or lose money when in prison.");
        row3.setFont(font);
        Label prisonLabel = new Label("You will see jail icon, if you are in prison.");
        prisonLabel.setFont(font);
        Label windowLabel = new Label("Here you can see your figure, money and what do you own.");
        windowLabel.setFont(font);
        Label row4 = new Label("Out of prison: prisonFree chance or you will wait 3 rounds.");
        row4.setFont(font);
        Label lowMoney = new Label("In case of low budget, game will notify you with red color.");
        lowMoney.setFont(font);
        Label row1 = new Label("On the right side there is panel with info about players.");
        row1.setFont(font);
        Label row12 = new Label("What happens if you have to pay, but don't have the required money?");
        row12.setFont(font);
        Label last = new Label("YOU LOST!");
        last.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20));
        last.setTextFill(Color.RED);
        Label lastRow = new Label("But it was sure fun, so why not play again.");
        lastRow.setFont(font);

        Label rule = new Label("RULES");
        rule.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 30));
        rule.setTranslateX(350);
        rule.setTranslateY(50);

        rules.addRow(0, row1);
        rules.addRow(1, new Label(""));
        rules.addRow(2, makeWindow());
        rules.addRow(3, windowLabel);
        rules.addRow(4, lowMoney);
        rules.addRow(5, new Label(""));
        rules.addRow(6, firstRow);
        rules.addRow(7, makeWindowJail());
        rules.addRow(8, prisonLabel);
        rules.addRow(9, row2);
        rules.addRow(10, row3);
        rules.addRow(11, row4);
        rules.addRow(12, new Label(""));
        rules.addRow(13, row12);
        rules.addRow(14, last);
        rules.addRow(15, makeWindowLost());
        rules.addRow(16, new Label(""));
        rules.addRow(17, lastRow);
        root.setTop(rule);
        root.setCenter(rules);
        return root;
    }

    public static BorderPane showRules3(){
        BorderPane root = new BorderPane();
        GridPane rules = new GridPane();
        rules.setVgap(5);
        rules.setTranslateX(160);
        rules.setTranslateY(60);
        Image image = new Image("book.png");
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        root.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
        Label rule = new Label("RULES");
        rule.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 30));
        rule.setTranslateX(350);
        rule.setTranslateY(50);

        return root;
    }

    private static GridPane makeStart(){
        GridPane start = new GridPane();
        Label startLabel = new Label("You 'spawn' on this tile and every time you cross it, you gain money.");
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
        Label chanceText = new Label("You will find here mysteries, that can help you advance or fall.");
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
        Label freeText = new Label("You can rest here, just enjoy this warm water.");
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
        Label portalText = new Label("Using some weird magic, this will take you to another tile.");
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
        img.setFitWidth(40); img.setFitHeight(60);
        img.setTranslateX(-10); img.setTranslateY(10);
        window.setLeft(img);
        Label name = new Label("Player 1");
        name.setMaxSize(120,20);
        Label money = new Label("Account: 500");
        money.setMaxSize(120,20);
        TextArea owns = new TextArea();
        owns.setEditable(false);
        owns.setMaxSize(120,40);
        owns.setMinHeight(0);
        center.addRow(0, name);
        center.addRow(1, money);
        center.addRow(2, owns);
        window.setCenter(center);
        window.setTranslateX(20);
        root.setLeft(window);
        return root;
    }

    private static BorderPane makeWindowJail(){
        BorderPane root = new BorderPane();
        BorderPane window = new BorderPane();
        GridPane center = new GridPane();
        ImageView img = new ImageView("diamond steve.png");
        img.setFitWidth(40); img.setFitHeight(60);
        img.setTranslateX(-10); img.setTranslateY(10);
        window.setLeft(img);
        Label name = new Label("Player 1");
        name.setMaxSize(120,20);
        Label money = new Label("Account: 500");
        money.setMaxSize(120,20);
        ImageView prison = new ImageView(new Image("jaildoor.png"));
        prison.setFitWidth(30); prison.setFitHeight(30);
        prison.setTranslateX(-30);
        prison.setVisible(true);
        TextArea owns = new TextArea();
        owns.setEditable(false);
        owns.setMaxSize(120,40);
        center.addRow(0, name, prison);
        center.addRow(1, money);
        center.addRow(2, owns);
        window.setCenter(center);
        window.setTranslateX(20);
        root.setLeft(window);
        return root;
    }

    private static BorderPane makeWindowLost(){
        BorderPane root = new BorderPane();
        BorderPane window = new BorderPane();
        GridPane center = new GridPane();
        ImageView img = new ImageView(new Image("diamond steve.png"));
        img.setFitWidth(40); img.setFitHeight(60);
        img.setTranslateX(-10); img.setTranslateY(10);
        window.setLeft(img);
        Label name = new Label("Player 1");
        name.setMaxSize(120,20);
        Label money = new Label("Account: 0");
        money.setMaxSize(120,20);
        money.setTextFill(Color.RED);
        TextArea owns = new TextArea();
        owns.setEditable(false);
        owns.setMaxSize(120,40);
        owns.setMinHeight(0);
        owns.appendText("You lost!");
        center.addRow(0, name);
        center.addRow(1, money);
        center.addRow(2, owns);
        window.setCenter(center);
        window.setTranslateX(20);
        root.setLeft(window);
        return root;
    }
}
