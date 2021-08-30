package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * Program to keep track of a list of tasks.
 *
 * @author Cheong Yee Ming
 * @version Duke A-JavaDoc
 */
public class Duke {

    // List to store all user inputs
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        taskList = new TaskList();
        storage = new Storage();
        ui = new Ui();
        parser = new Parser(taskList, storage, ui);
    }

    /**
     * Runs when bot is first initialised.
     * Greets the user and loads data from local directory if present
     * If not, local directory and local data file are created.
     * Takes in user input and responds respectively.
     */
    private void initiateSystem() {
        taskList.loadFromStorage(storage.load());
        ui.greet();
        Scanner sc = new Scanner(System.in);
        boolean isActive = true;

        while (isActive) {
            String userInput = sc.nextLine();
            try {
                Command command = parser.parseUserInput(userInput);
                command.runCommand();
                isActive = !(command instanceof ExitCommand);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Main function for Duke program.
     * @param args Null.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initiateSystem();
    }
}
