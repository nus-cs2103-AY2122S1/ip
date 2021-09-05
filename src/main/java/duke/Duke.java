package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents Duke, a program that helps people to keep track of tasks.
 *
 * @author ruiquan
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Duke instance.
     *
     * @param filePath The path to a text file for storage.
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
     * Run Duke.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                ui.showLine();
                Command c = Parser.parse(input);
                String message = c.execute(tasks, ui, storage);
                ui.reply(message);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Returns the appropriate response given an input by the user.
     *
     * @param input The input by the user.
     * @return The response by Duke.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    /**
     * This is the entry point for the Duke program in CLI.
     *
     * @param args An array of String arguments to follow convention.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
