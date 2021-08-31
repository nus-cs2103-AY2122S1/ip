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
            String taskIndexString = fullCommand.replace("delete", "");
            this.taskIndex = Integer.parseInt(taskIndexString.trim());
        } catch (NumberFormatException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Execute user command.
     * @param tasks List of tasks.
     * @param ui UI of Duke Chatbot.
     * @param storage Storage of Duke Chatbot.
     * @throws DukeException If execution fails.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = (Task) tasks.remove(taskIndex - 1);
            System.out.println(String.format("Task deleted.\n %s", t));
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    public Boolean isExit() {
        return false;
    }
}
