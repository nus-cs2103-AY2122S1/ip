import java.util.ArrayList;

public class CompleteTask extends DukeMessage{
    private int taskIndex;

    public CompleteTask(int index) {
        this.taskIndex = index;
    }

    public void display() {
        System.out.println("Nice! I've marked this task as done:");
        ArrayList<Task> taskList = (ArrayList<Task>) TaskList.getTaskList().getTasks();
        Task completedTask = taskList.get(taskIndex);
        completedTask.markAsDone();
        System.out.println(completedTask.getTaskString());
    }
}
