package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    
    private int taskIndex;

<<<<<<< Updated upstream
    public DeleteCommand(String fullCommand) {
        String taskIndexString = fullCommand.replace("delete", "");
        this.taskIndex = Integer.parseInt(taskIndexString.trim());
=======
    public DeleteCommand(String fullCommand) throws DukeException {
        try {
            String taskIndexString = fullCommand.replace("delete", "").trim();
            this.taskIndex = Integer.parseInt(taskIndexString);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Task Selected");
        }
>>>>>>> Stashed changes
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
<<<<<<< Updated upstream
        Task t = tasks.remove(taskIndex - 1);
        System.out.println(String.format("Task deleted.\n %s", t));
        storage.save(tasks);
    }

    public Boolean isExit() {
=======
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
>>>>>>> Stashed changes
        return false;
    }
}
