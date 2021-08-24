package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand implements  Command {
    private String text;

    public DeleteCommand(String arr) {
        this.text = arr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int i = Integer.parseInt(text.trim()) - 1;
            String t = taskList.removeTask(i);
            ui.echo("Noted. I removed this duke.task: " + t);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Oops! Enter a valid duke.task no. to delete the duke.task.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
