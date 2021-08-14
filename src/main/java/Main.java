import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        levelTwo();
    }

    public static void levelOne() {
        // Create a new chat bot object.
        Meow meow = new Meow();

        // Greeting from chat bot.
        meow.greet();

        // Create a scanner to read from standard input.
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        // Check if the chat bot should continue or exit
        while (!meow.checkExit(input)) {
            meow.echo(input);
            input = scanner.next();
        }

        // Goodbye message from the chat bot.
        meow.exit();

        // Clean up the scanner.
        scanner.close();
    }

    public static void levelTwo() {
        // Create a new chat bot object.
        Meow meow = new Meow();

        // Greeting from chat bot.
        meow.greet();

        // Create a scanner to read from standard input.
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Check if the chat bot should continue or exit
        while (!meow.checkExit(input)) {
            if (meow.checkDisplayList(input)) {
                meow.displayList();
            } else {
                meow.addTask(input);
            }
            input = scanner.nextLine();
        }

        // Goodbye message from the chat bot.
        meow.exit();

        // Clean up the scanner.
        scanner.close();
    }
}
