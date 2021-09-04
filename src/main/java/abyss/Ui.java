package abyss;

import abyss.task.Task;
import abyss.task.TaskManager;

/**
 * Represents a storage file which stores and updates user tasks.
 */
public abstract class Ui {
    /**
     * Returns reply with given messages in the interface.
     *
     * @param messages  Messages to be printed.
     */
    public static String reply(String... messages) {
        return formatReply(messages);
    }

    /**
     * Returns reply after a task is successfully added.
     *
     * @param task Task that is added.
     */
    public static String replyTaskAdded(Task task) {
        int numberOfTasks = Abyss.getNumberOfTasks();
        String addedMsg = "Task piece is added to the Abyss.";
        String tasksLeftMsg = "The Abyss now contains " + numberOfTasks + " task piece(s).";
        return reply(addedMsg, task.toString(), tasksLeftMsg);
    }

    /**
     * Formats messages into a reply.
     *
     * @param messages Messages to be included in the reply.
     * @return Formatted reply.
     */
    public static String formatReply(String[] messages) {
        String reply = "";
        for (int i = 0; i < messages.length; i++) {
            reply += messages[i] + "\n";
        }
        return reply;
    }

    /**
     * Formats a list of tasks into a reply.
     *
     * @param tasks List of tasks.
     * @return Formatted reply.
     */
    public static String formatListReply(TaskManager tasks) {
        String reply = "";
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            Task task = tasks.get(i);
            reply += (i + 1) + "." + task.toString() + "\n";
        }
        if (tasks.getNumberOfTasks() == 0) {
            reply += "The Abyss is empty.";
        }
        return reply;
    }
}
