package duke.ui;

import duke.commands.Task;
import duke.data.TaskList;

/**
 * Encapsulates the Ui component of Duke.
 *
 * @author Owen Tan
 * @version Duke Level-9
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
    public void showGreeting() {
        System.out.println("Aloha! I'm Sophia.\nWhat can I do for you?");
    }
    public void showFarewellMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showAddTaskMsg(Task text) {
        System.out.println("Sure thing! I've added this task:\n  " + text);
    }

    public void showCompleteTaskMsg(Task text) {
        System.out.println("Nice! I've marked this task as done:\n  " + text);
    }

    public void showDeleteTaskMsg(Task text) {
        System.out.println("Noted! I've removed this task:\n  " + text);
    }

    /**
     * Prints number of tasks in TaskList.
     */
    public void showListCountMsg() {
        int len = tasks.getLength();
        if (len == 1) {
            System.out.println(String.format("Now you have %d task in the list.", len));
        } else {
            System.out.println(String.format("Now you have %d tasks in the list.", len));
        }
    }
}
