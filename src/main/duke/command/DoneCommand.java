package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.Task;

public class DoneCommand extends Command {

    private int taskIndex;

    public DoneCommand(String fullCommand) {
        String taskIndexString = fullCommand.replace("done", "").trim();
        this.taskIndex = Integer.parseInt(taskIndexString);
    }

    /**
     * Execute user command.
     * @param tasks List of tasks.
     * @param ui UI of Duke Chatbot.
     * @param storage Storage of Duke Chatbot.
     * @throws DukeException If execution fails.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = (Task) tasks.get(taskIndex - 1);
        task.finishTask();
        ui.showMessage(String.format("Congratulations on finishing this task!\n %s", task));
        storage.save(tasks);
    }

    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    public boolean isExit() {
        return false;
    }

}
