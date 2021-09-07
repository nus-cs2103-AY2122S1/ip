package duke.command;

/**
 * Represents a generic "Apply On Task Number" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public abstract class ApplyOnTaskNumberCommand extends Command {

    private int taskIndex;

    /**
     * Creates an ApplyOnTaskNumberCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public ApplyOnTaskNumberCommand(String command) {
        super(command);
    }

    @Override
    void parseCommand(String[] tokens) {
        String taskNumberString = parseTaskNumberString(tokens);
        checkTaskNumberStringLength(taskNumberString);
        parseTaskIndex(taskNumberString);
    }

    private void checkTaskNumberStringLength(String taskNumberString) {
        if (taskNumberString.length() == 0) {
            throw new DukeInvalidCommandException(String.format("A task number is required for \"%s\" commands.",
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

    private void parseTaskIndex(String taskNumberString) throws DukeInvalidCommandException {
        try {
            taskIndex = Integer.parseInt(taskNumberString) - 1;
        } catch (NumberFormatException e) {
            throw new DukeInvalidCommandException("The task number is not a number.");
        }
    }
}
