package extra;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Player {
    private int account;
    private int pos, tile = 0;
    private ArrayList<String> owned;
    private Image figure;
    private boolean inPrison;
    private int prisonCount = 1;

    public Player(int num){
        account = 500;
        owned = new ArrayList<>();
        pos = num;
        inPrison = false;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public void addToAccount(int value){
        account -= -value;
    }

    public void takeFromAccount(int value){
        account -= value;
    }

    public ArrayList<String> getOwned() {
        return owned;
    }

    public void setOwned(ArrayList<String> owned) {
        this.owned = owned;
    }

    public void addOwned(String name){
        owned.add(name);
    }

    public void setFigure(String img){
        figure = new Image(img);
    }

    public Image getFigure(){
        return figure;
    }

    public boolean isPlaying(){
        if (figure != null)
            return true;
        return false;
    }

    public int getPos() {
        return pos;
    }

    public int getTile(){
        return tile;
    }

    public void addTile(int num){
        tile -= -num;
    }

    public boolean isInPrison(){
        return inPrison;
    }

    public void toPrison(){
        inPrison = true;
    }

    public void addPrison(){
        prisonCount++;
        if (prisonCount > 2){
            inPrison = false;
            prisonCount = 1;
        }
    }

    public int getPrisonCount(){
        return prisonCount;
    }
}
