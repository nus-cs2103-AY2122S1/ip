package core;

import gui.Ui;
import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private Ui ui;

    public TaskList() {
        taskList = new ArrayList<>();
        ui = new Ui();
    }

    public void addTask(Task task) {
        taskList.add(task);
        ui.displayLine();
        System.out.println("added: " + task);
        ui.displayLine();
    }

    public void listTasks() {
        ui.displayLine();
        int index = 1;
        for (Task task : taskList) {
            System.out.println(String.format("%s. %s", index, task));
            index++;
        }
        ui.displayLine();
    }

    public void markAsDone(int index) {
        Task taskToMark = taskList.get(index - 1);
        taskToMark.setCompleted();
        ui.displayLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskToMark);
        ui.displayLine();
    }
}
