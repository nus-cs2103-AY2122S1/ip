package duke.util;

import duke.task.*;
import java.util.ArrayList;

public class Ui {
    private static final String EXIT_MESSAGE = "Goodbyeeee! Hope to see you again soon! :>";
    private static final String SEPARATOR = "\t-------------------------------------------------------";
    private static final String INPUT_PROMPT = "Enter a command *_*";
    private static final String TASK_LIST = "Here are the task(s) in your list! n_n\n\t";
    private static final String NO_TASKS_FOUND = "Nothing in the list... :( Type todo/event/deadline to add something first! :^)";

    /**
     * Formats the input and prints it in a formatted version.
     *
     * @param message message to be printed.
     */
    public static void prettify(String message) {
        System.out.printf("%s\n\t%s\n%s\n", SEPARATOR, message, SEPARATOR);
    }

    /** Prints introduction message when bot is first launched. **/
    public static void printIntroMessage() {
        prettify(
                "Hello! I'm Duke, your personal CLI bot! :D\n\t"
                        + "I function as a simple TodoList.\n\t"
                        + "I can keep track of 3 types of tasks:\n\t"
                        + "\t> Todo: To add a new todo task, enter 'todo' followed by a task description.\n\t"
                        + "\t> Deadline: To add a new deadline, enter 'deadline' followed by the task description "
                        + "and specify the due date using '/by <due-date>'\n\t"
                        + "\t> Event: To add a new event, enter 'event' followed by the event description "
                        + "and specify the time using '/by <time-stamp>'\n\t"
                        + "- To see all your tasks, enter 'list'.\n\t"
                        + "- To delete a task, enter 'delete' followed by the index of the task you wish to delete (e.g delete 2).\n\t"
                        + "- You can also mark tasks as done by typing 'done' followed by the index of the task you completed (e.g done 2).\n\t"
                        + "- Once you are done, just enter 'bye' to quit the program.\n\t"
                        + "Have fun! ^_^");

    }

    public static void printExitMessage() {
        prettify(EXIT_MESSAGE);
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        StringBuilder allTasks = new StringBuilder(TASK_LIST);
        if (taskList.size() == 0) {
            prettify(NO_TASKS_FOUND);
        } else {
            for (int i  = 0; i < taskList.size(); i++) {
                int taskNumber =  i + 1;
                String taskName = taskList.get(i).toString();
                allTasks.append(String.format("\t%d. %s\n\t", taskNumber, taskName));
            }
            prettify(allTasks.toString());
        }
    }

    public static void prompt() {
        prettify(INPUT_PROMPT);
    }



}
