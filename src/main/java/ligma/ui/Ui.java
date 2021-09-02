package ligma.ui;

import ligma.TaskList;
import ligma.task.Task;

public class Ui {
    private static final String PARTITION = "______________________";

    /**
     * Prints all tasks from the tasklist given.
     *
     * @param tasks tasklist whose tasks are to be printed
     */
    public static String getStringTaskList(TaskList tasks) {
        String[] tasksText = tasks.getStringArr();
        int len = tasksText.length;
        String reply = "";
        for (int i = 0; i < len; i++) {
            reply += String.format("\n%d." + tasksText[i], i + 1);
        }
        return reply;
    }

    /**
     * Prints tasks that matches a target string for a FindCommand.
     *
     * @param tasks tasks that match the target string
     */
    public static String getMatches(Task[] tasks) {
        int len = tasks.length;
        String reply = "Found " + len + " matches:";
        for (int i = 0; i < len; i++) {
            reply += String.format("\n%d." + tasks[i], i + 1);
        }
        return reply;
    }

    /**
     * Prints Ligma's introduction.
     */
    public static String getIntroduction() {
        return "Hello! I'm Ligma, Ligma Balls.\nWhat can I do for you?";
    }

    /**
     * Prints Ligma's exit message.
     */
    public static String getFarewell() {
        return "Bye. I love Imagine Dragons...\n\n\n" +
                "Imagine Dragon Deez Nuts Cross Your Face.";
    }

    public static String getSuccessMessage(String commandDesc) {
        return "Successfully " + commandDesc;
    }

}
