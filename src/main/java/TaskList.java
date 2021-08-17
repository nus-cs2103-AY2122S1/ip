import Tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public String addTask(Task task) {
        this.taskList.add(task);
        return "Okay okay I've added the task:\n" + task.printTask() + "\n"
                + "Yay " + this.taskList.size() + " tasks!\n";
    }

    public String getList() {
        String result = "Here's your tasks! Wow I'm so helpful!\n";
        for (int index = 0; index < this.taskList.size(); index++) {
            result = result + (index + 1) + "." + this.taskList.get(index).printTask() + "\n";
        }
        return result;
    }

    public String markIndexCompleted(int index) {
        Task selectedTask = this.taskList.get(index);
        selectedTask.markCompleted();
        return "Wow you finally did something productive!\n" + selectedTask.printTask() + "\n";
    }

    public String deleteIndex(int index) {
        Task selectedTask = this.taskList.get(index);
        this.taskList.remove(index);
        return "Okay task yeeted away :D\n" + selectedTask.printTask() + "\n"
                + "Yay " + this.taskList.size() + " tasks!\n";
    }

    public String noOfTasks() {
        return Integer.toString(this.taskList.size());
    }
}
