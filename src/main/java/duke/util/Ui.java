package duke.util;

import duke.task.*;
import java.util.ArrayList;

public class Ui {
    private static final String EXIT_MESSAGE = "Goodbyeeee! Hope to see you again soon! :>";
    private static final String SEPARATOR = "\t-------------------------------------------------------";
    private static final String INPUT_PROMPT = "Enter a command ^_^";

    /**
     * Formats the input and prints it in a formatted version.
     *
     * @param message message to be printed.
     */
    public static void prettify(String message) {
        System.out.printf("%s\n\t%s\n%s\n", SEPARATOR, message, SEPARATOR);
    }

    /** Prints introduction message when bot is first launched. **/
    public void printIntroMessage() {
        prettify(
                "Hello! I'm Duke, your personal CLI bot! :D\n\t"
                        + "I function as a simple TodoList.\n\t"
                        + "I can keep track of 3 types of tasks:\n\t"
                        + "\t> Todo: To add a new todo task, enter 'todo <task>' e.g. 'todo quiz'.\n\t"
                        + "\t> Deadline: To add a new deadline, enter 'deadline <task> /by <date-time>'.\n\t"
                        + "\t> Event: To add a new event, enter 'event <event-name> /at <date-time>'.\n\t"
                        + "\t  * For deadline and event tasks, you can specify the time "
                        + "by using the format 'YYYY-MM-DD' or 'YYYY-MM-DD hhmm'\n\t\t"
                        + "    where 'hhmm' represents the 24 hour clock.\n\t\t"
                        + "    e.g. event dinner /at 2021-12-26 1830\n\t"
                        + "- To see all your tasks, enter 'list'.\n\t"
                        + "- To delete a task, enter 'delete <task-id>' e.g. 'delete 2'.\n\t"
                        + "- You can also mark tasks as done by typing 'done <task-id>' e.g. 'done 2'.\n\t"
                        + "- Once you are done, just enter 'bye' to quit the program.\n\t"
                        + "Have fun! ^_^");

    }

    public void printExitMessage() {
        prettify(EXIT_MESSAGE);
    }

    public void prompt() {
        prettify(INPUT_PROMPT);
    }
}