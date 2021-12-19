package abyss;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import abyss.command.Command;
import abyss.command.Parser;
import abyss.exception.AbyssException;
import abyss.exception.LoadTaskException;
import abyss.task.TaskManager;

/**
 * The Abyss is an application which keeps track of tasks, allowing users to
 * add and delete tasks of multiple types, including deadlines, events and to-dos.
 * The Abyss can also list existing tasks and mark tasks as done.
 */
public class Abyss {
    private static TaskManager taskManager = new TaskManager();
    private static Storage storage;
    private static final String FILE_PATH = "./data/abyss.txt";

    /**
     * Returns a response given a user input.
     *
     * @param input Input arguments.
     */
    public String getResponse(String input) {
        String response;

        try {
            storage = new Storage(FILE_PATH);
            taskManager = storage.loadTasks();
        } catch (IOException | LoadTaskException e) {
            response = Ui.formatReply(e.getMessage());
            return response;
        }

        try {
            Command cmd = Parser.parseCommand(input);
            response = cmd.execute();
        } catch (AbyssException | IOException | DateTimeParseException e) {
            response = Ui.formatReply(e.getMessage());
        }
        return response;
    }

    public static TaskManager getTaskManager() {
        return taskManager;
    }

    public static Storage getStorage() {
        return storage;
    }
}
