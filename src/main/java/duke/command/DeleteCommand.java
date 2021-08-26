package duke.command;
import duke.TaskList;
import duke.DukeException;
import duke.Storage;
import duke.Ui;


public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private String cmd;

    public DeleteCommand(String input) {
        this.cmd = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int id = Integer.parseInt(cmd.strip());
            taskList.delete(id);
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}