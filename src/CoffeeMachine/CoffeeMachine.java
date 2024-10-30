package CoffeeMachine;

public class CoffeeMachine {
    private int water = 400;
    private int milk = 540;
    private int beans = 120;
    private int cups = 9;
    private int money = 550;
    public boolean exitStatus = true;
    private String currentStatus = "Awake";
    private int fillStage = 0;

    private void coffeeCook(int w, int m, int b, int c) {
        if (water >= w && milk >= m && beans >= b && cups > 0) {
            water -= w;
            milk -= m;
            beans -= b;
            cups--;
            money += c;
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            if (water < w) {
                System.out.println("Sorry, not enough water!");
                return;
            }
            if (milk < m) {
                System.out.println("Sorry, not enough milk!");
                return;
            }
            if (beans < b) {
                System.out.println("Sorry, not enough coffee beans!");
                return;
            }
            System.out.println("Sorry, not enough cups!");
        }
    }

    private void buyMenu(String coffeeType) {
        if (coffeeType.equals("back")) {
            currentStatus = "Awake";
            showActionPrompt();
            return;
        }

        switch (coffeeType) {
            case "1" -> coffeeCook(250, 0, 16, 4);
            case "2" -> coffeeCook(350, 75, 20, 7);
            case "3" -> coffeeCook(200, 100, 12, 6);
            default -> {System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            return;}
        }
        currentStatus = "Awake";
        showActionPrompt();
    }

    private void fillAction(String outsideInput) {
        switch (fillStage) {
            case 1 -> {
                water += Integer.parseInt(outsideInput);
                System.out.println("Write how many ml of milk you want to add:");
                fillStage = 2;
            }
            case 2 -> {
                milk += Integer.parseInt(outsideInput);
                System.out.println("Write how many grams of coffee beans you want to add:");
                fillStage = 3;
            }
            case 3 -> {
                beans += Integer.parseInt(outsideInput);
                System.out.println("Write how many disposable cups you want to add:");
                fillStage = 4;
            }
            case 4 -> {
                cups += Integer.parseInt(outsideInput);
                fillStage = 0;
                currentStatus = "Awake";
                showActionPrompt();
            }
        }
    }

    private void takeAction() {
        System.out.println("I gave you " + money);
        money = 0;
        showActionPrompt();
    }

    private void remainingResources() {
        System.out.println("\nThe coffee machine has:\n" +
                water + " of water\n" +
                milk + " of milk\n" +
                beans + " of coffee beans\n" +
                cups + " of disposable cups\n" +
                money + " of money");
        showActionPrompt();
    }

    public void updatedMachineAction(String outsideInput) {
        if (currentStatus.equals("buy")) {
            buyMenu(outsideInput);
        } else if (currentStatus.equals("fill")) {
            fillAction(outsideInput);
        } else {
            switch (outsideInput) {
                case "buy" -> {
                    currentStatus = "buy";
                    System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                }
                case "fill" -> {
                    currentStatus = "fill";
                    System.out.println("\nWrite how many ml of water you want to add:");
                    fillStage = 1;
                }
                case "take" -> takeAction();
                case "remaining" -> remainingResources();
                case "exit" -> exitStatus = false;
                default -> showActionPrompt();
            }
        }
    }

    public void showActionPrompt() {
        if (exitStatus) {
            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
        }
    }
}
