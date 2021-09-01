package duke;

import duke.command.Command;
import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of tasks to do.
 */
public class Duke {

    private final Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Duke Chatbot with a data storage file.
     *
     * @param filePath The path of the data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showStartUpError(e);
            tasks = new TaskList();
        }
    }

    /**
     * The main program.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Runs the Chatbot program.
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand, tasks);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showMessage(e.toString());
            } finally {
                ui.showLine();
            }
        }
    }
}
