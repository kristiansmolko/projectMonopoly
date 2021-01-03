import sun.security.provider.PolicyParser;

public class Chance {
    public String text;
    public static String action;
    public int value;

    Chance(String text, String action, int value, PolicyParser.PermissionEntry card) {
        this.text = text;
        this.action = action;
        this.value = value;

        if (card.action.equals("money")) {

        } else if (card.action.equals("move")) {
        } else if (card.action.equals("prison")){
        }
    }

    private PolicyParser.PermissionEntry card;
    Chance getloseTax = new Chance("Your town's raising taxes, for your every house pay $250", "money, move", -250, card);
    Chance getMoveToExtra = new Chance("Move to the nearest EXTRA square. When you pass through START pick up $2000", "move", 2000, card);
    Chance getloseMoney150 = new Chance("You lost $150", "money", -150, card);
    Chance getMove3Squares = new Chance("Move 3 squares back!", "move", 3, card);
    Chance getwinMoney150 = new Chance("You won $150", "money", 150, card);
    Chance loseMoney50 = new Chance("You lost $50", "money", -50, card);
    Chance getwinMoney350 = new Chance("You won $350", "money", 350, card);
    Chance start = new Chance("Move to start and pick up $2000", "money, move", 2000, card);
    Chance getMoveToPortal = new Chance("Move to the nearest EXTRA square. When no one owns this square u can buy it. When someone owns it u pay double of claim. When you pass through START pick up $2000", "move", 2000, card);
    Chance getMoveToJail = new Chance("You got yourself to jail. Move to jail, you won't get through start and you won't get $2000", "move", 0, card);
    Chance getMovetonearestSquare = new Chance("Move to the nearest square, when someone someone owns it roll the dice and pay 2 times more of the roll. When you pass through START pick up $2000", "move", 2000, card);
    Chance goToThePigFarm = new Chance("Move to pig farm. When you pass through START pick up $2000", "move, money", 2000, card);
    Chance getOutofJail = new Chance("Get out of jail free","prison",0, card);
}
