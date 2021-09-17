package duke.message;

import duke.task.TaskList;
import java.util.ArrayList;
import duke.task.Task;

/**
 * Represents Duke' response when the user requests for list of tasks.
 */
public class GetAllTasksMessage extends DukeMessage{

    @Override
    public String createMessageString() {
        String reply = "";
        ArrayList<Task> taskList = (ArrayList<Task>) TaskList.getTaskList().getTasks();
        if(taskList.isEmpty()) {
            return "List khaali h bhai (No tasks)";
        }

        int count = 1;
        for(Task task : taskList) {
            reply = reply + count + "." + task.getTaskString() + "\n";
            System.out.println(count + "." + task.getTaskString());
            task.setRefId(count);
            count = count + 1;
        }
        return reply;
    }
}
