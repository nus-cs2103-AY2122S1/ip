package duke;

import duke.exceptions.DukeException;
import duke.utils.Parser;
import duke.utils.TaskList;

import java.io.IOException;

public class Duke {
    private static TaskList taskList = new TaskList();
    private static Parser parser = new Parser();
    private static final String GREETING = "Hi, I'm Duke, your personal assistant!\n";
    private static final String FAREWELL = "Bye from Duke!";

    public static String initialize() {
        try {
            taskList.loadFromDisk();
            return GREETING + taskList.toString();
        } catch(IOException e) {
            return GREETING + "Your schedule is empty. What should I add to your schedule?";
        }
    }

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