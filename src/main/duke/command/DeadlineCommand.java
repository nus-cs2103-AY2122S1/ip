package duke.command;

import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final Deadline newTask;

    public DeadlineCommand(String description, LocalDateTime by) {
        this(new Deadline(description, by));
    }

    public DeadlineCommand(Deadline newTask){
        this.newTask = newTask;
    }

    /**
     * Execute the command to add a new Deadline to the given TaskList.
     *
     * @param tasks   The TaskList of the Duke instance.
     * @param ui      The UI handler of the Duke instance.
     * @param storage The storage handler of the Duke instance.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(newTask);
        storage.update(tasks);
        ui.showFramedMsg("Got it. I've added this task:\n  "
                + newTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
