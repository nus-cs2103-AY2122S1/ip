package myjournal;

import myjournal.task.Deadline;
import myjournal.task.Event;
import myjournal.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public int getSize() {
        return tasks.size();
    }

    @Override
    public String toString() {
        String newFile = "";
        for (int i = 0; i < this.getSize(); i++) {
            newFile = newFile + tasks.get(i).getSymbol() + "|" + (tasks.get(i).getState() ? "1" : "0") + "| "
                    + tasks.get(i).getTaskName()
                    + ((tasks.get(i) instanceof Deadline || tasks.get(i) instanceof Event)
                    ? "|" + tasks.get(i).getTime(): "")  + "\n";
        }
        return newFile;
    }
}
