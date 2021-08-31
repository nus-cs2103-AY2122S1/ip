package duke;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;


/**
 * The {@code Duke} class is the entry point of duke.
 * To begin the program, it calls run() on an instance of this class.
 */
public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Initialises a {@code Duke} object that uses the provided {@code pathName}
     * as the location of the save text file.
     * Performs a check for GUI that prints error messages encountered.
     *
     * @param isGui A {@code boolean} to denote if the program is using Gui.
     */
    public Duke(String pathName, boolean isGui) {
        storage = new Storage(pathName);
        ui = new Ui();

        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException | IOException e) {
            tasks = new TaskList();
            if (!isGui) {
                ui.print(e.getMessage());
            }
        }
    }

    /**
     * Returns a response to the given input.
     *
     * @param input The user input.
     * @return A {@code String} response.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return ui.close();
        }

        String response;
        try {
            response = Parser.parse(input).execute(tasks);
            storage.saveData(tasks.getTasks());
        } catch (DukeException | IOException e) {
            response = e.getMessage();
        }

        return response;
    }

    /**
     * Returns the welcome message for GUI mode.
     *
     * @return Welcome message {@code String} for GUI mode.
     */
    public String getWelcomeMessage() {
        return this.ui.getGreeting();
    }

    /**
     * Run main body of a {@code Duke} object.
     */
    public void run() {
        String input;

        while (!(input = ui.getCommand()).equals("bye")) {
            try {
                String output = Parser.parse(input).execute(tasks);
                ui.print(output);
                storage.saveData(tasks.getTasks());
            } catch (DukeException | IOException e) {
                ui.print(e.getMessage());
            }
        }
        ui.close();
    }


    /**
     * Main class that Duke builds from.
     * @param args Unused string array of args.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt", false);
        duke.run();
    }
}

