package duke;

import java.util.ArrayList;

/**
 * The class for creating Duke' response after a task is marked as done.
 */
public class CompleteTaskMessage extends DukeMessage{
    private int taskIndex;

    public CompleteTaskMessage(int index) {
        this.taskIndex = index;
    }

    public void display() {
        System.out.println("Theek hai... ye task khatam!");
        ArrayList<Task> taskList = (ArrayList<Task>) TaskList.getTaskList().getTasks();
        Task completedTask = taskList.get(taskIndex);
        completedTask.markAsDone();
        System.out.println(completedTask.getTaskString());
    }
}
