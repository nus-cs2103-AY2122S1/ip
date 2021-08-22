package duke.commands;

import duke.Storage;
import duke.TaskList;

abstract public class Command {
    private String desc;
    public Command (String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
    public abstract boolean isExit();
    public abstract void execute(TaskList tasks, Storage storage);
}
