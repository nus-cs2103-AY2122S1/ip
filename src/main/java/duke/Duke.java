package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * A class that encapsulates Duke, a task management bot.
 */
public class Duke {
    /**
     * Where the task in stored.
     */
    private final Storage storage;

    /**
     * A task list that store the tasks.
     */
    private TaskList tasks;

    /**
     * Constructs a Duke bot that save its tasks in the {@code tasks.txt} file.
     */
    public Duke() {
        this.storage = new Storage("tasks.txt");
        try {
            this.tasks = this.storage.parseToTaskList();
        } catch (DukeException e) {
            Ui.reportError(e);
        }
    }

    /**
     * Runs the Duke bot. Users may deal with tasks by entering command.
     */
    private void run() {
        boolean isExit = false;
        Ui.greet();

        while (!isExit) {
            try {
                String input = Ui.readCommand();
                Command command = Parser.parseCommand(input);
                command.execute(this.tasks, this.storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                Ui.reportError(e);
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return Ui.greet();
    }
}
