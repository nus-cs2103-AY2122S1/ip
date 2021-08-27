package abyss;

import abyss.task.Task;
import abyss.task.TaskList;

/**
 * Represents a storage file which stores and updates user tasks.
 */
public abstract class Ui {
    /**
     * Prints reply with given messages in the interface.
     *
     * @param messages  Messages to be printed.
     */
    public static void reply(String... messages) {
        System.out.println(formatReply(messages));
    }

    /**
     * Prints reply after a task is successfully added.
     *
     * @param task Task that is added.
     * @param numberOfTasks Number of tasks in the task list.
     */
    public static void replyTaskAdded(Task task, int numberOfTasks) {
        String addedMsg = "Task piece is added to the Abyss.";
        String tasksLeftMsg = "The Abyss now contains " + numberOfTasks + " task piece(s).";
        Ui.reply(addedMsg, task.toString(), tasksLeftMsg);
    }

    /**
     * Formats messages into a reply chat bubble.
     *
     * @param messages Messages to be included in the reply.
     * @return Formatted reply.
     */
    public static String formatReply(String[] messages) {
        String reply = "\t......................................................\n";
        for (int i = 0; i < messages.length; i++) {
            reply += "\t " + messages[i] + "\n";
        }
        reply += "\n\t......................................................";
        return reply;
    }

    /**
     * Formats a list of tasks into a reply chat bubble.
     *
     * @param tasks List of tasks.
     * @return Formatted reply.
     */
    public static String formatListReply(TaskList tasks) {
        String reply = "\t......................................................\n";
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            Task task = tasks.get(i);
            reply += "\t " + (i + 1) + "." + task.toString() + "\n";
        }
        if (tasks.getNumberOfTasks() == 0) {
            reply += "\t The Abyss is empty.\n";
        }
        reply += "\n\t......................................................";
        return reply;
    }

    /**
     * Prints the Abyss logo.
     */
    public static void printLogo() {
        String logo =
                "  ('-.    .-. .-')                .-')     .-')   \n"
                        + "  ( OO ).-.\\  ( OO )              ( OO ).  ( OO ). \n"
                        + "  / . --. / ;-----.\\  ,--.   ,--.(_)---\\_)(_)---\\_)\n"
                        + "  | \\-.  \\  | .-.  |   \\  `.'  / /    _ | /    _ | \n"
                        + ".-'-'  |  | | '-' /_).-')     /  \\  :` `. \\  :` `. \n"
                        + " \\| |_.'  | | .-. `.(OO  \\   /    '..`''.) '..`''.)\n"
                        + "  |  .-.  | | |  \\  ||   /  /\\_  .-._)   \\.-._)   \\\n"
                        + "  |  | |  | | '--'  /`-./  /.__) \\       /\\       /\n"
                        + "  `--' `--' `------'   `--'       `-----'  `-----' \n";
        System.out.println(logo);
    }
}
