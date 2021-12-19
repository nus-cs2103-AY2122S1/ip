package abyss;

import abyss.task.Task;
import abyss.task.TaskManager;

/**
 * Represents a storage file which stores and updates user tasks.
 */
public abstract class Ui {
    /**
     * Returns reply after a task is successfully added.
     *
     * @param task Task that is added.
     */
    public static String replyTaskAdded(Task task) {
        int numberOfTasks = Abyss.getTaskManager().getNumberOfTasks();
        String addedMsg = "Task piece is added to the Abyss.";
        String tasksLeftMsg = "The Abyss now contains " + numberOfTasks + " task piece(s).";
        return formatReply(addedMsg, task.toString(), tasksLeftMsg);
    }

    /**
     * Returns reply after a task is successfully edited.
     *
     * @param task Task that is edited.
     */
    public static String replyTaskEdited(Task task) {
        int numberOfTasks = Abyss.getTaskManager().getNumberOfTasks();
        String addedMsg = "Task piece is edited.";
        String tasksLeftMsg = "The Abyss now contains " + numberOfTasks + " task piece(s).";
        return formatReply(addedMsg, task.toString(), tasksLeftMsg);
    }

    /**
     * Returns reply after a task is successfully deleted.
     *
     * @param task Task that is deleted.
     */
    public static String replyTaskDeleted(Task task) {
        int numberOfTasks = Abyss.getTaskManager().getNumberOfTasks();
        String removedMsg = "Task piece is swallowed by the Abyss.";
        String tasksLeftMsg = "The Abyss now contains " + numberOfTasks + " task piece(s).";
        return formatReply(removedMsg, task.toString(), tasksLeftMsg);
    }

    /**
     * Returns reply after a task is successfully deleted.
     *
     * @param task Task that is deleted.
     */
    public static String replyTaskMarked(Task task) {
        String markedMsg = "Task piece is lit up in the Abyss.";
        String markedTask = "  " + task.toString();
        return formatReply(markedMsg, markedTask);
    }

    /**
     * Formats messages into a reply.
     *
     * @param messages Messages to be included in the reply.
     * @return Formatted reply.
     */
    public static String formatReply(String ...messages) {
        String reply = "";
        for (int i = 0; i < messages.length; i++) {
            reply += messages[i] + "\n";
        }
        return reply;
    }

    /**
     * Formats a list of tasks into a reply.
     *
     * @param taskManager Task manager containing list of tasks.
     * @return Formatted reply.
     */
    public static String formatListReply(TaskManager taskManager) {
        String reply = "";
        for (int i = 0; i < taskManager.getNumberOfTasks(); i++) {
            Task task = taskManager.get(i);
            reply += (i + 1) + "." + task.toString() + "\n";
        }
        if (taskManager.getNumberOfTasks() == 0) {
            reply += "The Abyss is empty.";
        }
        return reply;
    }
}
