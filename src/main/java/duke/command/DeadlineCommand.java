package duke.command;

import duke.task.Deadlines;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    public static final String COMMAND = "deadline";
    private String desc;
    private LocalDateTime by;

    public DeadlineCommand(String desc, LocalDateTime by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Deadlines newDeadline = new Deadlines(this.desc, this.by);
        taskList.add(newDeadline);
        storage.writeToFile(taskList);
        ui.printAdd(newDeadline, taskList.getList().size());
    }
}
