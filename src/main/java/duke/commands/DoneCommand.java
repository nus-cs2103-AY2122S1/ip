package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.tasks.Task;
import duke.Ui;
import duke.exceptions.DoneOutOfBoundsException;
import duke.exceptions.WrongDoneFormatException;

public class DoneCommand extends Command {
    public DoneCommand(String desc) {
        super(desc);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(
            TaskList tasks, Ui ui,
            Storage storage) throws WrongDoneFormatException,
            DoneOutOfBoundsException {
        String[] instructions = this.commandDescription.split(" ");
        if (instructions.length != 2) {
            throw new WrongDoneFormatException();
        }
        int taskNumber = Integer.valueOf(instructions[1]);
        Task completedTask = tasks.markTaskAsDone(taskNumber);
        storage.updateStorageList(tasks.getTaskList());
        ui.displayCompletedMessage(completedTask.toString());
    }
}
