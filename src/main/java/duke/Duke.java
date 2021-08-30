package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Represents the chatbot that users can save tasks with
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke constructor using file path to load/save tasks.
     *
     * @param filePath Path of file to save tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = TaskList.emptyTaskList();
        }
    }

    /**
     * Runs Duke chatbot until termination.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showLoadingError(e.getMessage());
            }
        }
    }

    /**
     * Launches Duke chatbot with specified file path.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
