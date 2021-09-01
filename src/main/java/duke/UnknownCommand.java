package duke;

/**
 * This class is used when an input is not understood.
 */
public class UnknownCommand implements ICommand {

    String reply;

    public UnknownCommand() {}

    @Override
    public void execute(TaskManager tm, Ui ui, Storage storage) {
        this.reply = ui.getUnknownCommandMessage();
    }

    @Override
    public String getReply() {
        return reply;
    }
}
