package duke;

import duke.command.Command;
import duke.exception.DukeException;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.initialise());
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readInput();
                Command c = Parser.parse(userInput, ui, taskList);
                c.execute(taskList, ui, storage);
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
