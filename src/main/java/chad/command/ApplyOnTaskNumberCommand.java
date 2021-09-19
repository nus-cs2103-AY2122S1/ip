package chad.command;

/**
 * Represents a generic "Apply On Task Number" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public abstract class ApplyOnTaskNumberCommand extends Command implements UndoableCommand {

    private static final String MISSING_TASK_NUMBER_ERROR_TEMPLATE = "A task number is required for \"%s\" commands.";
    private static final String INVALID_TASK_NUMBER_ERROR_MESSAGE = "The task number is not a number.";

    private int taskIndex;

    /**
     * Creates an ApplyOnTaskNumberCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public ApplyOnTaskNumberCommand(String command) throws ChadInvalidCommandException {
        super(command);
    }

    @Override
    void parseCommand(String[] tokens) throws ChadInvalidCommandException {
        String taskNumberString = parseTaskNumberString(tokens);
        checkTaskNumberStringLength(taskNumberString);
        parseTaskIndex(taskNumberString);
    }

    private void checkTaskNumberStringLength(String taskNumberString) throws ChadInvalidCommandException {
        if (taskNumberString.length() == 0) {
            throw new ChadInvalidCommandException(String.format(MISSING_TASK_NUMBER_ERROR_TEMPLATE,
                    getCommandType().getCommandDescription()));
        }
    }

    int getTaskIndex() {
        return taskIndex;
    }

    private String parseTaskNumberString(String[] tokens) {
        StringBuilder taskNumberSb = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            taskNumberSb.append(tokens[i]);
            if (taskNumberSb.length() > 0) {
                break;
            }
        }
        return taskNumberSb.toString().strip();
    }

    private void parseTaskIndex(String taskNumberString) throws ChadInvalidCommandException {
        try {
            taskIndex = Integer.parseInt(taskNumberString) - 1;
        } catch (NumberFormatException e) {
            throw new ChadInvalidCommandException(INVALID_TASK_NUMBER_ERROR_MESSAGE);
        }
    }
}
