package duke;

import java.util.ArrayList;

/**
 * The class for creating Duke' response when the user requests for list of tasks.
 */
public class GetTasksMessage extends DukeMessage{
    public void display() {
        ArrayList<Task> taskList = (ArrayList<Task>) TaskList.getTaskList().getTasks();
        int count = 1;
        for(Task task : taskList) {
            System.out.println(count + "." + task.getTaskString());
            count = count + 1;
        }
    }
}
