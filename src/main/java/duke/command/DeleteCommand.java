package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {

    private int taskIndex;

    /**
     * Create a new Command indicating a task is deleted.
     * @param fullCommand Unedited user command.
     */
    public DeleteCommand(String fullCommand) throws DukeException {
        String taskIndexString = fullCommand.replace("delete", "").trim();
        try {
            this.taskIndex = Integer.parseInt(taskIndexString);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Task Selected");
        }
    }

    /**
     * Execute user command.
     * @param tasks List of tasks.
     * @param ui UI of Duke Chatbot.
     * @param storage Storage of Duke Chatbot.
     * @throws DukeException If execution fails.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = (Task) tasks.remove(taskIndex - 1);
            storage.save(tasks);
            return String.format("Task deleted.\n %s", task);
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
