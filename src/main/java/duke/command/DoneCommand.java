package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    private int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum >= tasks.numTasks()) {
            throw new DukeException("you typed an invalid number: " + (taskNum + 1));
        }
        tasks.markTask(taskNum);
        storage.save(tasks);
        ui.printMsg("Nice! I've marked this task as done:\n  " + tasks.getTask(taskNum));
    }
}
