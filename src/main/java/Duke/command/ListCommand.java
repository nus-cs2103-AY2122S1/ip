package Duke.command;

import java.util.ArrayList;
import Duke.TaskList;
import Duke.task.Task;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        String reply = "Here are the tasks in your list:\n";
        ArrayList<Task> taskList = tasks.getTasks();
        int count = 0;
        for (Task task : taskList) {
            count++;
            reply += "  " + count + "." + task.toString() + "\n";
        }
        return reply;
    }
}
