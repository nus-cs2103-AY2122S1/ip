package duke.tasks;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import duke.exception.EmptyListException;
import duke.exception.InvalidIndexException;

/**
 * Represents a task.
 */
public class Task {

    /** Description of task. */
    private final String description;

    /** Whether the task has been completed. */
    private boolean isDone;

    /** List of expenses for current task. */
    private final HashMap<String, ArrayList<Float>> expenses;

    /** Formatter to convert floats to 2 decimal places. */
    private final DecimalFormat df = new DecimalFormat("0.00");

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
        ArrayList<Float> spending = expenses.get(purpose);
        if (spending == null) {
            spending = new ArrayList<>();
        }
        spending.add(amount);
        expenses.put(purpose, spending);
    }

    /**
     * Deletes an expense from the task.
     *
     * @param deleteIndex index of expense to be deleted.
     * @throws EmptyListException if the list of expenses is currently empty.
     * @throws InvalidIndexException if the index of expense to be deleted is out of bounds.
     */
    public String deleteExpenseFromTask(int deleteIndex) throws EmptyListException,
            InvalidIndexException {

        if (expenses.isEmpty()) {
            throw new EmptyListException();
        } else if (deleteIndex < 0 || deleteIndex >= expenses.size()) {
            throw new InvalidIndexException(1, expenses.size(), deleteIndex + 1);
        }
        int count = 0;
        for (String key : expenses.keySet()) {
            if (count == deleteIndex) {
                expenses.remove(key);
                return key;
            }
            count++;
        }
        return "";
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
            sb.append(purpose + ": ");
            ArrayList<Float> spending = expenses.get(purpose);
            for (Float f : spending) {
                sb.append("$" + df.format(f) + "|");
            }
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
            for (Float f : expenses.get(purpose)) {
                sb.append(purpose + "|");
                sb.append(df.format(f) + "|");
            }
        }
        return sb.toString();
    }

    /**
     * Sums all expenses in the task and returns it in a formatted String message.
     *
     * @return total expenses in the task as a formatted String message.
     */
    public String sumExpenseToString() {
        float total = 0;
        for (String purpose : expenses.keySet()) {
            for (Float f : expenses.get(purpose)) {
                total += f;
            }
        }
        return df.format(total) + " spent on " + this;
    }

    /**
     * Sums all expenses in the task.
     *
     * @return total expenses in the task.
     */
    public float sumExpense() {
        float total = 0;
        for (String purpose : expenses.keySet()) {
            for (Float f : expenses.get(purpose)) {
                total += f;
            }
        }
        return total;
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
     * Parses the Task to a format to be saved.
     *
     * @return a string containing only important information of the task to be saved.
     */
    public String parseToSave() {
        return getStatusIcon() + "|" + description;
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
