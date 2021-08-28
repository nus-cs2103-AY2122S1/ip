package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.command.Command;
import duke.storage.StorageDuke;
import duke.tasklist.TaskListDuke;

/**
 * Duke is a Personal Assistant Chatbot that helps a person
 * keep track of various things.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class Duke {
    private StorageDuke storage;
    private Ui ui;
    private TaskListDuke tasks;

    /**
     * Constructor for Duke.
     *
     * @param filePath the filePath for the saved data
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new StorageDuke(filePath);
        try {
            tasks = new TaskListDuke(this.storage.load(), ui);
        } catch (IOException e) {
            ui.reply("☹ OOPS!!! Cannot create file!");
            tasks = new TaskListDuke(new ArrayList<>(), ui);
        }
    }

    /**
     * Executes the Duke application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(new DukeException("Error reading commands.").getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
