package ligma;

import ligma.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private int noOfTasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.noOfTasks = tasks.size();
    }

    public void addTask(Task t) {
        tasks.add(t);
        noOfTasks++;
    }
    public Task deleteTask(int index) {
        noOfTasks--;
        return tasks.remove(index);
    }

    public Task markAsDone(int index) {
        return tasks.get(index).markAsDone();
    }

    public int getTaskAmt() {
        return noOfTasks;
    }

    public String[] getStringArr() {
        return tasks.stream()
                .map(Task::toString)
                .toArray(String[]::new);
    }

    public Task[] find(String target) {
        return tasks.stream().filter(task -> task.match(target)).toArray(Task[]::new);
    }
}