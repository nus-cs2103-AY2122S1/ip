package winston;

/**
 * Represents an invalid command from the parent abstract class Command.
 */
public class InvalidCommand extends Command {
    private String errorMessage;
    
    /**
     * Constructor for InvalidCommand
     * 
     * @param taskList the taskList with the list of tasks desired
     */
    public InvalidCommand(TaskList taskList) {
        super(taskList);
        this.errorMessage = "Invalid Command";
    }

    /**
     * Constructor for InvalidCommand
     *
     * @param taskList the taskList with the list of tasks desired
     */
    public InvalidCommand(TaskList taskList, String errorMessage) {
        super(taskList);
        this.errorMessage = errorMessage;
    }
    
    /**
     * Prints a message to let user's know that the command given was invalid.
     */
    public String run() {
        String result =  Ui.invalidTask(errorMessage);
        assert(!result.equals(""));
        return result;
    }
}
