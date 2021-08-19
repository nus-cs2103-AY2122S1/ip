package Duke.Commands;

import Duke.Duke;
import Duke.Task.Task;

class AddTaskCommand extends Command {
    private static final String ADD_TASK_SUCCESS_MESSAGE = "added: %s";

    @Override
    public void run(Duke duke, Duke.UserInput input) {
        Task newTask = new Task(input.getRaw());
        duke.getTaskList().add(newTask);
        duke.say(String.format(ADD_TASK_SUCCESS_MESSAGE, newTask));
    }

    @Override
    protected String getKeyword() {
        return null;
    }
}
