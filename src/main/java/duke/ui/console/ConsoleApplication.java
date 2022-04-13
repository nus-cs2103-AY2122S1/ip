package duke.ui.console;

import java.util.Scanner;

import duke.Duke;
import duke.exception.DukeException;

public class ConsoleApplication {
    /**
     * Initializes a instance of {@link Duke} in the console.
     */
    public static void start() {
        String filePath = "duke.txt";
        Duke duke;

        try {
            duke = new Duke(filePath);
        } catch (DukeException e) {
            System.out.println("Unable to initialize storage file");
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
