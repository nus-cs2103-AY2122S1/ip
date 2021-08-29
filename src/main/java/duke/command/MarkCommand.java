package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storable;
import duke.TaskList;
import duke.Ui;
import duke.Ui.Commands;

/**
 * Represents a command that can be executed to mark a task as done, print the marked task,
 * then save tasks to storage.
 */
public class MarkCommand extends Command {
    private final String userInput;

    /**
     * Constructor for MarkCommand.
     * Creates a MarkCommand containing user input.
     *
     * @param userInput User's input into Duke chatbot.
     */
    public MarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Marks task at index specified by user input as done,
     * then returns String describing the marked task.
     * Accepts user input of the form "done N", where N can be any valid index.
     * Index provided should be 1-based.
     *
     * @param tasks TaskList to mark a task in.
     * @param ui Ui to get enums, response messages and exception messages from.
     * @return String describing the marked task.
     * @throws DukeException If user input is missing an index.
     * @throws DukeException If user input for index is not an integer.
     * @throws DukeException If user input for index is invalid.
     */
    private String markTask(TaskList tasks, Ui ui) throws DukeException {

        // Preliminary check for any input following command.
        Parser.checkInputValidity(this.userInput, Commands.DONE.getCommand(),
                Ui.exceptionMissingIndexForMarking());

        // Parses integer in user input.
        int userNumInput = Parser.parseUserNumInput(this.userInput, Commands.DONE);

        // Decrement integer from user input to match indexing of tasks.
        int idx = userNumInput - 1;

        // Checks for invalid index.
        if (idx >= tasks.size() || idx < 0) {
            throw new DukeException(Ui.exceptionInvalidIndexForMarking());
        }

        // Marks task at index as done.
        tasks.get(idx).markAsDone();

        // Returns response to user after successfully marking task at index as done.
        return ui.getMarkSuccess(tasks.get(idx));

    }

    /**
     * Marks a task at index specified by user input as done, saves tasks to storage,
     * then returns String describing the marked task.
     * Accepts user input of the form "done N", where N can be any valid index.
     * Index provided should be 1-based.
     *
     * @param tasks TaskList that command executes upon.
     * @param ui Ui contains enums, response messages and exception messages that command execution will use.
     * @param storage Storage that command executes upon.
     * @return String describing the marked task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storable storage) {
        try {
            // Marks duke.task.Task at user specified index in duke.TaskList.
            String output = this.markTask(tasks, ui);

            // Saves edited duke.TaskList to save file.
            storage.saveTasksToData(tasks);

            return output;
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }

    /**
     * Indicates whether another object is equals to this MarkCommand.
     *
     * @param obj Other object to be compared to.
     * @return A boolean indicating whether the other object is equal to this MarkCommand.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MarkCommand) {
            MarkCommand other = (MarkCommand) obj;
            return this.userInput.equals(other.userInput);
        }
        return false;
    }
}
