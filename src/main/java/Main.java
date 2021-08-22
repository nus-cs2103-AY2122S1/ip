import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        // Create a new chat bot object.
        Meow meow = new Meow();
        // Read the file and convert the content to the taskList array
        meow.convertFileToArray();
        // Greeting from chat bot.
        meow.greet();
        // Create a scanner to read from standard input.
        Scanner scanner = new Scanner(System.in);
        while (!meow.getIsExit()) {
            try {
                String input = scanner.nextLine();
                meow.checkCommand(input);
            } catch (MeowException exception) {
                System.out.println(exception.toString());
            }
        }
        // Clean up the scanner.
        scanner.close();
    }
}
