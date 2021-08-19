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
        String outputLine1 = String.format("Got it. I've added this task:\n%s\n", task);
        String outputLine2 = String.format("Now you have %s tasks in the list.", taskList.size());
        String output = outputLine1 + outputLine2;
        Ui.formatAndPrint(output);
    }

    public void listTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        int index = 1;
        for (Task task : taskList) {
            sb.append(String.format("%s. %s\n", index, task));
            index++;
        }
        // Removed the last \n for nicer output
        Ui.formatAndPrint(sb.substring(0, sb.length() - 1));
    }

    public void markAsDone(int index) {
        Task taskToMark = taskList.get(index - 1);
        taskToMark.setCompleted();
        String outputLine1 = "Nice! I've marked this task as done:\n";
        String output = outputLine1 + taskToMark;
        Ui.formatAndPrint(output);
    }
}
