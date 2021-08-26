package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
    }

    public List<Task> getList() {
        return this.tasks;
    }

    public void add(Task t) {
        tasks.add(t);
        System.out.println("Ok~ I've added the task:\n" + t.toString());
    }

    public void delete(int index) {
        Task deleted = tasks.get(index);
        tasks.remove(index);
        System.out.println("Ok~ I've deleted the task:\n" + deleted.toString());
    }

    public void display() {
        System.out.println("Here are your tasks ~ OwO");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public Task get(int index) {
        return tasks.get(index);
    }
}