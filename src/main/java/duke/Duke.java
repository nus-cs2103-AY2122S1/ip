package duke;

import duke.exception.DukeException;

import java.util.Scanner;

public class Duke {
    private final Storage storage;

    private TaskList tasks;

    public Duke(String fileName) {
        this.storage = new Storage(fileName);
        try {
            this.tasks = this.storage.parseToTaskList();
        } catch (DukeException e) {
            Ui.reportError(e);
        }
    }

    private void run() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        Ui.greet();

        String input = scanner.next();

        while (!input.equals("bye")) {
            try {
                Parser.parseCommand(input, tasks, storage);
            } catch (DukeException e) {
                Ui.reportError(e);
            } finally {
                input = scanner.next();
            }
        }

        Ui.farewell();
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
