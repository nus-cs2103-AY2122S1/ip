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
     *
     * @param filePath The path for the file that will contain the list of tasks tracked by the chatbot.
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
     * Executes the functionality of the chat-bot.
     */
    public void run() {
        ui.greet();

        Parser.parse(this.tasks);

        try {
            storage.save(this.tasks);
        } catch (IOException e) {
            System.out.println("Error encountered when saving data: " + e.getMessage());
        }

        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("data/mango.txt").run();
    }
}
