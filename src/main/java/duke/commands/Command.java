package duke.commands;

import java.time.format.DateTimeFormatter;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

    abstract public boolean isExit();
    abstract public void execute(TaskList tasks, Ui ui, Storage storage);
}
