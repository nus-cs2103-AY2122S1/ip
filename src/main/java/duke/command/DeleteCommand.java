package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

public class DeleteCommand extends Command {
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        try {
            Task task = taskList.deleteTask(taskNumber);
            storage.writeToDisk(taskList.compileTasks());
            String response = String.format("Ooh yeah! Task %d deleted:\n  %s" +
                            "\nNow you have %d tasks in the list.",
                    taskNumber,
                    task,
                    taskList.getSize());
            ui.respond(response);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException(String.format("Task number %d invalid.", taskNumber));
        }
    }
}
