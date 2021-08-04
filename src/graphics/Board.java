package graphics;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Board {
    private ImageView background;
    private int color = 0;
    private int bgrndNum = 1;

    private static final String FONT = "Times New Roman";

    public BorderPane makeBoard(){
        var board = new BorderPane();
        board.setMaxSize(1000,800);
        board.setPadding(new Insets(0));
        var bottom = new GridPane();
        var top = new GridPane();
        var left = new GridPane();
        var right = new GridPane();

        var backgroundImage = new Image("background.jpg");
        background = new ImageView(backgroundImage);
        background.setFitWidth(800); background.setFitHeight(600);

        StackPane startTile = makeStart();
        StackPane free = makeFree();
        StackPane jail = makeJail();
        StackPane wood = makeTile("wood.png", "Wood", 60);
        StackPane stick = makeTile("stick.png", "Stick", 60);
        StackPane crafting = makeTile("crafting.png", "Crafting Table", 80);
        StackPane stonePick = makeTile("cobpick.png", "Stone Pickaxe", 100);
        StackPane cobble = makeTile("cobble.jpg", "Cobblestone", 100);
        StackPane ironOre = makeTile("ironore.jpeg", "Iron Ore", 120);
        StackPane hoe = makeTile("hoe.png", "Iron Hoe", 140);
        StackPane wheat = makeTile("wheat.png", "Wheat", 140);
        StackPane carrot = makeTile("carrot.png", "Carrot", 160);
        StackPane arrow = makeTile("arrow.png", "Arrow", 180);
        StackPane bow = makeTile("bow.png", "Bow", 200);
        StackPane iron = makeTile("ironpick.png", "Iron Pickaxe", 220);
        StackPane gold = makeTile("gold.png", "Gold Bar" , 220);
        StackPane redstone = makeTile("redstone.png", "Redstone", 240);
        StackPane sword = makeTile("ironsword.png", "Iron Sword", 260);
        StackPane shield = makeTile("shield.png", "Shield", 260);
        StackPane armor = makeTile("armor.png", "Iron Armor", 280);
        StackPane ench = makeTile("enchanting.png", "Enchanting" , 300);
        StackPane brew = makeTile("brewing.png", "Brewing", 300);
        StackPane shulker = makeTile("shulker.png", "Shulker Box", 320);
        StackPane diamond = makeTile("diamond.png", "Diamond",350);
        StackPane emerald = makeTile("emerald.png", "Emerald", 400);

        right.addRow(1, ench);
        right.addRow(2, brew);
        right.addRow(3, shulker);
        right.addRow(4, makeChance());
        right.addRow(5, diamond);
        right.addRow(6, emerald);
        left.addRow(2, arrow);
        left.addRow(3, makeChance());
        left.addRow(1, bow);
        left.addRow(4, carrot);
        left.addRow(5, wheat);
        left.addRow(6, hoe);
        top.addRow(0, free, iron, makeVill(), gold, redstone, sword, shield, makeChance(), armor, makePortal());
        bottom.addRow(0, jail, ironOre, makeChance(), cobble, stonePick, crafting, makeVill(), stick, wood, startTile);
        board.setTop(top);
        board.setLeft(left);
        board.setRight(right);
        board.setCenter(background);
        board.setBottom(bottom);
        board.setOnMouseClicked(e -> {bgrndNum++; changeBackground(); board.setCenter(background);});
        return board;
    }

    private StackPane makeTile(String fileName, String name, int value){
        var tile = new StackPane();
        var back = new Rectangle();
        back.setWidth(100); back.setHeight(100);
        if (color++ == 0)
            back.setFill(Color.LIMEGREEN);
        else
            back.setFill(Color.GREEN);
        if (color > 1)
            color = 0;
        var img = new ImageView(new Image(fileName));
        img.setFitHeight(80); img.setFitWidth(80);
        img.setTranslateY(10);
        var desc = new Text(name);
        desc.setTranslateY(-40);
        desc.setFont(Font.font(FONT, FontWeight.BOLD, FontPosture.REGULAR, 15));
        var price = new Text(String.valueOf(value));
        price.setFont(Font.font(FONT, FontWeight.BOLD, FontPosture.ITALIC, 15));
        price.setTranslateY(40);
        price.setTranslateX(35);
        tile.setMaxHeight(100); tile.setMaxWidth(100);
        tile.getChildren().addAll(back, img,desc, price);
        return tile;
    }

    private static StackPane makeStart(){
        var tile = new StackPane();
        var img = new ImageView("start.png");
        img.setFitWidth(100); img.setFitHeight(100);
        var text = new Text("START");
        text.setFont(Font.font(FONT, FontWeight.BOLD, FontPosture.REGULAR, 30));
        text.setRotate(45);
        tile.getChildren().addAll(img, text);
        return tile;
    }

    private static StackPane makeChance(){
        var tile = new StackPane();
        var img = new ImageView("chest.jpg");
        img.setFitWidth(100); img.setFitHeight(100);
        var text = new Text("Chance");
        text.setFont(Font.font(FONT, FontWeight.BOLD, FontPosture.REGULAR, 30));
        text.setRotate(45);
        tile.getChildren().addAll(img, text);
        return tile;
    }

    private static StackPane makeVill(){
        var tile = new StackPane();
        var img = new ImageView("villager.png");
        img.setFitWidth(100); img.setFitHeight(100);
        var text = new Text("TAX");
        text.setFont(Font.font(FONT, FontWeight.BOLD, FontPosture.REGULAR, 30));
        text.setTranslateY(-20);
        tile.getChildren().addAll(img, text);
        return tile;
    }

    private static StackPane makeFree(){
        var tile = new StackPane();
        var img = new ImageView("water.jpg");
        img.setFitWidth(100); img.setFitHeight(100);
        var text = new Text("FREE");
        text.setFont(Font.font(FONT, FontWeight.BOLD, FontPosture.REGULAR, 30));
        text.setRotate(45);
        tile.getChildren().addAll(img, text);
        return tile;
    }

    private StackPane makeJail(){
        var tile = new StackPane();
        var img = new ImageView("nether.png");
        img.setFitHeight(100); img.setFitWidth(100);
        tile.getChildren().addAll(img);
        return tile;
    }

    private StackPane makePortal(){
        var tile = new StackPane();
        var img = new ImageView("portal.png");
        img.setFitHeight(100); img.setFitWidth(100);
        tile.getChildren().addAll(img);
        return tile;
    }

    private void changeBackground(){
        if (bgrndNum > 11)
            bgrndNum = 1;

        switch (bgrndNum) {
            case 1 -> background.setImage(new Image("background.jpg"));
            case 2 -> background.setImage(new Image("background2.jpg"));
            case 3 -> background.setImage(new Image("background3.jpg"));
            case 4 -> background.setImage(new Image("background4.jpg"));
            case 5 -> background.setImage(new Image("background5.jpg"));
            case 6 -> background.setImage(new Image("background6.jpg"));
            case 7 -> background.setImage(new Image("background7.jpg"));
            case 8 -> background.setImage(new Image("background8.jpg"));
            case 9 -> background.setImage(new Image("background9.jpg"));
            case 10 -> background.setImage(new Image("background10.jpg"));
            case 11 -> background.setImage(new Image("background11.jpg"));
        }
        background.setFitWidth(800); background.setFitHeight(600);
    }
}
