package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand implements  Command {
    private String number;

    public DoneCommand(String number) {
        this.number = number;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int i = Integer.parseInt(number.trim()) - 1;
            String t = taskList.markDone(i);
            ui.echo("Nice! I marked this duke.task as completed: " + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Oops! Enter a valid duke.task no. to complete the duke.task.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
