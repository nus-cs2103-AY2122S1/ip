package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.UI;

public abstract class Command {
    private String arguments;

    public Command(String arguments) {
        this.arguments = arguments;
    }

    public abstract Command of(String arguments);

    public abstract void execute(TaskList tl, Storage s, UI ui);

    public abstract String startsWith();

    public boolean isExit() {
        return false;
    }

    public String getArguments() {
        return arguments;
    }
}
