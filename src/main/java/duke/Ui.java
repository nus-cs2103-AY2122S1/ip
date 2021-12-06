package duke;

import java.util.ArrayList;

public class Ui {

    public Ui() {
    }

    /**
     * Duke's greeting message seen by the user upon start-up.
     */
    public static String greeting() {
        String greeting = "I am Jo the Frog! RIBBIT!\n";
        return greeting + "How may I help you?\n";
    }

    /**
     * Message to be seen by user if hard disk file does not exist.
     */
    public static String showLoadingError() {
        return "File not detected, new file will be created!";
    }

    /**
     * Message signalling completion of task by user.
     *
     * @param tasks The TaskList to be affected
     * @param index The index of the task to be marked done
     */
    public static String showDoneMessage(TaskList tasks, int index) {
        return "You have swallowed that pesky fly! RIBBIT!\n" +
                "  " + tasks.get(index).toString() + "\n";
    }

    /**
     * Message to be seen by user upon deleting the given task.
     *
     * @param tasks The TaskList to be affected
     * @param description The description of the task to be deleted
     */
    public static String showDeleteMessage(TaskList tasks, String description) {
        return "Rotten flies deserve to die!\n" +
                " " + description + "\n" +
                "Now you have " + tasks.size() + " flies to eat! RIBBIT!\n";
    }

    /**
     * Message to be seen by user upon "list" user input.
     *
     * @param tasks The TaskList to be affected
     */
    public static String showListMessage(TaskList tasks) {
        StringBuilder message = new StringBuilder();
        message.append("Here is your menu for today:\n");
        for (int i = 0; i < tasks.size(); i++) {
            message.append(i + 1 + "." + tasks.get(i).toString() + "\n");
        }
        return message.toString();
    }

    /**
     * Message to be seen by user when they add a task into the task list.
     *
     * @param tasks The TaskList to be affected
     */
    public static String addTaskMessage(TaskList tasks) {
        return "A fly has been added to the menu:\n" +
                " " + tasks.get(tasks.size() - 1).toString() + "\n" +
                "Now you have " + tasks.size() + " flies to eat! RIBBIT!";
    }

    public static String showFindMessage(TaskList tasks, ArrayList<Integer> matches) {
        StringBuilder message = new StringBuilder();
        message.append("Here are the flies with the matching smell:\n");
        for (int i = 0; i < matches.size(); i++) {
            message.append(i + 1 + "." + tasks.get(matches.get(i)).toString() + "\n");
        }
        return message.toString();
    }
}