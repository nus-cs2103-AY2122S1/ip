package mango;

import java.io.IOException;

/**
 * Represents a chat-bot named Mango that keeps track of a list of tasks that the user
 * can manipulate by adding, deleting, or completing.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a Duke chat-bot.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/mango.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError(new DukeException(e.getMessage()));
            tasks = new TaskList();
        }
    }
    /**
     * Constructor for a Duke chat-bot.
     *
     * @param filePath The path for the file that will contain the list of tasks tracked by the chat-bot.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError(new DukeException(e.getMessage()));
            tasks = new TaskList();
        }
    }

    /**
     * Returns a greeting from the ui.
     *
     * @return A greeting message to the user.
     */
    public String greet() {
        return this.ui.greet();
    }

    /**
     * Returns a farewell message from the ui.
     *
     * @return A farewell message to the user.
     */
    public String exit() {
        try {
            this.storage.save(this.tasks);
        } catch (IOException e) {
            return "Error encountered when saving: " + e.getMessage();
        }
        return this.ui.exit();
    }

    /**
     * Retrieves Mango's response to the user input.
     *
     * @param input The user input string.
     * @return Mango's response to the user input.
     */
    public String getResponse(String input) {
        return Parser.parse(this.tasks, input, this);
    }
}
