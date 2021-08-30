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
     */
    public String showWelcome() {
        return "Urgh I hate having to wake up. Why did you do that";
    }

    /**
     * Prints out confirmation when user adds a task.
     *
     * @param task the task which is added.
     * @param count the current number of tasks.
     */
    public String taskAdded(Task task, int count) {
        String toPrint = String.format("     Got it. I've added this task:\n     "
                        + "%s\n     Now you have %x task%s in the list.",
                task.toString(), count, count > 1 ? "s" : "");
        return strBreak + toPrint + "\n" + strBreak;
    }

    /**
     * Prints out sentence saying bye when user exits.
     */
    public String sayBye() {
        return "Don't wake me up again";
    }

    /**
     * Prints out sentence confirming when user marks a task as done.
     *
     * @param task task that is marked done.
     */
    public String showMarkDone(Task task) {
        return "Toight!\n" + task + " marked done.";
    }

    /**
     * Prints our sentence confirming when user deletes a task.
     *
     * @param task task that is deleted.
     */
    public String deleteTask(Task task) {
        return task + " deleted. Bruh be more careful next time";
    }

    /**
     * Prints out the list of current tasks.
     *
     * @param tasks the current list of tasks.
     */
    public String printList(Tasklist tasks) {
        return tasks.toString();
    }

    /**
     * Prints out the error message of exception that was thrown.
     *
     * @param e the exception that was thrown.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }

    /**
     * Prints tasks that are related to keywords.
     *
     * @param tasks tasks that are related to keyword.
     * @param keywords keywords that user is searching for.
     */
    public String printRelatedTasks(Tasklist tasks, ArrayList<String> keywords) {
        String strKeywords = keywords.stream().reduce((x, y) -> x + " " + y).orElse("");
        return "Your tasks that match with " + strKeywords + ":\n" + this.printList(tasks);
    }
}
