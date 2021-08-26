package duke.command;

import duke.task.Deadlines;
import duke.List;
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
    public void execute(List list, Ui ui, Storage storage) throws IOException {
        Deadlines newDeadline = new Deadlines(this.desc, this.by);
        list.add(newDeadline);
        storage.writeToFile(list);
        ui.printAdd(newDeadline, list.getList().size());
    }
}
