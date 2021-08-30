package duke;

import java.util.ArrayList;

import duke.task.Task;


/**
 * Class to handle the user interface of Duke.
 */
public class Ui {
    private static final String strBreak = "    ____________________________________________________________\n";

    /**
     * Prints out sentence welcoming user when Duke starts.
     *
     * @return welcome message by Duke.
     */
    public String showWelcome() {
        return "Urgh I hate having to wake up. Why did you do that";
    }

    /**
     * Returns confirmation when user adds a task.
     *
     * @param task the task which is added.
     * @param count the current number of tasks.
     * @return confirmation when user adds a task.
     */
    public String taskAdded(Task task, int count) {
        String toPrint = String.format("     Got it. I've added this task:\n     "
                        + "%s\n     Now you have %x task%s in the list.",
                task.toString(), count, count > 1 ? "s" : "");
        return strBreak + toPrint + "\n" + strBreak;
    }

    /**
     * Return string saying bye.
     *
     * @return String saying bye.
     */
    public String sayBye() {
        return "Don't wake me up again";
    }

    /**
     * Returns sentence confirming when user marks a task as done.
     *
     * @param task task that is marked done.
     * @return sentence confirming when user marks a task as done.
     */
    public String showMarkDone(Task task) {
        return "Toight!\n" + task + " marked done.";
    }

    /**
     * Returns sentence confirming when user deletes a task.
     *
     * @param task task that is deleted.
     * @return string for delete command.
     */
    public String deleteTask(Task task) {
        return task + " deleted. Bruh be more careful next time";
    }

    /**
     * Return string of the list of current tasks.
     *
     * @param tasks the current list of tasks.
     * @return string of list of current tasks.
     */
    public String printList(Tasklist tasks) {
        return tasks.toString();
    }

    /**
     * Returns string for exception thrown.
     *
     * @param e the exception that was thrown.
     * @return string for exception thrown.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }

    /**
     * Returns string for find command.
     *
     * @param tasks tasks that are related to keyword.
     * @param keywords keywords that user is searching for.
     * @return string for find command.
     */
    public String printRelatedTasks(Tasklist tasks, ArrayList<String> keywords) {
        String strKeywords = keywords.stream().reduce((x, y) -> x + " " + y).orElse("");
        return "Your tasks that match with " + strKeywords + ":\n" + this.printList(tasks);
    }
}
