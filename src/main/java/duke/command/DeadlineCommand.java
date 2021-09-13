package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.TaskList;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public static final String MISSING_DESCRIPTION_MESSAGE =
            "The description of the deadline task cannot be empty";

    private String description;
    private String date;
    private String time;

    public DeadlineCommand(String description, String date, String time) {
        this.description = description;
        this.date = date;
        this.time = time;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Deadline newDeadline = new Deadline(description, date, time);
        tasks.addTask(newDeadline);
        storage.save(tasks);
        return Ui.getAddedMessage(newDeadline, tasks.size());
    }
}
