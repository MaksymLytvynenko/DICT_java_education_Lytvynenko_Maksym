package CoffeeMachine;

import java.util.Scanner;

public class CoffeeMachineInput {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);
        coffeeMachine.showActionPrompt();

        while (coffeeMachine.exitStatus) {
            coffeeMachine.updatedMachineAction(scanner.next());
        }
    }
}
