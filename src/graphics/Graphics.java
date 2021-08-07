package graphics;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Graphics {

    public static Circle get1(Rectangle rect, int x){
        var c = new Circle();
        c.setCenterX(rect.getX() + x + (rect.getWidth()/2));
        c.setCenterY(rect.getY() + (rect.getHeight()/2));
        c.setRadius(8);
        c.setFill(Color.BLACK);
        return c;
    }

    public static Circle[] get2(Rectangle rect, int x){
        var c1 = new Circle();
        var c2 = new Circle();
        c1.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 10);
        c1.setCenterY(rect.getY() + (rect.getHeight()/2) - 10);
        c1.setRadius(8);
        c1.setFill(Color.BLACK);
        c2.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 10);
        c2.setCenterY(rect.getY() + (rect.getHeight()/2) + 10);
        c2.setRadius(8);
        c2.setFill(Color.BLACK);
        return new Circle[] {c1,c2};
    }

    public static Circle[] get3(Rectangle rect, int x) {
        var c1 = new Circle();
        var c2 = new Circle();
        var c3 = new Circle();
        var set = new Circle[] {c1,c2,c3};
        c1.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 15);
        c1.setCenterY(rect.getY() + (rect.getHeight()/2) - 15);
        c2.setCenterX(rect.getX() + x + (rect.getWidth()/2));
        c2.setCenterY(rect.getY() + (rect.getHeight()/2));
        c3.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 15);
        c3.setCenterY(rect.getY() + (rect.getHeight()/2) + 15);
        for (Circle c : set){
            c.setFill(Color.BLACK);
            c.setRadius(6);
        }
        return set;
    }

    public static Circle[] get4(Rectangle rect, int x) {
        var c1 = new Circle();
        var c2 = new Circle();
        var c3 = new Circle();
        var c4 = new Circle();
        var set = new Circle[] {c1,c2,c3,c4};
        c1.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 13);
        c1.setCenterY(rect.getY() + (rect.getHeight()/2) - 13);
        c2.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 13);
        c2.setCenterY(rect.getY() + (rect.getHeight()/2) + 13);
        c3.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 13);
        c3.setCenterY(rect.getY() + (rect.getHeight()/2) + 13);
        c4.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 13);
        c4.setCenterY(rect.getY() + (rect.getHeight()/2) - 13);
        for (Circle c : set){
            c.setFill(Color.BLACK);
            c.setRadius(6);
        }
        return set;
    }

    public static Circle[] get5(Rectangle rect, int x) {
        var c1 = new Circle();
        var c2 = new Circle();
        var c3 = new Circle();
        var c4 = new Circle();
        var c5 = new Circle();
        var set = new Circle[] {c1,c2,c3,c4,c5};
        c1.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 14);
        c1.setCenterY(rect.getY() + (rect.getHeight()/2) - 14);
        c2.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 14);
        c2.setCenterY(rect.getY() + (rect.getHeight()/2) + 14);
        c3.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 14);
        c3.setCenterY(rect.getY() + (rect.getHeight()/2) + 14);
        c4.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 14);
        c4.setCenterY(rect.getY() + (rect.getHeight()/2) - 14);
        c5.setCenterX(rect.getX() + x + (rect.getWidth()/2));
        c5.setCenterY(rect.getY() + (rect.getHeight()/2));
        for (Circle c : set){
            c.setFill(Color.BLACK);
            c.setRadius(6);
        }
        return set;
    }

    public static Circle[] get6(Rectangle rect, int x){
        var c1 = new Circle();
        var c2 = new Circle();
        var c3 = new Circle();
        var c4 = new Circle();
        var c5 = new Circle();
        var c6 = new Circle();
        var set = new Circle[] {c1,c2,c3,c4,c5,c6};
        c1.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 15);
        c1.setCenterY(rect.getY() + (rect.getHeight()/2) - 15);
        c2.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 15);
        c2.setCenterY(rect.getY() + (rect.getHeight()/2));
        c3.setCenterX(rect.getX() + x + (rect.getWidth()/2) + 15);
        c3.setCenterY(rect.getY() + (rect.getHeight()/2) + 15);
        c4.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 15);
        c4.setCenterY(rect.getY() + (rect.getHeight()/2) - 15);
        c5.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 15);
        c5.setCenterY(rect.getY() + (rect.getHeight()/2));
        c6.setCenterX(rect.getX() + x + (rect.getWidth()/2) - 15);
        c6.setCenterY(rect.getY() + (rect.getHeight()/2) + 15);
        for (Circle c : set){
            c.setFill(Color.BLACK);
            c.setRadius(5);
        }
        return set;
    }

    public static ImageView makeFigure(Image img){
        var iw = new ImageView(img);
        iw.setFitWidth(50);
        iw.setFitHeight(100);
        return iw;
    }

    public static Rectangle makeDice(){
        var rect = new Rectangle(120,500,50,50);
        var stops = new Stop[] {new Stop(0,Color.BLUE), new Stop(1, Color.AQUAMARINE)};
        var linear = new LinearGradient(0,0,1,0,true, CycleMethod.NO_CYCLE, stops);
        rect.setFill(linear);
        rect.setStroke(Color.WHITE);
        rect.setArcWidth(15);
        rect.setArcHeight(15);
        return rect;
    }

    public static StackPane noJailPane(){
        var noJail = new StackPane();
        var noPrison = new ImageView(new Image("jaildoor.png"));
        var remove = new ImageView(new Image("remove.png"));
        noPrison.setFitWidth(25); noPrison.setFitHeight(25);
        remove.setFitWidth(25); remove.setFitHeight(25);
        noJail.getChildren().addAll(noPrison, remove);
        return noJail;
    }

    public static StackPane noTaxPane(){
        var stack = new StackPane();
        var noTax = new ImageView(new Image("villager.png"));
        noTax.setFitWidth(25); noTax.setFitHeight(25);
        var tax = new Label("TAX");
        tax.setFont(Font.font(10));
        tax.setTranslateY(-5);
        var line = new Line(-10,10,10,-10);
        line.setStroke(Color.RED);
        line.setStrokeWidth(2);
        stack.getChildren().addAll(noTax, tax, line);
        return stack;
    }

    public static void fillDice(BorderPane root, Circle[] set2) {
        for (Circle setC : set2)
            root.getChildren().addAll(setC);
    }

    public static Rectangle chanceCard(){
        var rectangle = new Rectangle(50, 100);
        rectangle.setVisible(false);
        rectangle.setFill(new ImagePattern(new Image("paper.jpg")));
        return rectangle;
    }
}
