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
    
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
<<<<<<< Updated upstream
        Task t = tasks.get(taskIndex - 1);
        t.finishTask();
        System.out.println(String.format("Congratulations on finishing this task!\n %s", t));
        storage.save(tasks);
    }

    public Boolean isExit() {
=======
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
>>>>>>> Stashed changes
        return false;
    }

}
