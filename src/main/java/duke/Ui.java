package duke;

import java.util.Base64;
import java.util.Scanner;

import duke.task.Task;

public class Ui {
    private static String welcomeMsg = "Hello! I'm Duke\nSay 'help' to see how I can help you.";
    private static String goodbyeMsg = "See you next time!";
    private static String secretCmdKey = new String(Base64.getDecoder().decode("aGVhZHBhdA==")); //Look away please
    private static Scanner sc = new Scanner(System.in);

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public String getGoodbyeMsg() {
        return goodbyeMsg;
    }

    public String getSecretCmdKey() {
        return secretCmdKey;
    }

    public String getHpMsg(int hpCount) {
        if (hpCount == 1) {
            return String.format("1 %s given", secretCmdKey);
        }
        return String.format("%d %ss given", hpCount, secretCmdKey);
    }

    /**
     * Formats the done adding task message after a task is successfully added to task list.
     *
     * @param tasks The user's task list.
     * @param t The task added.
     * @return The message to be displayed to user.
     */
    public String formatDoneAddingTaskMsg(TaskList tasks, Task t) {
        return "Got it. I've added this task: \n\t" + t
                + "\nNow you have " + formatNumTasks(tasks.getSize()) + " in the list.";
    }

    /**
     * Formats the number of tasks as part of a display message.
     * @param size The number of tasks.
     * @return A String representation of the number of tasks.
     */
    public String formatNumTasks(int size) {
        return size == 0 ? "no tasks"
                : size == 1 ? "1 task"
                : size + " tasks";
    }
}
