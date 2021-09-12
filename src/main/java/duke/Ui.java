package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the UI of the chatbot.
 * Contains a Scanner that reads the input given by the user.
 */

public class Ui {

    private Scanner sc;
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Creates a UI Object containing a Scanner that reads the inputs.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the error message in the case the file path that the Storage is supposed to read from does not exist.
     */
    public void showLoadingError() {
        System.out.println("File Not Found");
    }

    /**
     * Returns the message when a Task is added to the TaskList.
     * @param task Task to be added to the TaskList
     * @param tasks TaskList the Task is to be added to.
     * @return message to be printed;
     */

    public String printAdd(Task task, TaskList tasks) {
        return "Got it. I've added this task: \n"
                + String.format("  %s", task.toString())
                + "\n"
                + String.format("Now you have %d tasks in the list", tasks.getSize());
    }

    /**
     * Returns the message when a Task is completed in the TaskList.
     * @param tasks TaskList containing the Task that was completed.
     * @param toComplete Index of the completed Task in the TaskList
     * @return Message to be printed
     */
    public String printDone(TaskList tasks, int toComplete) {
        return "Nice! I've marked this task as done:\n"
                + String.format("  %s", tasks.get(toComplete));
    }

    /**
     * Returns the message when a Task is deleted in the TaskList.
     * @param tasks TaskList containing the Task that was deleted.
     * @param toDelete Index of the deleted Task in the TaskList
     * @return Message to be printed
     */
    public String printDelete(TaskList tasks, int toDelete) {
        return "Noted. I've removed this task: \n"
                + String.format("  %s", tasks.get(toDelete))
                + "\n"
                + String.format("Now you have %d tasks in the list", tasks.getSize() - 1);
    }

    /**
     * Prints the message when the Bot is Terminated.
     */
    public String printExit() {
        sc.close();
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out the list of Tasks stored in the TaskList.
     * @param tasks TaskList to be printed.
     */
    public String printList(TaskList tasks) {
        String output = "Here are the tasks in your list: ";
        for (int i = 0; i < tasks.getSize(); i++) {
            output += "\n" + (i + 1) + ". " + tasks.get(i);
        }
        return output;
    }

}