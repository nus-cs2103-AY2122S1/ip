package duke;

import duke.exceptions.DukeException;
import duke.utils.Parser;
import duke.utils.TaskList;

import java.io.IOException;

/**
 * Duke class is responsible for the overall app functionality. Duke runs
 * by taking in a user input and outputting a response after the user command
 * is executed.
 */
public class Duke {
    private static TaskList taskList = new TaskList();
    private static Parser parser = new Parser();
    private static final String GREETING = "Hi, I'm Duke, your personal assistant!\n";
    private static final String FAREWELL = "Bye from Duke!";

    /**
     * Initializes the app by loading saved tasks (if any) and greets the user.
     *
     * @return String containing the initial greeting to the user.
     */
    public static String initialize() {
        try {
            taskList.loadFromDisk();
            return GREETING + taskList.toString();
        } catch(IOException e) {
            return GREETING + "Your schedule is empty. What should I add to your schedule?";
        }
    }

    /**
     * Runs the app by taking in a user input to return a response based on the user's command.
     *
     * @param input Input provided by the user.
     * @return String response from the app.
     */
    public static String runDuke(String input) {
        if (input.equals("bye")) {
            return FAREWELL;
        } else {
            try {
                TaskList updatedTaskList = parser.parseInput(taskList, input);
                return updatedTaskList.toString();
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    }
}