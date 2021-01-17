package extra;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
        Label secondRow = new Label("Then you can choose figure you want to play with.");
        secondRow.setFont(font);
        Label row2 = new Label("To familiarize with the board (there are multiple tiles): ");
        row2.setFont(font);
        Label startLabel = new Label("Start: you start here with 500 blocks.");
        startLabel.setFont(font);
        Label row4 = new Label("You move with your figure as you roll the dice.");
        row4.setFont(font);
        Label row3 = new Label("Other tiles: if noone owns it, you can buy it.");
        row3.setFont(font);
        Label row5 = new Label("If you stand on tile someone owns, you pay half the price.");
        row5.setFont(font);

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
        rules.addRow(13, row3);
        rules.addRow(14, new Label(""));
        rules.addRow(15, startLabel);
        rules.addRow(16, row4);
        rules.addRow(17, row5);
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

        Label windowLabel = new Label("Here you can see your figure, blocks and what do you own.");
        windowLabel.setFont(font);
        Label lowMoney = new Label("In case of low budget, game will notify you with red color.");
        lowMoney.setFont(font);
        Label row1 = new Label("On the right side there is panel with info about players.");
        row1.setFont(font);
        Label row2 = new Label("Chance: - get or lose blocks");
        row2.setFont(font);
        Label blink = new Label("If you get blocks, your account will blink green.");
        blink.setFont(font);
        Label blink2 = new Label("If you lose blocks, your account will blink red.");
        blink2.setFont(font);
        Label row3 = new Label("               - move forward or backwards");
        row3.setFont(font);
        Label row4 = new Label("               - move to portal");
        row4.setFont(font);
        Label row5 = new Label("               - move to prison");
        row5.setFont(font);
        Label row6 = new Label("               - move to start");
        row6.setFont(font);
        Label row7 = new Label("               - move to free tile");
        row7.setFont(font);
        Label row8 = new Label("               - get special relief from tax or prison");
        row8.setFont(font);
        Label rule = new Label("RULES");
        rule.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 30));
        rule.setTranslateX(350);
        rule.setTranslateY(50);
        BorderPane blinkingPane = new BorderPane();
        blinkingPane.setLeft(makeWindowBlink(Color.LIMEGREEN));
        blinkingPane.setRight(makeWindowBlink(Color.RED));


        rules.addRow(0, row1);
        rules.addRow(1, new Label(""));
        rules.addRow(2, makeWindow());
        rules.addRow(3, windowLabel);
        rules.addRow(4, lowMoney);
        rules.addRow(5, new Label(""));
        rules.addRow(6, blinkingPane);
        rules.addRow(7, blink);
        rules.addRow(8, blink2);
        rules.addRow(9, new Label(""));
        rules.addRow(10, row2);
        rules.addRow(11, row3);
        rules.addRow(12, row4);
        rules.addRow(13, row5);
        rules.addRow(14, row6);
        rules.addRow(15, row7);
        rules.addRow(16, row8);
        rules.addRow(17, new Label(""));
        rules.addRow(18, makeWindowNoTax());

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

        Label firstRow = new Label("Prison: you can get there only through portal or chance.");
        firstRow.setFont(font);
        Label row2 = new Label("Only one can be in prison. If someone else goes to prison, you're free!");
        row2.setFont(font);
        Label row3 = new Label("You won't get or lose blocks when in prison.");
        row3.setFont(font);
        Label prisonLabel = new Label("You will see jail icon, if you are in prison.");
        prisonLabel.setFont(font);
        Label row4 = new Label("Out of prison: prisonFree chance or you will wait 3 rounds.");
        row4.setFont(font);
        Label row5 = new Label("This is how prisonFree chance looks on panel.");
        row5.setFont(font);

        Label row6 = new Label("What happens if you have to pay, but don't have enough blocks?");
        row6.setFont(font);
        Label row7 = new Label("YOU LOST!");
        row7.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20));
        row7.setTextFill(Color.RED);
        Label row8 = new Label("But it was sure fun, so why not play again.");
        row8.setFont(font);
        Label row9 = new Label("Remember, that the length of game differs from money managing skills.");
        row9.setFont(font);

        rules.addRow(0, firstRow);
        rules.addRow(1, makeWindowJail());
        rules.addRow(2, prisonLabel);
        rules.addRow(3, row2);
        rules.addRow(4, row3);
        rules.addRow(5, new Label(""));
        rules.addRow(6, row4);
        rules.addRow(7, makeWindowNoPrison());
        rules.addRow(8, row5);
        rules.addRow(9, new Label(""));
        rules.addRow(10, row6);
        rules.addRow(11, row7);
        rules.addRow(12, makeWindowLost());
        rules.addRow(13, new Label(""));
        rules.addRow(14, row8);
        rules.addRow(15, row9);

        root.setTop(rule);
        root.setCenter(rules);
        return root;
    }

    private static GridPane makeStart(){
        GridPane start = new GridPane();
        Label startLabel = new Label("You 'spawn' on this tile and every time you cross it, you gain blocks.");
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
        Label villText = new Label("This greedy creature wants your blocks. Beware!");
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
        owns.appendText("Wood\n");
        owns.appendText("Iron Sword\n");
        owns.appendText("Carrot\n");
        ImageView moneyImg = new ImageView(new Image("dirt.png"));
        moneyImg.setFitHeight(20); moneyImg.setFitWidth(20);
        moneyImg.setTranslateX(-50);
        center.addRow(0, name);
        center.addRow(1, money, moneyImg);
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
        ImageView moneyImg = new ImageView(new Image("dirt.png"));
        moneyImg.setFitHeight(20); moneyImg.setFitWidth(20);
        moneyImg.setTranslateX(-50);
        owns.setMaxSize(120,40);
        center.addRow(0, name, prison);
        center.addRow(1, money, moneyImg);
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
        ImageView moneyImg = new ImageView(new Image("dirt.png"));
        moneyImg.setFitHeight(20); moneyImg.setFitWidth(20);
        moneyImg.setTranslateX(-63);
        owns.appendText("You lost!");
        center.addRow(0, name);
        center.addRow(1, money, moneyImg);
        center.addRow(2, owns);
        window.setCenter(center);
        window.setTranslateX(20);
        root.setLeft(window);
        return root;
    }

    private static BorderPane makeWindowNoPrison(){
        BorderPane root = new BorderPane();
        BorderPane window = new BorderPane();
        GridPane center = new GridPane();
        ImageView img = new ImageView(new Image("diamond steve.png"));
        img.setFitWidth(40); img.setFitHeight(60);
        img.setTranslateX(-10); img.setTranslateY(10);

        StackPane stack = new StackPane();
        ImageView noprison = new ImageView(new Image("jaildoor.png"));
        ImageView remove = new ImageView(new Image("remove.png"));
        noprison.setFitWidth(25); noprison.setFitHeight(25);
        remove.setFitWidth(25); remove.setFitHeight(25);
        stack.getChildren().addAll(noprison, remove);
        stack.setTranslateX(-30);
        window.setLeft(img);
        Label name = new Label("Player 1");
        name.setMaxSize(120,20);
        Label money = new Label("Account: 500");
        money.setMaxSize(120,20);
        TextArea owns = new TextArea();
        owns.setEditable(false);
        owns.setMaxSize(120,40);
        owns.setMinHeight(0);
        ImageView moneyImg = new ImageView(new Image("dirt.png"));
        moneyImg.setFitHeight(20); moneyImg.setFitWidth(20);
        moneyImg.setTranslateX(-50);
        center.addRow(0, name, stack);
        center.addRow(1, money, moneyImg);
        center.addRow(2, owns);
        window.setCenter(center);
        window.setTranslateX(20);
        root.setLeft(window);
        return root;
    }

    private static BorderPane makeWindowNoTax(){
        BorderPane root = new BorderPane();
        BorderPane window = new BorderPane();
        GridPane center = new GridPane();
        ImageView img = new ImageView(new Image("diamond steve.png"));
        img.setFitWidth(40); img.setFitHeight(60);
        img.setTranslateX(-10); img.setTranslateY(10);

        StackPane stack = new StackPane();
        ImageView noprison = new ImageView(new Image("villager.png"));
        noprison.setFitWidth(25); noprison.setFitHeight(25);
        Line line = new Line(-10,10,10,-10);
        line.setStroke(Color.RED);
        line.setStrokeWidth(2);
        Label tax = new Label("TAX");
        tax.setFont(Font.font(10));
        tax.setTranslateY(-5);
        stack.getChildren().addAll(noprison, tax, line);
        stack.setTranslateX(-30);
        window.setLeft(img);
        Label name = new Label("Player 1");
        name.setMaxSize(120,20);
        Label money = new Label("Account: 500");
        money.setMaxSize(120,20);
        TextArea owns = new TextArea();
        owns.setEditable(false);
        owns.setMaxSize(120,40);
        owns.setMinHeight(0);
        ImageView moneyImg = new ImageView(new Image("dirt.png"));
        moneyImg.setFitHeight(20); moneyImg.setFitWidth(20);
        moneyImg.setTranslateX(-50);
        center.addRow(0, name, stack);
        center.addRow(1, money, moneyImg);
        center.addRow(2, owns);
        window.setCenter(center);
        window.setTranslateX(20);
        root.setLeft(window);
        return root;
    }

    private static BorderPane makeWindowBlink(Color color){
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
        ImageView moneyImg = new ImageView(new Image("dirt.png"));
        moneyImg.setFitHeight(20); moneyImg.setFitWidth(20);
        moneyImg.setTranslateX(-50);
        center.addRow(0, name);
        center.addRow(1, money, moneyImg);
        center.addRow(2, owns);
        window.setCenter(center);
        window.setTranslateX(20);
        root.setLeft(window);

        money.setText("Account: 500");
        Timeline timer1 = new Timeline(
                new KeyFrame(Duration.millis(2), e -> money.setTextFill(color)),
                new KeyFrame(Duration.millis(3), e -> money.setTextFill(Color.BLACK))
        );
        timer1.setCycleCount(Animation.INDEFINITE);
        timer1.play();

        return root;
    }
}
