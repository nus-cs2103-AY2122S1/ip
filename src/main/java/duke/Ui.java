package duke;

import java.util.ArrayList;

public class Ui {

    /**
     * Displays a greeting message.
     */
    public String greet() {
        return "Hello from Duke! :D\n"+ "What can I do for you?";
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

    /**
     * Returns the message to be displayed when a given task is marked as done.
     *
     * @param task The task that is marked as done.
     * @return The message.
     */
    public String getDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n"
                + "  " + task;
    }

    /**
     * Returns the message to be displayed when the task list is listed.
     *
     * @param taskList The task list that is listed.
     * @return The message.
     */
    public String getListMessage(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTaskList();
        if (tasks == null || tasks.isEmpty()) {
            return "You currently have no tasks!";
        } else {
            StringBuilder result = new StringBuilder();
            result.append("Here are your tasks:\n");
            for (int i = 0; i < tasks.size(); i++) {
                result.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            return result.toString();
        }
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

    /**
     * Returns the message to be displayed when a task at a given index is updated.
     *
     * @param index The index of the task to be updated.
     * @param task The task after being updated.
     * @return The message.
     */
    public String getUpdateMessage(int index, Task task) {
        return "Nice! I've updated this task:\n"
                + index + ". " + task;
    }
}
