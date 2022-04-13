package duke.command;

/**
 * Confused command is a Command that encapsulates the attributes and behaviour of someone confused.
 *
 * @author leezhixuan
 */
public class ConfusedCommand extends Command {

    /**
     * Creates an instance of ConfusedCommand.
     */
    public ConfusedCommand() {
    }

    @Override
    public String execute() {
        return "I'm confused... Put in a little more effort this time?";
    }
}
