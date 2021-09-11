package tokio.commands;

import java.io.IOException;

import tokio.storage.Storage;
import tokio.tasks.Deadlines;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

public class AddDeadlineCommand extends Command {
    protected String description;
    
    public AddDeadlineCommand(String description) {
        this.description = description.trim();
    }
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] descDateArray = description.split("/by ");
        if (descDateArray.length < 2) {
            return ui.printSomethingMissing("deadline");
        }
        String deadlineDesc = descDateArray[0].trim();
        String deadlineDate = descDateArray[1].trim();
        Deadlines addDeadline = new Deadlines(deadlineDesc, deadlineDate);
        if (!tasks.addTask(addDeadline)) {
            return ui.printDuplicateTask();
        }
        storage.writeTask(addDeadline);
        return ui.printAddCommand(addDeadline, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
