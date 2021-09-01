package duke;

import java.util.ArrayList;

/**
 * Handles interactions with user through message outputs.
 */
public class Ui {

    /**
     * Returns error message.
     *
     * @param error message
     * @return error message
     */
    public String showError(String error) {
        return error;
    }

    /**
     * Returns greeting when program first begins.
     *
     * @return greeting message
     */
    public String showGreeting() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Returns message showing the task marked as done.
     *
     * @param doneTask the task marked as done
     * @return message acknowledging the task is marked as done
     */
    public String showDoneMessage(Task doneTask) {
        return String.format("Nice! I've marked this task as done: %n%s", doneTask);
    }

    /**
     * Returns message showing the task that was deleted.
     *
     * @param deletedTask the task that was deleted
     * @return message acknowledging the task as deleted
     */
    public String showDeletedMessage(Task deletedTask) {
        return String.format("Noted. I've removed this task: %n%s", deletedTask);
    }

    /**
     * Returns message showing the task that was added.
     *
     * @param addedTask the task that was added
     * @return message acknowledging the task as added
     */
    public String showAddMessage(Task addedTask) {
        return String.format("Got it. I've added this task: %n%s", addedTask);
    }

    /**
     * Returns message showing how many tasks are in the task list
     *
     * @param size of the task list
     * @return message showing how many tasks are in the task list
     */
    public String showTaskListSize(int size) {
        if (size == 1) {
            return "Now you have 1 task in the list.";
        } else {
            return String.format("Now you have %d tasks in the list.", size);
        }
    }

    /**
     * Returns goodbye when program ends.
     *
     * @return goodbye message
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns all the tasks that match the user's search term.
     *
     * @param matchingTaskList task list of tasks that matches the user's search term
     * @return message showing all the tasks in the matching task list
     */
    public String showMatchingTasks(ArrayList<Task> matchingTaskList) {
        if (matchingTaskList.size() == 0) {
            return "There are no tasks containing that term in your list!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the matching tasks in your list:\n");
            int num = 1;
            for (Task task : matchingTaskList) {
                sb.append(String.format("%d.%s%n", num, task));
                num++;
            }
            return sb.toString();
        }
    }

    /**
     * Returns all the tasks that the user has.
     *
     * @param taskList list of tasks
     * @return message showing all the tasks the user has
     */
    public String showTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            return "There are no tasks in your list!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are tasks in your list:\n");
            int num = 1;
            for (Task task : taskList) {
                sb.append(String.format("%d.%s%n", num, task));
                num++;
            }
            return sb.toString();
        }
    }
}
