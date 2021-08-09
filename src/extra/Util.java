package extra;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Util {
    private static final Random rnd = new Random();

    public static Chance[] createChances(){
        var moveTo = "moveTo";
        var money = "money";
        var chances = new Chance[12];
        var getMoveToExtra = new Chance("Move to the FREE square.", moveTo, 16);
        var getloseMoney150 = new Chance("You lost 150.", money, -150);
        var getMove3Squares = new Chance("Move 3 squares back!", "move", -3);
        var getwinMoney150 = new Chance("You won 150.", money, 150);
        var loseMoney50 = new Chance("You lost 50.", money, -50);
        var getwinMoney350 = new Chance("You won 350.", money, 350);
        var start = new Chance("Move to start and pick up 200.", moveTo, 0);
        var getMoveToPortal = new Chance("Move to the portal.", moveTo, 25);
        var getMoveToJail = new Chance("You got yourself to jail.", moveTo, 9);
        var getMovetonearestSquare = new Chance("Move to the nearest square.", "move", 1);
        var taxFree = new Chance("Next time you come to tax \n you don't have to pay it!", "tax", 0);
        var getOutofJail = new Chance("Get out of jail card.","prison",0);
        ArrayList<Chance> allChance = new ArrayList<>();
        allChance.add(getloseMoney150); allChance.add(getOutofJail);
        allChance.add(loseMoney50); allChance.add(getMovetonearestSquare);
        allChance.add(start); allChance.add(getMoveToJail);
        allChance.add(getMoveToExtra); allChance.add(taxFree);
        allChance.add(getwinMoney150); allChance.add(getwinMoney350);
        allChance.add(getMoveToPortal); allChance.add(getMove3Squares);
        for (var i = 0; i < chances.length; i++){
            var number = rnd.nextInt(allChance.size());
            chances[i] = allChance.get(number);
            allChance.remove(number);
        }
        return chances;
    }

    public static String getPlayerName(Map<String, String> map, int position){
        var pos = 1;
        for(Map.Entry<String, String> entry : map.entrySet()){
            if (pos == position)
                return entry.getKey();
            else
                pos -= -1;
        }
        return "";
    }

    public static String getPlayerFigure(Map<String, String> map, int position){
        var pos = 1;
        for(Map.Entry<String, String> entry : map.entrySet()){
            if (pos == position)
                return entry.getValue();
            else
                pos -= -1;
        }
        return "";
    }

    public static void setPlayer(Player player, Map<String, String> map, int position){
        player.setFigure(getPlayerFigure(map, position));
        player.setName(getPlayerName(map, position));
    }
}
