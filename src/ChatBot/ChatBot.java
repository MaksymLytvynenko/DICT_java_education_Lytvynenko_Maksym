package ChatBot;

import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        System.out.println("Hello! My name is ChatBot.");
        System.out.println("I was created in 2024.");
        System.out.println("Please remind me your name.");

        Scanner scanner = new Scanner(System.in);
        String Name = scanner.nextLine();

        System.out.println("What a great name you have " + Name + "!");
    }
}
