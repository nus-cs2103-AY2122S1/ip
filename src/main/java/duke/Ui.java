package duke;

import java.util.ArrayList;

/**
 * Handles interactions with user through message outputs.
 */
public class Ui {

    /**
     *
     * @param error
     * @return
     */
    public String showError(String error) {
        return error;
    }

    /**
     *
     * @return
     */
    public String printGreeting() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     *
     * @param doneTask
     * @return
     */
    public String printDoneMessage(Task doneTask) {
        return String.format("Nice! I've marked this task as done: %s", doneTask);
    }

    /**
     *
     * @param deletedTask
     * @return
     */
    public String printDeleteMessage(Task deletedTask) {
        return String.format("Noted. I've removed this task: %s", deletedTask);
    }

    public String printAddMessage(Task addedTask) {
        return String.format("Got it. I've added this task: %s", addedTask);
    }

    /**
     *
     * @param size
     * @return
     */
    public String printTaskListSize(int size) {
        if (size == 1) {
            return "Now you have 1 task in the list." ;
        } else {
            return String.format("Now you have %d tasks in the list.", size);
        }
    }

    /**
     *
     * @return
     */
    public String printGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     *
     * @param matchingTaskList
     * @return
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
     *
     * @param taskList
     * @return
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
