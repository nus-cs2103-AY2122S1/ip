package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

import java.util.ArrayList;

public abstract class Command {
    abstract public boolean isExit();
    abstract public ArrayList<String> execute(TaskList taskList, Ui ui, Storage storage);
}
