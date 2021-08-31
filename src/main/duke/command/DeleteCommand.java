package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    
    private int taskIndex;

    public DeleteCommand(String fullCommand) throws DukeException {
        try {
            String taskIndexString = fullCommand.replace("delete", "").trim();
            this.taskIndex = Integer.parseInt(taskIndexString);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Task Selected");
        }
    }

    /**
     * Execute user commands.
     * @param tasks List of tasks.
     * @param ui Ui of Duke chatbot.
     * @param storage Storage of Duke chatbot.
     * @throws DukeException If execution fails.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = (Task) tasks.remove(taskIndex - 1);
            ui.showMessage(String.format("Task deleted.\n %s", task));
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid Task Index");
        }
    }

    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    public boolean isExit() {
        return false;
    }
}
