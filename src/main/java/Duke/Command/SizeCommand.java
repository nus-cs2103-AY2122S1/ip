package Duke.Command;

import Duke.Main.TaskList;

public class SizeCommand extends Command {

    private TaskList taskList;
    public SizeCommand(TaskList taskList) {
        super("size", taskList);
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
