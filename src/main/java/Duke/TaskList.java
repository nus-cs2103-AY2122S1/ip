package Duke;

import Duke.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public String markAsDone(int n) {
        Task task = this.tasks.get(n-1);
        task.markAsDone();
        return Ui.doneTask(task);
    }

    public String addTask(Task task) {
        tasks.add(task);
        return Ui.addTask(task, tasks.size());
    }

    public String deleteTask(int n) {
        Task task = tasks.get(n-1);
        tasks.remove(n-1);
        return Ui.deleteTask(task, tasks.size());
    }

    public String findTasks(String search) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDesription().contains(search)) {
                matches.add(task);
            }
        }

        return Ui.findTasks(matches);
    }

    public int getNumberOfTasks() {
        return this.tasks.size();
    }
}
