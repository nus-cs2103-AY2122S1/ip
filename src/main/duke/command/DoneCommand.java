package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.Task;

public class DoneCommand extends Command {

    private int taskIndex;

    public DoneCommand(String fullCommand) {
        String taskIndexString = fullCommand.replace("done", "");
        this.taskIndex = Integer.parseInt(taskIndexString.trim());
    }

    /**
     * Execute user command.
     * @param tasks List of tasks.
     * @param ui UI of Duke Chatbot.
     * @param storage Storage of Duke Chatbot.
     * @throws DukeException If execution fails.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = (Task) tasks.get(taskIndex - 1);
        t.finishTask();
        System.out.println(String.format("Congratulations on finishing this task!\n %s", t));
        storage.save(tasks);
    }

    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    public Boolean isExit() {
        return false;
    }

}
