package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storable;
import duke.TaskList;
import duke.Ui;
import duke.Ui.Commands;
import duke.task.Task;

/**
 * Represents a command that can be executed to delete a task based on user input,
 * print the task deleted and new total count of tasks, then save tasks to storage.
 */
public class DeleteCommand extends Command {
    private String userInput;

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
     * Deletes a task based on user input, then prints the task deleted and new total count of tasks.
     * Task is deleted at index provided by user input in format "delete N", where N can be any valid index.
     * Index provided should be 1-based.
     *
     * @param tasks TaskList to delete task from.
     * @param ui Ui to get enums, response messages and exception messages from.
     * @throws DukeException If user input is missing an index.
     * @throws DukeException If user input for index is not an integer.
     * @throws DukeException If user input for index is invalid.
     */
    private void deleteTask(TaskList tasks, Ui ui) throws DukeException {

        // Preliminary check for any input following command.
        Parser.checkInputValidity(this.userInput, Commands.DELETE.getCommand(),
                Ui.exceptionMissingIndexForDelete());

        // Parses integer in user input. 1 space is accounted for as it separates command and index.
        int userNumInput = Parser.parseUserNumInput(this.userInput, Commands.DELETE);

        // Decrement integer from user input to match indexing of tasks.
        int idx = userNumInput - 1;

        // Checks for invalid index.
        if (idx >= tasks.size() || idx < 0) {
            throw new DukeException(Ui.exceptionInvalidIndexForDelete());
        }

        // Deletes task at index and obtain the deleted task
        Task deletedTask = tasks.remove(idx);

        // Prints response to user after successfully deleting task at index.
        ui.showDeleteSuccess(deletedTask, tasks.size());
    }

    /**
     * Deletes a task based on user input, prints the task deleted and new total count of tasks,
     * then saves tasks to storage.
     * Task is deleted at index provided by user input in format "delete N", where N can be any valid index.
     * Index provided should be 1-based.
     *
     * @param tasks TaskList that command executes upon.
     * @param ui Ui contains enums, response messages and exception messages that command execution will use.
     * @param storage Storage that command executes upon. Should contain the TaskList used in tasks parameter.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storable storage) {
        try {
            // Deletes task at user specified index.
            this.deleteTask(tasks, ui);

            // Saves edited duke.TaskList to save file.
            storage.saveTasksToData(tasks);
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
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
