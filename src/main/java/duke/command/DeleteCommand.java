package duke.command;

import duke.exception.DukeException;
import duke.Parser;
import duke.Storable;
import duke.TaskList;
import duke.Ui;
import duke.Ui.UserCommands;
import duke.task.Task;

/**
 * Represents a command that can be executed to delete a task based on user input,
 * print the task deleted and new total count of tasks, then save tasks to storage.
 */
public class DeleteCommand extends Command {
    private final String userInput;

    /**
     * Constructor for DeleteCommand.
     * Creates DeleteCommand containing user input.
     *
     * @param userInput User's input into Duke chatbot.
     */
    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Deletes a task based on user input,
     * then returns String describing the task deleted and new total count of tasks.
     * Task is deleted at index provided by user input in format "delete N", where N can be any valid index.
     * Index provided should be 1-based.
     *
     * @param tasks TaskList to delete task from.
     * @param ui Ui to get enums, response messages and exception messages from.
     * @return String describing the task deleted and new total count of tasks.
     * @throws DukeException If user input is missing an index.
     * @throws DukeException If user input for index is not an integer.
     * @throws DukeException If user input for index is invalid.
     */
    private String deleteTask(TaskList tasks, Ui ui) throws DukeException {

        // Preliminary check for any input following command.
        Parser.checkInputValidity(this.userInput, UserCommands.DELETE.getCommand(),
                Ui.exceptionMissingIndexForDelete());

        // Parses integer in user input. 1 space is accounted for as it separates command and index.
        int userNumInput = Parser.parseUserNumInput(this.userInput, UserCommands.DELETE);

        // Decrement integer from user input to match indexing of tasks.
        int idx = userNumInput - 1;

        // Checks for invalid index.
        if (idx >= tasks.size() || idx < 0) {
            throw new DukeException(Ui.exceptionInvalidIndexForDelete());
        }

        Task deletedTask = tasks.remove(idx);

        return ui.getDeleteSuccessMessage(deletedTask, tasks.size());
    }

    /**
     * Deletes a task based on user input, saves tasks to storage,
     * then returns String describing the task deleted and new total count of tasks.
     * Task is deleted at index provided by user input in format "delete N", where N can be any valid index.
     * Index provided should be 1-based.
     *
     * @param tasks TaskList that command executes upon.
     * @param ui Ui contains enums, response messages and exception messages that command execution will use.
     * @param storage Storage that command executes upon.
     * @return String describing the task deleted and new total count of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storable storage) {
        try {
            String output = this.deleteTask(tasks, ui);
            storage.saveTasksToData(tasks);
            return output;
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }

    /**
     * Indicates whether another object is equals to this DeleteCommand.
     *
     * @param obj Other object to be compared to.
     * @return A boolean indicating whether the other object is equal to this DeleteCommand.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeleteCommand) {
            DeleteCommand other = (DeleteCommand) obj;
            return this.userInput.equals(other.userInput);
        }
        return false;
    }
}
