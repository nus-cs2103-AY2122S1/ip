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
    private TaskList tasks;

    /**
     * Constructor for Duke class. Initializes the ui and storage.
     *
     * @param filePath the path to store the tasks.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }

        parser = new Parser(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Runs the duke app.
     */
    public void run() {
        initialize();
        startParser();
        exit();
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
     * Starts the parsing of input
     */
    public void startParser() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (!(input = scanner.nextLine().trim()).equals("bye")) {
            try {
                parser.parse(input);
            } catch (DukeException e) {
                Ui.print(e.getMessage());
            }
        }
        scanner.close();
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
