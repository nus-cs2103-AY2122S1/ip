package duke;

import java.util.LinkedList;
import java.util.Queue;

import duke.action.Action;
import duke.action.GoodbyeUser;
import duke.action.WelcomeUser;
import duke.exception.UserException;
import duke.request.Request;
import duke.task.TaskCollection;

/**
 * Duke is the class that represents the entire command line application.
 */
public class Duke {
    private static final String TASK_COLLECTION_STORAGE_PATH = "./data/duke.txt";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final TaskCollection tasks = new TaskCollection(TASK_COLLECTION_STORAGE_PATH);

    /**
     * Returns the string response.
     * @return
     */
    public static String greetUser() {
        Action welcomeUser = new WelcomeUser();
        Response response = welcomeUser.execute();
        return response.toString();
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    public Response handleUserInput(String userInput) {
        assert userInput != null : "User input should be a valid string";
        try {
            Request request = Request.create(tasks, userInput);
            Action action = request.action();
            Response response = action.execute();
            tasks.saveTasks();
            return response;
        } catch (UserException exception) {
            return exception.toResponse();
        }
    }
}
