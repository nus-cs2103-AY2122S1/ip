package command;
import task.*;
import duke.*;

public class DeleteCommand extends Command {

    private int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(deleteIndex);
        tasks.delete(deleteIndex);
        int numOfTasks = tasks.size();

        String response = respond(task, numOfTasks);
        ui.showResponse(response);
        // storage
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public String respond(Task task, int numOfTasks) {
        String response = "Noted. I've removed this task:\n"
                + task.toString() + "\n"
                + "Now you have " + numOfTasks
                + " tasks in the list.";
        return response;
    }
}
