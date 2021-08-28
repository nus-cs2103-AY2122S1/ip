package duke;

import duke.command.Command;
import duke.storage.StorageDuke;
import duke.tasklist.TaskListDuke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Duke is a Personal Assistant Chatbot that helps a person
 * keep track of various things.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class Duke {
    private final StorageDuke STORAGE;
    private final Ui UI;
    private TaskListDuke tasks;

    /**
     * Constructor for Duke.
     *
     * @param filePath the filePath for the saved data
     */
    public Duke(String filePath) {
        this.UI = new Ui();
        this.STORAGE = new StorageDuke(filePath);
        try {
            tasks = new TaskListDuke(this.STORAGE.load(), UI);
        } catch (IOException e) {
            UI.reply("☹ OOPS!!! Cannot create file!");
            tasks = new TaskListDuke(new ArrayList<>(), UI);
        }
    }

    /**
     * Executes the Duke application.
     */
    public void run() {
        UI.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = UI.readCommand();
                UI.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, UI, STORAGE);
                isExit = c.isExit();
            } catch (DukeException e) {
                UI.showError(e.getMessage());
            } catch (IOException e) {
                UI.showError(new DukeException("Error reading commands.").getMessage());
            } finally {
                UI.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
