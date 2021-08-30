import java.io.IOException;

public class DoneCommand extends Command {
    private int taskNum;

    public DoneCommand(String type, int taskNum) {
        super(type);
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            markDone(taskList, ui);
            storage.saveTasksToFile(taskList);
        } catch (DukeException | IOException e) {
            ui.displayError(e.getMessage());
        }
    }

    public void markDone(TaskList tasks, Ui ui) throws DukeException {
        if (taskNum >= 1 && taskNum <= tasks.size()) {
            Task taskDone = tasks.get(taskNum - 1);
            taskDone.setDone();

            String response = ui.taskDoneMessage(taskDone);
            ui.displayResponse(response);
        } else {
            throw new DukeException("Task not found. Please try again!");
        }
    }
}
