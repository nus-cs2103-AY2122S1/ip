package duke.core;

import java.util.Scanner;

import duke.command.Commandable;
import duke.exception.DukeException;

/**
 * Encapsulates the Main Program where Duke is run.
 *
 * @author Clifford
 */
public class Duke {
    private static final String TASKS_FILE_PATH = "bin/data/tasks.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises Duke for whole application to run
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage(TASKS_FILE_PATH);
            taskList = new TaskList(storage);
            taskList.retrieveTasks();
        } catch (DukeException e) {
            ui.formatDisplay(e.getMessage());
        }
    }

    /**
     * Runs the whole application
     */
    public void run() {
        ui.greetUser();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            try {
                String userInput = sc.nextLine();
                Commandable c = Parser.identifyCommand(userInput);
                ui.formatDisplay(c.execute(taskList, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.formatDisplay(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
