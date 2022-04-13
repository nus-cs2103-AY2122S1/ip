package duke.command;

import java.io.IOException;

import duke.exception.EmptyListException;
import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the expense command.
 */
public class ExpenseCommand extends Command {

    /** Represents the expense command keyword. */
    public static final String COMMAND = "expense";

    /** Index of task to run expense commands on. */
    private final int taskIndex;

    /** Purpose of expenditure. */
    private final String purpose;

    /** Amount spent. */
    private final float amount;

    /** Boolean to check if the command is to list all expenses in task list. */
    private final boolean isList;

    /** Boolean to check if the command is to delete an expense from a task. */
    private final boolean isDelete;

    /** Boolean to check if the command is to display the expenses of a task. */
    private final boolean isDisplay;

    /** Boolean to check if the command is to delete an expense of a task. */
    private final int deleteIndex;

    /** Boolean to check if the command is to sum the expenses of a task. */
    private final boolean isSum;

    /** Boolean to check if the command is to sum all expenses in the task list. */
    private final boolean isSumAll;

    /**
     * Constructor of ExpenseCommand.
     *
     * @param taskIndex index of task run expense commands on.
     * @param purpose purpose of expenditure.
     * @param amount amount spent
     * @param isList whether showing the entire list of expenses is necessary.
     * @param isDisplay whether showing a particular task's expenses is necessary.
     */
    public ExpenseCommand(int taskIndex, String purpose, float amount, boolean isList, boolean isDisplay) {
        this.taskIndex = taskIndex;
        this.purpose = purpose;
        this.amount = amount;
        this.isList = isList;
        isDelete = false;
        this.isDisplay = isDisplay;
        deleteIndex = -1;
        isSum = false;
        isSumAll = false;
    }

    /**
     * Overloaded constructor for ExpenseCommand.
     *
     * @param taskIndex index of task run expense commands on.
     * @param purpose purpose of expenditure.
     * @param amount amount spent
     */
    public ExpenseCommand(int taskIndex, String purpose, float amount) {
        this.taskIndex = taskIndex;
        this.purpose = purpose;
        this.amount = amount;
        isList = false;
        isDelete = false;
        isDisplay = false;
        deleteIndex = -1;
        isSum = false;
        isSumAll = false;
    }

    /**
     * Overloaded constructor for ExpenseCommand.
     *
     * @param taskIndex index of task run expense commands on.
     * @param deleteIndex index of expense to be deleted.
     */
    public ExpenseCommand(int taskIndex, int deleteIndex) {
        this.taskIndex = taskIndex;
        this.deleteIndex = deleteIndex;
        purpose = "";
        amount = 0;
        isList = false;
        isDelete = true;
        isDisplay = false;
        isSum = false;
        isSumAll = false;
    }

    /**
     * Overloaded constructor for ExpenseCommand.
     *
     * @param taskIndex  index of task whose expenses are to be summed up.
     * @param isSum flag to check if the command is to sum the expenses of a task.
     */
    public ExpenseCommand(int taskIndex, boolean isSum) {
        this.taskIndex = taskIndex;
        deleteIndex = -1;
        purpose = "";
        amount = 0;
        isList = false;
        isDelete = false;
        isDisplay = false;
        this.isSum = isSum;
        isSumAll = false;
    }

    /**
     * Overloaded constructor for ExpenseCommand.
     *
     * @param isSumAll flag to check if the command is to sum all expenses in task list.
     */
    public ExpenseCommand(boolean isSumAll) {
        taskIndex = -1;
        deleteIndex = -1;
        purpose = "";
        amount = 0;
        isList = false;
        isDelete = false;
        isDisplay = false;
        isSum = false;
        this.isSumAll = isSumAll;
    }

    /**
     * Checks if purpose is empty.
     *
     * @return boolean that represents if purpose is empty.
     */
    private boolean isPurposeEmpty() {
        return purpose.equals("");
    }

    /**
     * Checks if index given is positive.
     *
     * @return boolean that represents if index given is positive.
     */
    private boolean isIndexValid() {
        return taskIndex >= 0;
    }

    /**
     * Executes the command.
     *
     * @param tasks list of tasks.
     * @param ui ui to handle user interaction.
     * @param storage handles reading and writing of data file.
     * @return appropriate message for each expense command.
     * @throws IOException if the file cannot be saved.
     * @throws EmptyListException if the list of tasks is empty.
     * @throws InvalidIndexException if an out of bounds index is given.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, EmptyListException,
            InvalidIndexException {
        if (!isIndexValid() && isList) {
            return tasks.showAllExpenses();
        } else if (isPurposeEmpty() && isDisplay) {
            // list all expenses of a specific task
            return tasks.showExpense(taskIndex);
        } else if (isDelete) {
            String message = tasks.deleteExpense(taskIndex, deleteIndex);
            storage.save(tasks);
            return message;
        } else if (isSum) {
            return "$" + tasks.sumExpense(taskIndex);
        } else if (isSumAll) {
            return "$" + tasks.sumAllExpense();
        }
        String message = tasks.addExpense(taskIndex, purpose, amount);
        storage.save(tasks);
        return message;
    }
}
