package duke;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;


/**
 * Encapsulates the Duke bot that has the ability to create, read, update and delete tasks
 * such as todo, deadline and event tasks based on user input.
 *
 * @author Dickson
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath user filepath to store text file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException | IOException e) {
            ui.printMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the duke bot.
     */
    public void run() {
        String command;

        while (!((command = ui.readCommand()).equals("bye"))) {
            try {
                String message = Parser.parse(command).execute(tasks);
                ui.printMessage(message);
                storage.saveFile(tasks);
            } catch (DukeException | IOException e) {
                ui.printMessage(e.getMessage());
            }
        }
        ui.end();
    }

    public static void main(String[] args) {
        new Duke("\\duke.txt").run();
    }
}
