package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.tasks.Task;
import duke.Ui;
import duke.exceptions.DeleteOutOfBoundsException;
import duke.exceptions.WrongDeleteFormatException;

public class DeleteCommand extends Command {

    public DeleteCommand(String desc) {
        super(desc);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(
            TaskList tasks, Ui ui,
            Storage storage) throws WrongDeleteFormatException,
            DeleteOutOfBoundsException {
        String[] instructions = this.commandDescription.split(" ");
        if (instructions.length != 2) {
            throw new WrongDeleteFormatException();
        }
        int taskNumber = Integer.valueOf(instructions[1]);
        Task taskRemoved = tasks.deleteTask(taskNumber);
        storage.updateStorageList(tasks.getTaskList());
        ui.displayDeleteMessage(taskRemoved.toString(), tasks.getTaskListLength());
    }

    
}
