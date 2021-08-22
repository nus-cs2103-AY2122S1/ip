package duke;

import duke.command.Command;

import java.time.DateTimeException;

public class Duke {
    private final Ui ui;
    private TaskManager taskManager;

    public Duke() {
        this.ui = new Ui();
        this.taskManager = new TaskManager();
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while(!isExit) {
            String userInput = ui.getUserInput();
            try {
                Command c = Parser.parse(userInput);
                c.execute(taskManager, ui);
                isExit = c.isExit();
            } catch (DukeException | IllegalArgumentException | DateTimeException e) {
                ui.handleError(e);
            }
        }
    }

}
