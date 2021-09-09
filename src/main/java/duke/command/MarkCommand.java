package duke.command;

import duke.DukeResponse;
import duke.Parser;
import duke.Storable;
import duke.TaskList;
import duke.Ui;
import duke.Ui.UserCommands;
import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingIndexException;

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
     * @throws MissingIndexException If user input is missing an index.
     * @throws DukeException If underlying methods or checks fail.
     */
    private String markTask(TaskList tasks, Ui ui) throws DukeException {

        // Preliminary check for any input following command.
        Parser.checkInputValidity(this.userInput, UserCommands.DONE,
                new MissingIndexException(UserCommands.DONE));

        // Parses integer in user input.
        int userNumInput = Parser.parseUserNumInput(this.userInput, UserCommands.DONE);

        // Decrement integer from user input to match indexing of tasks.
        int idx = userNumInput - 1;

        // Checks for invalid index.
        if (idx >= tasks.size() || idx < 0) {
            throw new InvalidIndexException(UserCommands.DONE);
        }

        tasks.get(idx).markAsDone();

        return ui.getMarkSuccessMessage(tasks.get(idx));

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
     * @return DukeResponse containing string describing the marked task or error message.
     */
    @Override
    public DukeResponse execute(TaskList tasks, Ui ui, Storable storage) {
        try {
            String output = this.markTask(tasks, ui);
            storage.saveTasksToData(tasks);
            return new DukeResponse(output, false);
        } catch (DukeException dukeException) {
            return new DukeResponse(dukeException.toString(), true);
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
