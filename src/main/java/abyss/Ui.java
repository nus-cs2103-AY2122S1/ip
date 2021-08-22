package abyss;

import abyss.task.Task;
import abyss.task.TaskList;

public abstract class Ui {
    public static void reply(String... messages) {
        System.out.println(formatReply(messages));
    }

    public static void replyTaskAdded(Task task, int numberOfTasks) {
        String addedMsg = "Task piece is added to the Abyss.";
        String tasksLeftMsg = "The Abyss now contains " + numberOfTasks + " task piece(s).";
        Ui.reply(addedMsg, task.toString(), tasksLeftMsg);
    }

    private static String formatReply(String[] messages) {
        String reply = "\t......................................................\n";
        for (int i = 0; i < messages.length; i++) {
            reply += "\t " + messages[i] + "\n";
        }
        reply += "\n\t......................................................";
        return reply;
    }

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
