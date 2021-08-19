package Duke.Commands;

import Duke.Duke;
import Duke.Task.InvalidTaskException;
import Duke.Task.Task;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class DoneTaskCommand extends Command {
    private static final Set<String> KEYWORDS = new HashSet<>(List.of("done"));
    private static final String DONE_TASK_SUCCESS_MESSAGE = "Good job! I've marked this task as done:\n\t%s";
    private static final String INVALID_NUMBER_FORMAT_MESSAGE = "\"%s\" is not a valid task number.";

    @Override
    public void run(Duke duke, Duke.UserInput input) throws InvalidTaskException {
        String args = input.getArgs();
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException(String.format(INVALID_NUMBER_FORMAT_MESSAGE, args));
        }

        Task task = duke.getTaskList().get(taskIndex);
        task.markAsDone();
        duke.say(String.format(DONE_TASK_SUCCESS_MESSAGE, task));
    }

    @Override
    protected Set<String> getKeywords() {
        return KEYWORDS;
    }
}
