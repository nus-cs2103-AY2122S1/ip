package duke.command;

import duke.main.TaskList;

public class SizeCommand extends Command {

    private final TaskList taskList;
    public SizeCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String reply() {
        int size = taskList.size();
        if (size <= 1) {
            return "Now you have " + size + " task in your list";
        } else {
            return "Now you have " + size + " tasks in your list";
        }
    }
}
