package duke;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.io.Parser;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.TaskList;

/**
 * Main class for Duke app.
 */
public class Duke {

    private final Storage storage;
    private final Parser parser;
    private final TaskList tasks;

    /**
     * Constructor for Duke class. Initializes the ui and storage.
     *
     * @param filePath the path to store the tasks.
     */
    public Duke(String filePath) {
        TaskList tasks1;
        storage = new Storage(filePath);

        try {
            tasks1 = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showLoadingError();
            tasks1 = new TaskList();
        }

        tasks = tasks1;
        parser = new Parser(tasks);
    }

    /**
     * Driver code for Duke
     *
     * @param args arguments for main method.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Runs the duke app.
     */
    public void run() {
        // initialize
        initialize();

        // parse
        Scanner scanner = new Scanner(System.in);
        String input;

        while (!(input = scanner.nextLine().trim()).equals("bye")) {
            try {
                parser.parse(input);
            } catch (DukeException e) {
                Ui.print(e.getMessage());
            }
        }

        // end
        exit();
        scanner.close();
    }

    /**
     * Initializes Duke.
     *
     * @return hi message.
     */
    public String initialize() {
        return Ui.sayHi();
    }

    /**
     * Exits Duke.
     */
    public void exit() {
        try {
            storage.save(tasks);
        } catch (DukeException e) {
            Ui.print(e.getMessage());
        }
    }

    /**
     * Returns the response given user input.
     *
     * @param input user input.
     * @return response from duke.
     */
    public String getResponse(String input) {
        try {
            return parser.parse(input);
        } catch (DukeException e) {
            return Ui.print(e.getMessage());
        }
    }
}
