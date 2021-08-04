package extra;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import static graphics.Graphics.noJailPane;
import static graphics.Graphics.noTaxPane;

public class Rules {
    private static final String FONT = "Times New Roman";
    private static final Font rulesFont = Font.font(FONT, FontWeight.BOLD, FontPosture.ITALIC, 16);

    private static BorderPane setRules(){
        var root = new BorderPane();
        var image = new Image("book.png");
        var bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        root.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
        return root;
    }

    private static Label setRuleName(){
        var rule = new Label("RULES");
        rule.setFont(Font.font(FONT, FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 30));
        rule.setTranslateX(350);
        rule.setTranslateY(50);
        return rule;
    }

    public static BorderPane showRules(){
        var root = setRules();
        var rules = new GridPane();
        rules.setVgap(5);
        rules.setTranslateX(160);
        rules.setTranslateY(60);

        var rule = setRuleName();

        var welcome = new Label("Welcome and before we start, we want to thank you for playing.");
        welcome.setFont(rulesFont);
        var firstRow = new Label("First of all you have to choose how many players will play.");
        firstRow.setFont(rulesFont);
        var secondRow = new Label("Then you can choose figure you want to play with.");
        secondRow.setFont(rulesFont);
        var row2 = new Label("To familiarize with the board (there are multiple tiles): ");
        row2.setFont(rulesFont);
        var startLabel = new Label("Start: you start here with 500 blocks.");
        startLabel.setFont(rulesFont);
        var row4 = new Label("You move with your figure as you roll the dice.");
        row4.setFont(rulesFont);
        var row3 = new Label("Other tiles: if no one owns it, you can buy it.");
        row3.setFont(rulesFont);
        var row5 = new Label("If you stand on tile someone owns, you pay half the price.");
        row5.setFont(rulesFont);

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
        var root = setRules();
        var rules = new GridPane();
        rules.setVgap(5);
        rules.setTranslateX(160);
        rules.setTranslateY(60);

        var rule = setRuleName();

        var windowLabel = new Label("Here you can see your figure, blocks and what do you own.");
        windowLabel.setFont(rulesFont);
        var lowMoney = new Label("In case of low budget, game will notify you with red color.");
        lowMoney.setFont(rulesFont);
        var row1 = new Label("On the right side there is panel with info about players.");
        row1.setFont(rulesFont);
        var row2 = new Label("Chance: - get or lose blocks");
        row2.setFont(rulesFont);
        var blink = new Label("If you get blocks, your account will blink green.");
        blink.setFont(rulesFont);
        var blink2 = new Label("If you lose blocks, your account will blink red.");
        blink2.setFont(rulesFont);
        var row3 = new Label("               - move forward or backwards");
        row3.setFont(rulesFont);
        var row4 = new Label("               - move to portal");
        row4.setFont(rulesFont);
        var row5 = new Label("               - move to prison");
        row5.setFont(rulesFont);
        var row6 = new Label("               - move to start");
        row6.setFont(rulesFont);
        var row7 = new Label("               - move to free tile");
        row7.setFont(rulesFont);
        var row8 = new Label("               - get special relief from tax or prison");
        row8.setFont(rulesFont);

        var blinkingPane = new BorderPane();
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
        var root = setRules();
        var rules = new GridPane();
        rules.setVgap(5);
        rules.setTranslateX(160);
        rules.setTranslateY(60);
        var rule = setRuleName();

        var firstRow = new Label("Prison: you can get there only through portal or chance.");
        firstRow.setFont(rulesFont);
        var row2 = new Label("Only one can be in prison. If someone else goes to prison, you're free!");
        row2.setFont(rulesFont);
        var row3 = new Label("You won't get or lose blocks when in prison.");
        row3.setFont(rulesFont);
        var prisonLabel = new Label("You will see jail icon, if you are in prison.");
        prisonLabel.setFont(rulesFont);
        var row4 = new Label("Out of prison: prisonFree chance or you will wait 3 rounds.");
        row4.setFont(rulesFont);
        var row5 = new Label("This is how prisonFree chance looks on panel.");
        row5.setFont(rulesFont);

        var row6 = new Label("What happens if you have to pay, but don't have enough blocks?");
        row6.setFont(rulesFont);
        var row7 = new Label("YOU LOST!");
        row7.setFont(Font.font(FONT, FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 20));
        row7.setTextFill(Color.RED);
        var row8 = new Label("But it was sure fun, so why not play again.");
        row8.setFont(rulesFont);
        var row9 = new Label("Remember, that the length of game differs from money managing skills.");
        row9.setFont(rulesFont);

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
        var start = new GridPane();
        var startLabel = new Label("You 'spawn' on this tile and every time you cross it, you gain blocks.");
        startLabel.setFont(rulesFont);
        var tile = new StackPane();
        var img = new ImageView("start.png");
        img.setFitWidth(50); img.setFitHeight(50);
        var text = new Text("START");
        text.setFont(Font.font(FONT, FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setRotate(45);
        tile.getChildren().addAll(img, text);
        start.addRow(0, tile, startLabel);
        return start;
    }

    private static GridPane makeChance(){
        var chance = new GridPane();
        var chanceText = new Label("You will find here mysteries, that can help you advance or fall.");
        chanceText.setFont(rulesFont);
        var tile = new StackPane();
        var img = new ImageView("chest.jpg");
        img.setFitWidth(50); img.setFitHeight(50);
        var text = new Text("Chance");
        text.setFont(Font.font(FONT, FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setRotate(45);
        tile.getChildren().addAll(img, text);
        chance.addRow(0, tile, chanceText);
        return chance;
    }

    private static GridPane makeVill(){
        var villager = new GridPane();
        var villText = new Label("This greedy creature wants your blocks. Beware!");
        villText.setFont(rulesFont);
        var tile = new StackPane();
        var img = new ImageView("villager.png");
        img.setFitWidth(50); img.setFitHeight(50);
        var text = new Text("TAX");
        text.setFont(Font.font(FONT, FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setTranslateY(-10);
        tile.getChildren().addAll(img, text);
        villager.addRow(0, tile, villText);
        return villager;
    }

    private static GridPane makeFree(){
        var free = new GridPane();
        var freeText = new Label("You can rest here, just enjoy this warm water.");
        freeText.setFont(rulesFont);
        var tile = new StackPane();
        var img = new ImageView("water.jpg");
        img.setFitWidth(50); img.setFitHeight(50);
        var text = new Text("FREE");
        text.setFont(Font.font(FONT, FontWeight.BOLD, FontPosture.REGULAR, 15));
        text.setRotate(45);
        tile.getChildren().addAll(img, text);
        free.addRow(0, tile, freeText);
        return free;
    }

    private static GridPane makeJail(){
        var jail = new GridPane();
        var jailText = new Label("You will be trapped here for 3 rounds!");
        jailText.setFont(rulesFont);
        var tile = new StackPane();
        var img = new ImageView("nether.png");
        img.setFitHeight(50); img.setFitWidth(50);
        tile.getChildren().addAll(img);
        jail.addRow(0, tile, jailText);
        return jail;
    }

    private static GridPane makePortal(){
        var portal = new GridPane();
        var portalText = new Label("Using some weird magic, this will take you to another tile.");
        portalText.setFont(rulesFont);
        var tile = new StackPane();
        var img = new ImageView("portal.png");
        img.setFitHeight(50); img.setFitWidth(50);
        tile.getChildren().addAll(img);
        portal.addRow(0, tile, portalText);
        return portal;
    }

    private static BorderPane makeWindow(){
        var root = new BorderPane();
        var window = setupFigure();
        var center = setupPlayer("Wood\nCarrot\nStick");
        window.setCenter(center);
        root.setLeft(window);
        return root;
    }

    private static BorderPane makeWindowJail(){
        var root = new BorderPane();
        var window = setupFigure();
        var center = setupPlayer("Wood");
        var prison = new ImageView(new Image("jaildoor.png"));
        prison.setFitWidth(30); prison.setFitHeight(30);
        prison.setTranslateX(-30);
        prison.setVisible(true);
        center.addRow(0, prison);
        window.setCenter(center);
        root.setLeft(window);
        return root;
    }

    private static BorderPane makeWindowLost(){
        var root = new BorderPane();
        var window = setupFigure();
        var center = setupPlayer("You lost!");
        var money = new Label("Account: 0");
        money.setMaxSize(120,20);
        money.setTextFill(Color.RED);
        center.getChildren().remove(1);
        center.getChildren().remove(1, 2);
        center.addRow(1, money, dirtIcon());
        window.setCenter(center);
        root.setLeft(window);
        return root;
    }

    private static BorderPane makeWindowNoPrison(){
        var root = new BorderPane();
        var window = setupFigure();
        var center = setupPlayer("Stick");
        var stack = noJailPane();
        stack.setTranslateX(-30);
        center.addRow(0, stack);
        window.setCenter(center);
        root.setLeft(window);
        return root;
    }

    private static GridPane setupPlayer(String text){
        var center = new GridPane();
        var name = new Label("Player 1");
        name.setMaxSize(120,20);
        var money = new Label("Account: 500");
        money.setMaxSize(120,20);
        var owns = new TextArea();
        owns.setEditable(false);
        owns.setMaxSize(120,40);
        owns.setMinHeight(0);
        owns.appendText(text + "\n");
        var moneyImg = new ImageView(new Image("dirt.png"));
        moneyImg.setFitHeight(20); moneyImg.setFitWidth(20);
        moneyImg.setTranslateX(-50);
        center.addRow(0, name);
        center.addRow(1, money, moneyImg);
        center.addRow(2, owns);
        return center;
    }

    private static BorderPane setupFigure(){
        var window = new BorderPane();
        var img = new ImageView(new Image("diamond steve.png"));
        img.setFitWidth(40); img.setFitHeight(60);
        img.setTranslateX(-10); img.setTranslateY(10);
        window.setLeft(img);
        window.setTranslateX(20);
        return window;
    }

    private static BorderPane makeWindowNoTax(){
        var root = new BorderPane();
        var window = setupFigure();
        var center = setupPlayer("Carrot");
        var stack = noTaxPane();
        stack.setTranslateX(-30);
        center.addRow(0, stack);
        window.setCenter(center);
        root.setLeft(window);
        return root;
    }

    private static ImageView dirtIcon(){
        var moneyImg = new ImageView(new Image("dirt.png"));
        moneyImg.setFitHeight(20); moneyImg.setFitWidth(20);
        moneyImg.setTranslateX(-50);
        return moneyImg;
    }

    private static BorderPane makeWindowBlink(Color color){
        var root = new BorderPane();
        var window = setupFigure();
        var center = setupPlayer("Carrot");
        var money = new Label("Account: 500");
        money.setMaxSize(120,20);
        center.getChildren().remove(1);
        center.getChildren().remove(1, 2);
        center.addRow(1, money, dirtIcon());
        window.setCenter(center);
        root.setLeft(window);
        var timer1 = new Timeline(
                new KeyFrame(Duration.millis(2), e -> money.setTextFill(color)),
                new KeyFrame(Duration.millis(3), e -> money.setTextFill(Color.BLACK))
        );
        timer1.setCycleCount(Animation.INDEFINITE);
        timer1.play();

        return root;
    }
}
