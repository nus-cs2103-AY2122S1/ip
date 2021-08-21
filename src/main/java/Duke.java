import java.util.Scanner;

/**
 *  This class is the entry point into the program, Duke.
 *  Duke is a chat bot that helps manage tasks that you have.
 *
 * @author Ryan Tian Jun.
 */
public class Duke {
    public static void main(String[] args) {
        Intro printIntro = new Intro();
        Farewell printFarewell = new Farewell();

        printIntro.printIntro();

        // Starts reading user input
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!command.toLowerCase().equals("bye")) {
            command = scanner.nextLine();
            // Processes user input
            if (!command.toLowerCase().equals("bye")) {
                FeatureMain feature = new Feature(command);
            }
        }

        printFarewell.printFarewell();

    }
}
