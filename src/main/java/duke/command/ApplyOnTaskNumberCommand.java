package duke.command;

public abstract class ApplyOnTaskNumberCommand extends Command {

    private int taskIndex;

    public ApplyOnTaskNumberCommand(String command) {
        super(command);
    }

    @Override
    void parseCommand(String[] tokens) {
        StringBuilder taskNumberSb = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            taskNumberSb.append(tokens[i]);
            if (taskNumberSb.length() > 0) {
                break;
            }
        }
        if (taskNumberSb.length() == 0) {
            throw new DukeInvalidCommandException(String.format("A task number is required for \"%s\" commands.", getCommandType().getCommandDescription()));
        }
        try {
            taskIndex = Integer.parseInt(taskNumberSb.toString()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeInvalidCommandException("The task number is not a number.");
        }
    }

    int getTaskIndex() {
        return taskIndex;
    }
}
