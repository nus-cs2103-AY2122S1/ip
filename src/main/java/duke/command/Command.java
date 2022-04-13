package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

public abstract class Command {
    abstract public boolean isExit();
    abstract public ArrayList<String> execute(TaskList taskList, Ui ui, Storage storage);
}
