package duke.commands;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import duke.Duke;
import duke.task.InvalidTaskException;
import duke.task.TaskList;
import duke.ui.UserInput;

class DoneTaskCommand extends Command {
    private static final Set<String> KEYWORDS = new HashSet<>(List.of("done", "do"));
    private static final String DONE_TASK_SUCCESS_MESSAGE = "Good job. I've marked this task as done:\n\t%s";

    @Override
    public void run(Duke duke, UserInput input) throws InvalidTaskException {
        assert input != null;
        int taskIndex = parseTaskNumber(input);
        TaskList taskList = duke.getTaskList();
        taskList.markAsDone(taskIndex);
        duke.say(String.format(DONE_TASK_SUCCESS_MESSAGE, taskList.get(taskIndex)));
    }

    @Override
    protected Set<String> getKeywords() {
        return KEYWORDS;
    }
}
