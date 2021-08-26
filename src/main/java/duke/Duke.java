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
     * Constructs a Duke bot that stores the incoming tasks into a txt file with specified name.
     *
     * @param fileName The file name you want to store your tasks in.
     */
    public Duke(String fileName) {
        this.storage = new Storage(fileName);
        try {
            this.tasks = this.storage.parseToTaskList();
        } catch (DukeException e) {
            Ui.reportError(e);
        }
    }

    /**
     * Invokes the Duke bot.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
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
}
