import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        levelFour();
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

    public static void levelThree() {
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
                int taskToComplete = meow.checkCompleteTask(input);
               if (taskToComplete == 0)  {
                   meow.addTask(input);
               } else if (taskToComplete != Integer.MAX_VALUE){
                   meow.completeTask(taskToComplete);
                }
            }
            input = scanner.nextLine();
        }

        // Goodbye message from the chat bot.
        meow.exit();

        // Clean up the scanner.
        scanner.close();
    }

    public static void levelFour() {
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
                int taskToComplete = meow.checkCompleteTask(input);
                if (taskToComplete == 0)  {
                    meow.addDifferentTask(input);
                } else if (taskToComplete != Integer.MAX_VALUE){
                    meow.completeTask(taskToComplete);
                }
            }
            input = scanner.nextLine();
        }

        // Goodbye message from the chat bot.
        meow.exit();

        // Clean up the scanner.
        scanner.close();
    }
}
