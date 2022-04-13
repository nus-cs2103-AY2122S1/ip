package duke.commands;

import java.time.LocalDateTime;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.tasks.Task;

public class UpdateCommand extends Command {
    private final int index;
    private final String desc;
    private final LocalDateTime time;
    private final boolean isTimeBoundTask;

    /** Constructor to make an update command to update a specified task's description and time **/
    public UpdateCommand(int index, String desc, LocalDateTime time) {
        this.index = index;
        this.desc = desc;
        this.time = time;
        this.isTimeBoundTask = true;
    }

    /** Constructor to make an update command to update a specified task's description **/
    public UpdateCommand(int index, String desc) {
        this.index = index;
        this.desc = desc;
        this.time = null;
        this.isTimeBoundTask = false;
    }

    @Override
    public String execute(Ui ui, Storage storage) {
        Task updatedTask = storage.getTask(index - 1);
        if (isTimeBoundTask) {
            updatedTask.update(desc, time);
        } else {
            updatedTask.update(desc);
        }
        storage.save();
        String message = String.format("Got it! I have updated the task to the following:\n %s",
                updatedTask.toString());
        return ui.reply(message);
    }
}
