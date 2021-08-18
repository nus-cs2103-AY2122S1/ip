package core;

import gui.Ui;
import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
        Ui.displayLine();
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println(String.format("Now you have %s tasks in the list.", taskList.size()));
        Ui.displayLine();
    }

    public void listTasks() {
        Ui.displayLine();
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : taskList) {
            System.out.println(String.format("%s. %s", index, task));
            index++;
        }
        Ui.displayLine();
    }

    public void markAsDone(int index) {
        Task taskToMark = taskList.get(index - 1);
        taskToMark.setCompleted();
        Ui.displayLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskToMark);
        Ui.displayLine();
    }
}
