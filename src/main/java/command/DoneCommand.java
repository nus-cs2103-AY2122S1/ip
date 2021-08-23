package command;
import task.*;
import duke.*;

public class DoneCommand extends Command {

    private int doneIndex;

    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.done(doneIndex);

        Task task = tasks.get(doneIndex);
        String response = respond(task);
        ui.showResponse(response);
        // storage
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public String respond(Task task) {
        String response = "Nice! I've marked this task as done:\n"
                + task.toString();
        return response;
    }
}
