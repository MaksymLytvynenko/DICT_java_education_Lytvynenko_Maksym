package ChatBot;

import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! My name is ChatBot.");
        System.out.println("I was created in 2024.");
        System.out.println("Please remind me your name.");

        String name = scanner.nextLine();

        System.out.println("What a great name you have " + name + "!");
        System.out.println("Let me guess your age.");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");

        int remainder3 = scanner.nextInt();
        int remainder5 = scanner.nextInt();
        int remainder7 = scanner.nextInt();
        int age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;

        System.out.println("Your age is " + age + "; that's a good time to start programming!");
        System.out.println("Now I will prove to you that I can count to any number you want!");

        int number = scanner.nextInt();
        for (int i = 0; i <= number; i++) {
            System.out.println(i + "!");
        }

        System.out.println("Now let's do a test.");
        System.out.println("What is a correct syntax to output \"Hello World\" in Java?");
        System.out.println("1.  print (\"Hello World\")");
        System.out.println("2.  System.out.println(\"Hello World\")");
        System.out.println("3.  Console.WriteLine(\"Hello World\");");
        System.out.println("4.  echo(\"Hello World\");");

        while (true) {
            int answer = scanner.nextInt();
            if (answer == 2) {
                System.out.println("Correct");
                break;
            } else {
                System.out.println("Wrong");
            }
        }

        System.out.println("Goodbye, have a nice day!");
    }
}
