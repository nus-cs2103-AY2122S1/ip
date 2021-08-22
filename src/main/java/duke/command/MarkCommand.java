package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storable;
import duke.TaskList;
import duke.Ui;
import duke.Ui.Commands;

public class MarkCommand extends Command {
    private String userInput;

    public MarkCommand(String userInput) {
        this.userInput = userInput;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MarkCommand) {
            MarkCommand other = (MarkCommand) obj;
            return this.userInput.equals(other.userInput);
        }
        return false;
    }
}
