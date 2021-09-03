package winston;

/**
 *  Represents the bye command from the parent abstract class Command.
 */
public class TerminateCommand extends Command {
    private static boolean isTerminated = false;
    
    public TerminateCommand(TaskList taskList) {
        super(taskList);
        isTerminated = true;
    }

    /**
     * A method that prints the terminating message found in class Ui
     */
    public String run() {
        String result = Ui.terminationMessage();
        assert(!result.equals(""));
        return result;
    }

    /**
     * A method that determines if the program should be ended
     * @return a boolean on whether the program has ended
     */
    public static boolean isExit() {
        return isTerminated;
    }
}
