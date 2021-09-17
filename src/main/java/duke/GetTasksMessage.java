package duke;

import java.util.ArrayList;

/**
 * Represents Duke' response when the user requests for list of tasks.
 */
public class GetTasksMessage extends DukeMessage{

    public String createMessageString() {
        String reply = "";
        ArrayList<Task> taskList = (ArrayList<Task>) TaskList.getTaskList().getTasks();
        int count = 1;
        for(Task task : taskList) {
            reply = reply + count + "." + task.getTaskString() + "\n";
            System.out.println(count + "." + task.getTaskString());
            count = count + 1;
        }
        return reply;
    }
}
