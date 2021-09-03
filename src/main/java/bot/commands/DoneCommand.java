package bot.commands;

import bot.tasks.Task;
import bot.utility.TaskList;

public class DoneCommand extends Command {
    private final int index;
    public DoneCommand(String indexString) {
        this.index = Integer.parseInt(indexString);
    }
    @Override
    public String execute() {
        Task toBeDone = TaskList.showTasks().get(index);
        if (toBeDone.getStatusIcon().equals("X")) {
            return "\t This task has been marked as done";
        }
        toBeDone.markAsDone();
        return "\n\t Nice! I've marked this task as done:\n\t\t" + toBeDone;
    }
}
