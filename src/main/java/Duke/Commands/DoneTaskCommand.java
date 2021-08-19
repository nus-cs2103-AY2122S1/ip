package Duke.Commands;

import Duke.Duke;
import Duke.Task.Task;

class DoneTaskCommand extends Command {
    private static final String DONE_TASK_SUCCESS_MESSAGE = "Good job! I've marked this task as done:\n\t%s";
    private static final String KEYWORD = "done";

    @Override
    public void run(Duke duke, Duke.UserInput input) {
        int taskIndex = Integer.parseInt(input.getArgs()) - 1;
        Task task = duke.getTaskList().get(taskIndex);
        task.markAsDone();
        duke.say(String.format(DONE_TASK_SUCCESS_MESSAGE, task));
    }

    @Override
    protected String getKeyword() {
        return KEYWORD;
    }
}
