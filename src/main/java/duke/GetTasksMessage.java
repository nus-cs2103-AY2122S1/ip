package duke;

import java.util.ArrayList;

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
