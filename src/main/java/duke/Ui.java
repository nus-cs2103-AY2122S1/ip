package duke;

import duke.task.Task;
import exception.DukeException;

/**
 * Deals with interaction with the user
 */
public class Ui {


    /**
     * Says bye to the user
     *
     * @return farewell message
     */
    public String bye() {
        return "Bye. Hope to see you again!";
    }

    /**
     * Shows the user the duke error message
     *
     * @param e the exception
     * @return error message
     */
    public String showErrorMessage(DukeException e) {
        return e.getMessage();
    }

    /**
     * Informs the user that there are no tasks in the list
     *
     * @return no task message
     */
    public String showNoTaskMessage() {
        return "There are no tasks!";
    }

    /**
     * Shows the user the current task list
     *
     * @param taskList the list of the tasks
     * @return task list
     */
    public String showTaskList(TaskList taskList) {
        assert taskList.size() != 0;
        StringBuilder list = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 1; i < taskList.size() + 1; i++) {
            String s = String.format("  %d.%s%n\n", i, taskList.get(i));
            list.append(s);
        }
        return list.toString();
    }

    /**
     * Informs the user that the task is deleted
     *
     * @param deletedTask the deleted task
     * @param taskListSize the number of tasks in list after deletion
     * @return delete message
     */
    public String showDeleteMessage(Task deletedTask, int taskListSize) {
        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.%n",
                deletedTask, taskListSize);
    }

    /**
     * Informs the user that the task is added
     *
     * @param newTask the task to add to list
     * @param taskListSize the number of tasks in list after adding new task
     * @return add task message
     */
    public String showAddTaskMessage(Task newTask, int taskListSize) {
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.%n",
                newTask, taskListSize);
    }

    /**
     * Informs the user that the task is marked as done
     *
     * @param doneTask the task after marking as done
     * @return done message
     */
    public String showDoneMessage(Task doneTask) {
        return "Nice! I've marked this task as done:\n  " + doneTask;
    }

    /**
     * Shows the user the matched tasks
     *
     * @param keyword the keyword user enters
     * @param taskList the list of tasks
     */
    public String showMatchMessage(String keyword, TaskList taskList) {
        StringBuilder list = new StringBuilder("Here are the matching tasks in your list:\n");
        int currentNo = 1;
        for (int i = 1; i < taskList.size() + 1; i++) {
            if (taskList.get(i).toString().contains(keyword)) {
                String s = String.format("  %d.%s%n\n", currentNo, taskList.get(i));
                currentNo++;
                list.append(s);
            }
        }
        return list.toString();
    }

    /**
     * Informs the user the task is edited
     *
     * @param editTask the task that was edited
     * @return edit message
     */
    public String showEditMessage(Task editTask) {
        return String.format("Got it. I've edited this task:\n  %s",
                editTask);
    }
}
