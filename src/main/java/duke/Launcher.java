package duke;

import duke.command.Command;
import duke.ui.ConsoleUi;
import javafx.application.Application;

import java.util.Scanner;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
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
            try {
                String fullCommand = ui.readCommand();
                shouldExit = duke.executeCommand(fullCommand);
            } catch (Exception e) {
                // TODO: custom Duke exceptions?
                ui.printMessage("Error: " + e.getMessage());
            }
        }

        ui.printGoodbye();
        inputScanner.close();
    }
}
