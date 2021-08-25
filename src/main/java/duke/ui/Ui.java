package duke.ui;

import duke.commands.Task;
import duke.data.TaskList;

/**
 * Encapsulates the Ui component of Duke.
 * This class deals with interactions with the user.
 *
 * @author: Jason Ng
 * @version: Duke Level-9
 */
public class Ui {
    /** The border to be printed for messages */
    private static final String BORDER = "____________________________________________________________";
    /** The introductory message to be printed for the start of the program */
    private static final String INTRO = "Hello from\n"
                                        +  " ____        _        \n"
                                        + "|  _ \\ _   _| | _____ \n"
                                        + "| | | | | | | |/ / _ \\\n"
                                        + "| |_| | |_| |   <  __/\n"
                                        + "|____/ \\__,_|_|\\_\\___|\n"
                                        + "Im Duke\nWhat can I do for you?";

    /**
     * Prints the introductory message for Duke
     */
    public void PrintIntro() {
        System.out.println(INTRO);
    }

    /**
     * Prints the input message for Duke.
     * Message is lined with the borders stored in the attribute of the class.
     *
     * @param message The message to be printed.
     */
    public void PrintMessage(String message) {
        System.out.println(BORDER);
        System.out.println(message);
        System.out.println(BORDER);
    }

    /**
     * Prints the entire taskList.
     *
     * @param taskList The taskList to be printed.
     */
    public void PrintList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(BORDER);
        for (int i = 1; i <= taskList.size(); i++) {
            Task thisTask = taskList.get(i-1);
            String toPrint = String.format("%d. %s", i, thisTask);
            System.out.println(toPrint);
        }
        System.out.println(BORDER);
    }

    /**
     * Prints the message after special tasks are added to the taskList.
     *
     * @param taskList The taskList which the tasks are added to.
     */
    public void PrintSpecialTasks(TaskList taskList) {
        String message = taskList.get(taskList.size() - 1).toString();
        int total = taskList.size();
        String newMsg = String.
                format("Got it, I've added this task:\n  %s\nNow you have a total of %d tasks in the list.",
                message, total);
        PrintMessage(newMsg);
    }

    /**
     * Prints the message after a deletion of a task.
     * The deleted task is also printed,
     * together with the number of remaining tasks in the taskList.
     *
     * @param deleted The deleted task.
     * @param taskList The taskList the task was deleted from.
     */
    public void PrintDelete(Task deleted, TaskList taskList) {
        String message = deleted.toString();
        int total = taskList.size();
        String newMsg = String.
                format("Noted. I've removed this task:\n  %s\nNow you have a total of %d tasks in the list.",
                        message, total);
        PrintMessage(newMsg);
    }

    /**
     * Prints the message after a find of tasks.
     *
     * @param matchedTasks The list of matched tasks.
     */
    public void PrintFind(TaskList matchedTasks) {
        System.out.println(BORDER);
        System.out.println("Here are the matching tasks in your list: ");
        for (int i = 1; i <= matchedTasks.size(); i++) {
            Task thisTask = matchedTasks.get(i-1);
            String toPrint = String.format("%d. %s", i, thisTask);
            System.out.println(toPrint);
        }
        System.out.println(BORDER);
    }
}
