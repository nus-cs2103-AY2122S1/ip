package duke.command;

import duke.exception.InvalidInputException;
import duke.exception.SaveFileException;
import duke.exception.TaskCompletedException;
import duke.exception.TaskNotFoundException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DoneCommand extends Command {
    private String action;
    public DoneCommand(String action) {
        super(false);
        this.action = action;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskNotFoundException,
            InvalidInputException, TaskCompletedException, SaveFileException {
        try {
            int taskNumber = Integer.parseInt(action);
            if (taskNumber <= tasks.size() && taskNumber > 0) {
                Task taskToComplete = tasks.get(taskNumber - 1);
                if (!taskToComplete.isComplete()) {
                    taskToComplete.complete();
                    ui.showTaskDone(taskToComplete);
                } else {
                    throw new TaskCompletedException("This task is already completed.");
                }
            } else {
                throw new TaskNotFoundException("The task chosen does not exist. Use 'list' to see all your tasks.");
            }
            storage.save(tasks);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Command 'done' require an integer as the second parameter");
        }
    }
}
