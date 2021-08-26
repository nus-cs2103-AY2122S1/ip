package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;

import java.io.IOException;

/**
 * The {@code Duke} class is the entry point of duke.
 * To begin the program, it calls run() on an instance of this class.
 */
public class Duke {
    private final TaskList tasks;
    private final String pathName = "./data/duke.txt";
    private final Storage storage;
    private final Ui ui;

    /**
     * A private constructor to initialize variables.
     *
     * @throws IOException Invalid file.
     * @throws DukeException Throw respective exceptions.
     */
    private Duke() throws IOException, DukeException {
        storage = new Storage(pathName);
        ui = new Ui();

        tasks = new TaskList(storage.loadData());
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

    public static void main(String[] args) throws IOException, DukeException {
        Duke duke = new Duke();
        duke.run();
    }
}