package duke.ui;

import duke.commands.Task;
import duke.data.TaskList;

/**
 * Encapsulates the Ui component of duke.Duke.
 *
 * @author Owen Tan
 * @version duke.Duke Level-9
 */
public class Ui {
    private TaskList tasks;

    /**
     * Public constructor for Ui.
     *
     * @param tasks Associated TaskList.
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }
    public String showGreeting() {
        String message = "Aloha! I'm Sophia.\nWhat can I do for you?";
        return message;
    }
    public String showFarewellMsg() {
        String message = "Bye. Hope to see you again soon!";
        return message;
    }

    public String showAddTaskMsg(Task text) {
        String message = "Sure thing! I've added this task:\n  " + text;
        return message;
    }

    public String showCompleteTaskMsg(Task text) {
        String message = "Nice! I've marked this task as done:\n  " + text;
        return message;
    }

    public String showDeleteTaskMsg(Task text) {
        String message = "Noted! I've removed this task:\n  " + text;
        return message;
    }

    /**
     * Prints number of tasks in TaskList.
     */
    public String showListCountMsg() {
        String message;
        int len = tasks.getLength();
        if (len == 1) {
            message = String.format("Now you have %d task in the list.", len);
        } else {
            message = String.format("Now you have %d tasks in the list.", len);
        }
        return message;
    }
}
