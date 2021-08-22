package duke.commands;

import duke.Storage;
import duke.TaskList;

public class UnknownCommand extends Command {
    public UnknownCommand(String desc) {
        super(desc);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("*** Apologies, Master Wayne. But I don't know what that means ***");
    }
}
