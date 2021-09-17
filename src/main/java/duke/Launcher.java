package duke;

import java.util.Scanner;

import duke.ui.ConsoleUi;
import javafx.application.Application;

/**
 * A launcher class to launch Duke.
 */
public class Launcher {
    /**
     * Launches Duke either in GUI mode or console mode.
     *
     * @param args Arguments to the launcher.
     */
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("--console")) {
            runConsole();
        } else {
            Application.launch(Main.class, args);
        }
    }

    /**
     * Initializes a instance of {@link Duke} in the console.
     */
    public static void runConsole() {
        String filePath = "duke.txt";
        Duke duke;

        try {
            duke = new Duke(filePath);
        } catch (Exception e) {
            // TODO: figure out static/non-static Ui class
            System.out.println("Unable to initialize data file");
            return;
        }

        Scanner inputScanner = new Scanner(System.in);
        ConsoleUi ui = new ConsoleUi(inputScanner);
        ui.printGreeting();
        duke.setUi(ui);

        boolean shouldExit = false;
        while (!shouldExit) {
            String fullCommand = ui.readCommand();
            shouldExit = duke.executeCommand(fullCommand);
        }

        ui.printGoodbye();
        inputScanner.close();
    }
}
