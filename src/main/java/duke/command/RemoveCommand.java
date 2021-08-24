package duke.command;

import duke.task.TaskList;
import duke.task.Task;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";
    private int taskNum;

    public RemoveCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum >= tasks.numTasks()) {
            throw new DukeException("you typed an invalid number: " + (taskNum + 1));
        }
        String msg;

        Task removed = tasks.remove(taskNum);
        storage.save(tasks);
        msg = "Noted. I've removed this task:\n  " + removed + "\nNow you have " + tasks.numTasks();
        msg = tasks.numTasks() == 1 ? msg + " task in the list" : msg + " tasks in the list.";
        ui.printMsg(msg);
    }
}
