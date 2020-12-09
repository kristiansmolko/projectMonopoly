public class Chance {
    public String text;
    public static String action;
    public int value;

    Chance(String text, String action, int value) {
        this.text = text;
        this.action = action;
        this.value = value;
    }

    Chance getloseTax = new Chance("Your town's raising taxes, for your every house pay $250", "money, move", -250);
    Chance getMoveToExtra = new Chance("Move to the nearest EXTRA square. When you pass through START pick up $2000","move", 2000);
    Chance getloseMoney150 = new Chance("You lost $150","money",-150);
    Chance getMove3Squares = new Chance("Move 3 squares back!","move", 3);
    Chance getwinMoney150 = new Chance("You won $150","money",150);
    Chance loseMoney50 = new Chance("You lost $50","money",-50);
    Chance getwinMoney350 = new Chance("You won $350","money", 350);
    Chance start = new Chance("Move to start and pick up $2000", "money, move", 2000);
    Chance getMoveToPortal = new Chance("Move to the nearest EXTRA square. When no one owns this square u can buy it. When someone owns it u pay double of claim. When you pass through START pick up $2000", "move", 2000);
    Chance getMoveToJail =  new Chance("You got yourself to jail. Move to jail, you won't get through start and you won't get $2000", "move", 0);
    Chance getMovetonearestSquare = new Chance("Move to the nearest square, when someone someone owns it roll the dice and pay 2 times more of the roll. When you pass through START pick up $2000","move", 2000);
    Chance goToThePigFarm = new Chance("Move to pig farm. When you pass through START pick up $2000","move, money", 2000);
    
}

