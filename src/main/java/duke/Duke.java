package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.initialise());
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readInput();
                Command c = Parser.parse(userInput, ui, tasks);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
