package duke;

import duke.task.Task;
import duke.command.Command;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    protected Storage storage;
    private TaskList tasks;
    protected Ui ui;

    /**
     * Class constructor for Duke Class specifying the filepath
     */
    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> taskList = storage.load();
            if (taskList.isEmpty()) {
                throw new DukeException("task list is empty");
            }
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main method, create an instance of Duke and run it
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Duke victor = new Duke(Paths.get(System.getProperty("user.dir"), "data", "duke.txt"));
        victor.run();
    }

    /**
     * Run the init commands of Duke
     */
    public void run() {
        storage.load();
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
    }
}
