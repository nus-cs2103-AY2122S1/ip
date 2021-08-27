package duke.command;

import duke.data.TaskHandler;
import duke.storage.Storage;
import duke.data.exception.DukeException;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "DELETE";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Deletes selected task from list.";

    public DeleteCommand(TaskHandler th, Storage str) {
        super(th, str);
    }

    @Override
    public void execute(String cmd) {
        try {
            taskHandler.deleteTask(Integer.parseInt(cmd.substring(7)));
            taskHandler.printNoOfTasks();
            storage.updateFile(taskHandler.formatTaskToSave());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
