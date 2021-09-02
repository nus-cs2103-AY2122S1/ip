package duke;

import java.util.ArrayList;

public class Ui {

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Displays a greeting message.
     */
    public String greet() {
        return "Hello from\n" + logo +"\n"
                + "What can I do for you?";
    }

    /**
     * Displays a goodbye message.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a message for when a task is created.
     *
     * @param task The task created.
     * @param taskList The current TaskList.
     */
    public String getAddMessage(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n"
                + " " + task + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.";
    }

    public String getDeleteMessage(Task task, TaskList taskList) {
        return "Noted. I've removed this task:\n"
                + "  " + task + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.";
    }

    public String getDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n"
                + "  " + task;
    }

    /**
     * Displays the results of the find command.
     *
     * @param matchList The resultant list of tasks from calling find.
     */
    public String getFindMessage(ArrayList<Task> matchList) {
        if (matchList == null || matchList.isEmpty()) {
            return "Oh no, Duke cannot find any matches!";
        } else {
            StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");

            for (int i = 0; i < matchList.size(); i++) {
                result.append(i + 1).append(".  ").append(matchList.get(i));
            }
            return result.toString();
        }
    }
}
