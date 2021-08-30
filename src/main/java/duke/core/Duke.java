package duke.core;

/**
 * Encapsulates the Main Program where Duke is run.
 *
 * @author Clifford
 */

import duke.command.Commandable;
import duke.exception.DukeException;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static final String TASKS_FILE_PATH = "bin/data/tasks.txt";

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
