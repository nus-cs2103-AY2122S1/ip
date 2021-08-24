package Duke.Commands;

import Duke.Duke;
import Duke.DukeException;
import Duke.Task.InvalidTaskException;
import Duke.Ui.UserInput;

import java.util.Set;

public abstract class Command {
    private static final String INVALID_TASK_NUMBER_FORMAT_MESSAGE = "\"%s\" is not a valid task number.";
    protected static final String TASKS_COUNT_MESSAGE = "Now you have %d tasks in the list.";

    abstract public void run(Duke duke, UserInput input) throws DukeException;

    abstract protected Set<String> getKeywords();

    protected static int parseTaskNumber(UserInput input) throws InvalidTaskException {
        String args = input.getArgs();
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException(String.format(INVALID_TASK_NUMBER_FORMAT_MESSAGE, args));
        }
    }
}
