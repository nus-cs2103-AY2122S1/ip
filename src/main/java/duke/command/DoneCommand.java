package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private String cmd;

    public DoneCommand(String input) {
        this.cmd = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int id = Integer.parseInt(cmd.strip());
            Task task = tasks.get(id);
            task.markAsDone();
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}