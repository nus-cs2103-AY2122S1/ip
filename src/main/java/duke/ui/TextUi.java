package duke.ui;

import duke.TaskList;
import duke.tasks.Task;

public class TextUi {
    public static final String DIVIDER = "==========================================";

    /**
     * Prints a welcome message and a divider right after the programme starts.
     */
    public static void showWelcomeMessage() {
        System.out.println("Hi, Nat. Type out your task right away! :D");
        System.out.println(DIVIDER);
    }

    /**
     * Prints the task numbered according to the tasklist.
     *
     * @param i The index of the task.
     * @param t The task.
     */
    public static void showTaskNumbered(int i, Task t) {
        System.out.printf("%s. %s\n", i + 1, t);
    }

    /**
     * Prints the message after a task is added.
     *
     * @param tl The TaskList.
     */
    public static void showTaskAdded(TaskList tl) {
        System.out.println("added: " + tl.getTaskList().get(tl.getLength() - 1));
    }

    /**
     * Prints the message after a task is removed.
     *
     * @param t The task.
     */
    public static void showTaskRemoved(Task t) {
        System.out.printf("Noted. I've removed this task:\n%s\n", t);
    }

    /**
     * Prints the message after a task is marked as done.
     *
     * @param t The task.
     */
    public static void showTaskDone(Task t) {
        System.out.printf("%s\nThe task is marked as done! Good job :D\n", t);
    }

    /**
     * Prints the message after the TaskList is updated.
     *
     * @param tl The TaskList.
     */
    public static void showUpdatedNumberOfTasks(TaskList tl) {
        int len = tl.getLength();
        if (len == 1) {
            System.out.printf("Now you have %d task in the list.\n", tl.getLength());
        } else {
            System.out.printf("Now you have %d tasks in the list.\n", tl.getLength());
        }
    }

    /**
     * Prints the error message.
     *
     * @param s The error message in String.
     */
    public static void showErrorMessage(String s) {
        System.out.println(s);
    }

    /**
     * Prints a goodbye message when the user exits the chat bot.
     */
    public static void showGoodbyeMessage() {
        System.out.println("See you! :)");
    }

}
