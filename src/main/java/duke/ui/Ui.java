package duke.ui;

import duke.exception.LoadingException;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    /**
     * Returns the formatted string output.
     *
     * @param task The possible task string representations.
     * @param count The number of tasks.
     * @return The desirable output string related to task.
     */
    public String getOutputFrame(String task, int count) {
        String title = "Got it. I've added this task:" + System.lineSeparator();
        String middle = task + System.lineSeparator();
        String end = "Now you have " + count + " tasks in the list.";
        return title + middle + end;
    }

    /**
     * Shows the error message.
     *
     * @param message The error message.
     * @return error message.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Shows the deleted task.
     *
     * @param shouldDelete The task should be deleted.
     * @param tasks The list of tasks.
     * @return The deleted task string form.
     */
    public String showDelete(Task shouldDelete, TaskList tasks) {
        String title = "Noted. I've removed this task:" + System.lineSeparator();
        String out = shouldDelete.toString() + System.lineSeparator();
        String end = "Now you have " + tasks.getSize() + " tasks in the list.";
        return title + out + end;
    }

    /**
     * Shows adding the task to the list.
     *
     * @param task The task want to be shown.
     * @param count The number of tasks in task list.
     * @return The added string.
     */
    public String showAdding(String task, int count) {
        return getOutputFrame(task, count);
    }

    /**
     * Shows error while loading.
     *
     * @return The error message.
     */
    public String showLoadingError() {
        LoadingException e = new LoadingException();
        return e.getMessage();
    }

    /**
     * Shows the task marked as done.
     *
     * @param stringForm The string form of that task.
     * @return The string representation of done.
     */
    public String showDone(String stringForm) {
        String title = "Nice! I've marked this task as done:" + System.lineSeparator();
        String out = stringForm;
        return title + out;
    }

    public String showBye() {
        return "Bye, see you soon. ^-^";
    }

    /**
     * Shows the coming tasks in string representation.
     *
     * @param taskListMonth The tasks happened within a month.
     * @param taskListWeek The tasks happened within a week.
     * @return The string showing coming tasks.
     */
    public String showComings(TaskList taskListMonth, TaskList taskListWeek) {
        String titleMonth = "<<Coming tasks within a month>> " + System.lineSeparator();
        String listInMonth = taskListMonth.toString() + System.lineSeparator();
        String titleWeek = "<<Coming tasks within a week>> " + System.lineSeparator();
        String listInWeek = taskListWeek.toString() + System.lineSeparator();
        return titleMonth + listInMonth + titleWeek + listInWeek;
    }
}
