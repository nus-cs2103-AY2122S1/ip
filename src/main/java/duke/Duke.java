package duke;

import duke.command.Command;
import duke.dukeexception.DukeException;


/**
 * Represents the Entry point for Chat bot Duke as a whole.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs the DUke from a given file path.
     *
     * @param filePath The given file pth.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    /**
     * Initialises the Duke bot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                System.out.println(c.exec(this.tasks, this.ui, this.storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Returns an appropriate response based on the given input.
     *
     * @param input The given input string from user.
     * @return The appropriate response as a String.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.exec(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
    public static void main(String[] args) {
        new Duke("./data/file.txt").run();
    }
}

