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
    public static String showTaskNumbered(int i, Task t) {
        System.out.printf("%s. %s\n", i + 1, t);
        return (i + 1) + ". " + t.toString() + "\n";
    }

    /**
     * Prints the message after a task is added.
     *
     * @param tasks The TaskList.
     */
    public static String showTaskAdded(TaskList tasks) {
        System.out.println("added: " + tasks.getTaskList().get(tasks.getLength() - 1));
        return "added: " + tasks.getTaskList().get(tasks.getLength() - 1);
    }

    /**
     * Prints the message after a task is removed.
     *
     * @param t The task.
     */
    public static String showTaskRemoved(Task t) {
        System.out.printf("Noted. I've removed this task:\n%s\n", t);
        return "Noted. I've removed this task: \n" + t.toString();
    }

    /**
     * Prints the message after a task is marked as done.
     *
     * @param t The task.
     */
    public static String showTaskDone(Task t) {
        System.out.printf("%s\nThe task is marked as done! Good job :D\n", t);
        return t.toString() + "\nThe task is marked as done! Good job :D";
    }

    /**
     * Prints the message after the TaskList is updated.
     *
     * @param tasks The TaskList.
     */
    public static String showUpdatedNumberOfTasks(TaskList tasks) {
        int len = tasks.getLength();
        if (len == 1) {
            System.out.printf("Now you have %d task in the list.\n", tasks.getLength());
            return "Now you have " + tasks.getLength() + " task in the list.";
        } else {
            System.out.printf("Now you have %d tasks in the list.\n", tasks.getLength());
            return "Now you have " + tasks.getLength() + " tasks in the list.";
        }
    }

    /**
     * Prints the error message.
     *
     * @param s The error message in String.
     */
    public static String showErrorMessage(String s) {
        System.out.println(s);
        return s;
    }

    /**
     * Prints a goodbye message when the user exits the chat bot.
     */
    public static String showGoodbyeMessage() {
        System.out.println("See you! :)");
        return "See you! :)";
    }

}
