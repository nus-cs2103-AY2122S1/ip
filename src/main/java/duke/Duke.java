package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Duke is a educational software project designed to take you through
 * the steps of building a small software incrementally, while applying
 * as many Java and SE techniques as possible along the way.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
    }

    /**
     * Constructs a Duke with the specified filePath.
     *
     * @param filePath Path of the file to store the task list.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(false));
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }


    /**
     * Runs the Duke instance.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage, true);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage, false);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
