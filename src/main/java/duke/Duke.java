package duke;

import duke.command.Command;

import duke.control.DialogBox;

import duke.exception.DukeException;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/** A chatbot that handles todo-list operations. */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * An empty constructor only for initializing Duke GUI.
     *
     */
    public Duke() {
    }

    /**
     * A constructor for the class Duke.
     *
     * @param filePath The relative path of the file that stores the user's task list.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Execute the chatbot functions based on user's command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Initiate the Duke chatbot.
     *
     * @param args The argument attached to this method.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}