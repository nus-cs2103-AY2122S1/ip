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

    /**
     * Constructor of ExpenseCommand
     *
     * @param taskIndex index of task to run expense commands on.
     * @param purpose purpose of expenditure.
     * @param amount amount spent.
     */
    public ExpenseCommand(int taskIndex, String purpose, float amount) {
        this.taskIndex = taskIndex;
        this.purpose = purpose;
        this.amount = amount;
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
        if (!isIndexValid()) {
            return tasks.showAllExpenses();
        } else if (isPurposeEmpty()) {
            // list all expenses of a specific task
            return tasks.showExpense(taskIndex);
        }
        String message = tasks.addExpense(taskIndex, purpose, amount);
        storage.save(tasks);
        return message;
    }
}
