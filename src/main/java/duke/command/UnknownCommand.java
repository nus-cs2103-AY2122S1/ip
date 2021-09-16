package duke.command;

import duke.io.ResponseManager;
import duke.Storage;
import duke.task.TaskManager;

/**
 * This class is used when an input is not understood.
 */
public class UnknownCommand implements ICommand {

    private String reply;

    public UnknownCommand() {}

    @Override
    public void execute(TaskManager tm, ResponseManager responseManager, Storage storage) {
        this.reply = responseManager.getUnknownCommandMessage();
    }

    @Override
    public String getReply() {
        return reply;
    }
}
