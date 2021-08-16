import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MeowException {
        run();
    }

    public static void run() throws MeowException {
        // Create a new chat bot object.
        Meow meow = new Meow();
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
