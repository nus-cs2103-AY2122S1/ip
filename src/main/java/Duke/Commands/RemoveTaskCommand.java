package Duke.Commands;

import Duke.Duke;
import Duke.Task.InvalidTaskException;
import Duke.Task.Task;
import Duke.Task.TaskList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class RemoveTaskCommand extends Command {
    private static final Set<String> KEYWORDS = new HashSet<>(List.of("remove", "rm", "delete", "del"));
    private static final String REMOVE_TASK_SUCCESS_MESSAGE = "Ok, I've removed this task:\n\t%s\n"
            + TASKS_COUNT_MESSAGE;

    @Override
    public void run(Duke duke, Duke.UserInput input) throws InvalidTaskException {
       int taskIndex = parseTaskNumber(input);
       TaskList taskList = duke.getTaskList();
       Task task = taskList.remove(taskIndex);
       duke.say(String.format(REMOVE_TASK_SUCCESS_MESSAGE, task, taskList.size()));
    }

    @Override
    protected Set<String> getKeywords() {
        return KEYWORDS;
    }
}
