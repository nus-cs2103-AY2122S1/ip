package duke;

import duke.command.CommandExecutor;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The Duke program implements a chat bot called Duke that supports queries
 * such as creating, marking and deleting tasks.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private CommandExecutor commandExecutor;

    /**
     * A constructor for Duke.
     *
     * @param filePath Input path for the data file.
     */
    public Duke(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadTask());
            commandExecutor = new CommandExecutor(tasks, storage, ui);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * Runs the Duke chat bot program until user input the exit command.
     */
    public void run(String input) {
        Ui.welcome();
        commandExecutor.execute(input);
        Storage.saveList();
    }

    /**
     * Starts the Duke chat bot program.
     *
     * @param args Input path for the data file.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run("todo read me");
    }

    /**
     * Returns response from Duke.
     *
     * @param input User input command.
     * @return String representation of Duke's response.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            response = commandExecutor.execute(input);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }
}

