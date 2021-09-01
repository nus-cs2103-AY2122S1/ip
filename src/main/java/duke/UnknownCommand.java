package duke;

/**
 * This class is used when an input is not understood.
 */
public class UnknownCommand implements ICommand {

    String reply;

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
