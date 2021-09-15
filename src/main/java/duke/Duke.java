package duke;

import duke.action.Action;
import duke.action.WelcomeUser;
import duke.exception.UserException;
import duke.request.Request;
import duke.task.TaskCollection;

/**
 * Duke is the class that represents the entire command line application.
 */
public class Duke {
    private static final String TASK_COLLECTION_STORAGE_PATH = "./data/duke.txt";

    private final TaskCollection tasks = new TaskCollection(TASK_COLLECTION_STORAGE_PATH);

    /**
     * Returns the greeting Response of the Duke application.
     * @return The greeting Response of the Duke application.
     */
    public static Response greetUser() {
        Action welcomeUser = new WelcomeUser();
        return welcomeUser.execute();
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    public Response handleUserInput(String userInput) {
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
