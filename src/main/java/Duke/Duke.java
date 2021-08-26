package Duke;

import Duke.Commands.Command;
import Duke.Storage.FileFormatException;
import Duke.Storage.Storage;
import Duke.Storage.TaskStorage;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Ui.Parser;
import Duke.Ui.Ui;
import Duke.Ui.UserInput;

import java.io.IOException;

/**
 * The Duke class encapsulates the main logic and data for running the chat-bot.
 *
 * @author cai
 */
public class Duke {
    /** Default path for storing tasks */
    private static final String DEFAULT_TASK_STORAGE_PATH = "data/duke.txt";

    /** Boolean indicating whether the program has been stopped */
    private boolean isStopped = false;

    /** List of tasks as added by the user */
    private final TaskList taskList;

    /**
     * Constructs an instance of the Duke chat-bot with the specified task storage path.
     *
     * @param todoStoragePath Path for storing tasks.
     * @throws IOException If IOException is thrown when creating and/or loading the tasks from the path.
     */
    public Duke(String todoStoragePath) throws IOException {
        Storage<Task> taskStorage = new TaskStorage(todoStoragePath);
        try {
            this.taskList = new TaskList(taskStorage);
        } catch (FileFormatException e) {
            Ui.print(Ui.FILE_FORMAT_ERROR_MESSAGE);
            throw e;
        }
    }

    /**
     * Constructs an instance of the Duke chat-bot with the default task storage path.
     *
     * @throws IOException If IOException is thrown when creating and/or loading the tasks from the path.
     */
    public Duke() throws IOException {
        this(DEFAULT_TASK_STORAGE_PATH);
    }

    /**
     * Runs the Duke chat-bot.
     * The program interacts with the user through a read-eval-print loop. The user is prompted for a command,
     * which is parsed by the bot and executed. The program then loops back and asks for a prompt again.
     */
    public void run() {
        Ui.print(Ui.GREETING_MESSAGE);
        while (!this.isStopped) {
            UserInput input = Parser.parse(Ui.read());
            try {
                Command.matching(input).run(this, input);
            } catch (DukeException e) {
                Ui.print(String.format(Ui.ERROR_MESSAGE, e.getMessage()));
            }
        }
        Ui.print(Ui.EXIT_MESSAGE);
    }

    /**
     * Stops the Duke chat-bot.
     * Exits the program gracefully with an exit message on the next iteration of the read-eval-print loop.
     */
    public void stop() {
        this.isStopped = true;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}
