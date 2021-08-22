package duke;

import duke.exception.DukeException;
import duke.io.Parser;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.TaskList;

import java.util.Scanner;

public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private final TaskList tasks;

    public Duke(String filePath) {
        TaskList tasks1;
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks1 = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks1 = new TaskList();
        }

        tasks = tasks1;
        parser = new Parser(tasks, ui);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Runs the duke app.
     */
    public void run() {
        // start
        start();

        // parse
        Scanner scanner = new Scanner(System.in);
        String input;

        while (!(input = scanner.nextLine().trim()).equals("bye")) {
            try {
                parser.parse(input);
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }

        // end
        exit();
        scanner.close();
    }

    /**
     * Starts up Duke.
     */
    private void start() {
        ui.sayHi();
    }

    /**
     * Prints exit message upon exit.
     */
    private void exit() {
        try {
            storage.save(tasks);
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }
        ui.sayBye();
    }
}
