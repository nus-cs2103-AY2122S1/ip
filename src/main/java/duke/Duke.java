package duke;

import duke.command.CommandExecutor;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The duke.Duke program implements a chatbot called duke.Duke that
 * supports queries such as creating, marking and deleting tasks.
 *
 * @author Chen Hsiao Ting
 * @version 1.0
 * @since 2021-08-13
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private CommandExecutor commandExecutor;

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
     * Runs the duke.Duke chatbot program until user input the bye command.
     */
    public void run(String input) {
        Ui.welcome();
        commandExecutor.execute(input);
        Storage.saveList();
    }

    /**
     * Starts the duke.Duke chatbot program
     *
     * @param args input path for the data file
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run("todo read me");
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        new Duke("data/duke.txt").run(input);

        return "duke: ";
    }

}

