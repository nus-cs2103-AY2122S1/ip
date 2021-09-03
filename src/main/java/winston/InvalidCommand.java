package winston;

/**
 *  Represents an invalid command from the parent abstract class Command.
 */
public class InvalidCommand extends Command {
    public InvalidCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * Prints a message to let user's know that the command given was invalid
     */
    public String run() {
        String result =  Ui.invalidTask();
        assert(!result.equals(""));
        return result;
    }
}
