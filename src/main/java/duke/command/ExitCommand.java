package duke.command;

import duke.constant.MessageType;
import duke.listener.Message;

/**
 * This is the ExitCommand class that show exit message.
 */
public class ExitCommand extends Command {
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Constructs a ExitCommand object.
     *
     * @param message Message interface.
     */
    public ExitCommand(Message message) {
        super(message, true);
    }

    /**
     * Shows exit message.
     */
    @Override
    public void execute() {
        getMessage().show(MessageType.NORMAL, EXIT_MESSAGE);
    }
}
