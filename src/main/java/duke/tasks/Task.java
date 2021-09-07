package duke.tasks;

import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * Represents a task.
 */
public class Task {

    /** Description of task. */
    private final String description;

    /** Whether the task has been completed. */
    private boolean isDone;

    /** List of expenses for current task. */
    private HashMap<String, Float> expenses;

    private DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Constructor for Task.
     *
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        expenses = new HashMap<>();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns done status of task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Adds an expense to the task.
     *
     * @param purpose purpose for expenditure.
     * @param amount amount spent.
     */
    public void addExpenseToTask(String purpose, float amount) {
        expenses.put(purpose, amount);
    }

    /**
     * Displays all expenses of the task.
     *
     * @return a string containing all the expenses of the task.
     */
    public String displayExpenses() {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append(this + ": \n");
        for (String purpose : expenses.keySet()) {
            sb.append("\t" + count + ". ");
            sb.append(purpose + ": $");
            sb.append(df.format(expenses.get(purpose)));
            sb.append("\n");
            count++;
        }
        return sb.toString();
    }

    /**
     * Formats expenses in task to be ready to be saved to the data file.
     *
     * @return a string containing all expenses of the task in the correct format to be saved.
     */
    public String formatExpensesToSave() {
        StringBuilder sb = new StringBuilder();
        for (String purpose : expenses.keySet()) {
            sb.append(purpose + "|");
            sb.append(df.format(expenses.get(purpose)) + "|");
        }
        return sb.toString();
    }

    /**
     * Boolean to determine if this task currently contains any expenses.
     *
     * @return boolean which says if this task currently contains any expenses.
     */
    public boolean hasExpenses() {
        return !expenses.isEmpty();
    }

    /**
     * toString method of Task.
     *
     * @return toString description of Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
