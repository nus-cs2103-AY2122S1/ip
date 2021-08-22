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
    private String userInput;

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
     * Marks task at index specified by user input as done, then prints the marked task.
     * Accepts user input of the form "done N", where N can be any valid index.
     * Index provided should be 1-based.
     *
     * @param tasks TaskList to mark a task in.
     * @param ui Ui to get enums, response messages and exception messages from.
     * @throws DukeException If user input is missing an index.
     * @throws DukeException If user input for index is not an integer.
     * @throws DukeException If user input for index is invalid.
     */
    private void markTask(TaskList tasks, Ui ui) throws DukeException {

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

        // Prints response to user after successfully marking task at index as done.
        ui.showMarkSuccess(tasks.get(idx));

    }

    /**
     * Marks a task at index specified by user input as done, prints the marked task, then saves tasks to storage.
     * Accepts user input of the form "done N", where N can be any valid index.
     * Index provided should be 1-based.
     *
     * @param tasks TaskList that command executes upon.
     * @param ui Ui contains enums, response messages and exception messages that command execution will use.
     * @param storage Storage that command executes upon. Should contain the TaskList used in tasks parameter.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storable storage) {
        try {
            // Marks duke.task.Task at user specified index in duke.TaskList.
            this.markTask(tasks, ui);

            // Saves edited duke.TaskList to save file.
            storage.saveTasksToData(tasks);
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
        }
    }

    /**
     * Indicates whether another object is equals to this MarkCommand.
     *
     * @param obj Other object to be compared to.
     * @return boolean indicating whether the other object is equal to this MarkCommand.
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
