package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.Arrays;

public class EventCommand extends Command {
    public EventCommand(String[] args) {
        super(args);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addEventTask(args[0], args[1]);
        storage.writeToFile(tasks);
        ui.showNewTask(tasks.lastTask(), tasks.size());
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof EventCommand) {
            EventCommand otherCommand = (EventCommand) other;
            return Arrays.equals(this.args, otherCommand.args);
        }
        return false;
    }
}
