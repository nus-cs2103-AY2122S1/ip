package duke;

import java.util.ArrayList;

/**
 * Represents the user interface and helps prints long messages for the user.
 */
public class Ui {

    /**
     * Returns the welcoming message for the user and the current tasks in the user's todo list.
     *
     * @param taskList The user's current todo list.
     * @return welcoming message for user
     */
    public String getStartMessage(ArrayList<Task> taskList) {
        String str = "";
        str = str + "Hello I am Duke.\nWhat can I do for you?\n\n";
        if (taskList.size() > 0) {
            str = str + "Current number of tasks: " + taskList.size() + "\n";
            str = str + getIterateTaskList(taskList) + '\n';
        }
        str = str + "\n";
        return str;
    }

    /**
     * Returns the current tasks in the user's todo list.
     *
     * @param taskList The user's current todo list.
     * @return User's todo list.
     */
    public String getIterateTaskList(ArrayList<Task> taskList) {
        String str = "";
        if (taskList.size() == 0) {
            str = str + "List is empty!\n\n";
            return str;
        }
        str = str + "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            Task temp = taskList.get(i);
            str = str + (i + 1) + ". " + temp + "\n";
        }
        str = str + "\n";
        return str;
    }

    /**
     * Tells the user that the todo list is saved.
     *
     * @return Todo list is successfully saved message for the user.
     */
    public String getSaveMessage() {
        return "Your todo list is saved!";
    }

    /**
     * Returns the message for marking a task as done.
     *
     * @return Message for marking a task as done.
     */
    public String getMarkAsDoneMessage(Task task) {
        return "I have marked \"" + task.getDescription() + "\" as done!\n" + task + "\n";
    }

    /**
     * Returns the message for getting the number of task in user's todo list.
     *
     * @return Message  for getting the number of task in user's list.
     */
    public String getNumberOfTasksInList(TaskList taskList) {
        String str = "";
        assert(taskList != null);
        if (taskList.getNumOfTask() > 1) {
            str = str + "You have " + taskList.getNumOfTask() + " tasks left in your list.\n";
        } else {
            str = str + "You have " + taskList.getNumOfTask() + " task left in your list.\n";
        }
        str = str + "\n";
        return str;
    }

    /**
     * Returns the message for deleting a task.
     *
     * @return Message for deleting a task.
     */
    public String getDeletedTaskMessage(Task task) {
        return  "Noted. I've removed this task:\n" + task + "\n";
    }

    /**
     * Returns the message for adding a task.
     *
     * @return Message for adding a task.
     */
    public String getAddedTaskMessage(Task task) {
        return  "Got it! I have added this task:\n " + task + "\n";
    }


    /**
     * Gets the result of finding matching tasks.
     *
     * @param taskList The result of finding matching tasks.
     * @return A string of matching tasks.
     */
    public String getMatchingTaskList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            return "No matching task!\n\n";
        }
        String str = "Here are the tasks that match your search:\n";
        for (int i = 0; i < taskList.size(); i++) {
            Task temp = taskList.get(i);
            str = str + (i + 1) + "." + temp + "\n";
        }
        str = str + "\n";
        return str;
    }

    /**
     * Returns Duke's message when the user presses send or enter without typing anything.
     *
     * @return A string of response message.
     */
    public String getMessageForEmptyLineInput() {
        return "Please enter a new task or action.";
    }


}
