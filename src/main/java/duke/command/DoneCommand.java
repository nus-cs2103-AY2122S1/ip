package duke.command;

import duke.*;
import duke.task.Task;

/**
 * A Command class representing the 'Delete' command.
 */
public class DoneCommand implements Command {

    private int taskIndex;

    /**
     * Create a new Command indicating a task is done.
     * @param fullCommand Unedited user command.
     * @throws DukeException If the input string does not represent an integer.
     */
    public DoneCommand(String fullCommand) throws DukeException {
        String taskIndexString = fullCommand.replace("done", "").trim();
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
     * @return String of Duke chatbot response.
     */
    public ResponsePair execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = (Task) tasks.get(taskIndex - 1);
            task.finishTask();
            storage.save(tasks);
            return new ResponsePair(String.format("Congratulations on finishing this task!\n %s", task), isExit());
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
