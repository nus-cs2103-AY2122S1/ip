package duke.commands;

import duke.Tasklist;
import duke.UI;
import duke.PersistentStorage;
import duke.tasks.Deadline;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    private String description;
    private LocalDateTime dueDateTime;

    public DeadlineCommand(String description, LocalDateTime dueDateTime) {
        this.description = description;
        this.dueDateTime = dueDateTime;
    }
    
    public void executeCommand(Tasklist taskList, UI ui, PersistentStorage storage) {
        Deadline deadline = new Deadline(this.description, this.dueDateTime);
        taskList.addTask(deadline);
        ui.showAddedTask(taskList, deadline);
    }
}
