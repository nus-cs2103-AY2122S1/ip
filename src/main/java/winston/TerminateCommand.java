package winston;

/**
 * Represents the bye command from the parent abstract class Command.
 */
public class TerminateCommand extends Command {
    
    public TerminateCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * A method that prints the terminating message found in class Ui
     * 
     * @return A string of the termination message
     */
    public String run() {
        String result = Ui.terminationMessage();
        assert(!result.equals(""));
        return result;
    }
}
