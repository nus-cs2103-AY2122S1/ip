package core;

import gui.Ui;
import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
        String outputLine1 = String.format("Got it. I've added this task:\n%s\n", task);
        String outputLine2 = String.format("Now you have %s tasks in the list.", listOfTasks.size());
        String output = outputLine1 + outputLine2;
        Ui.formatAndPrint(output);
    }

    public void listTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        int index = 1;
        for (Task task : listOfTasks) {
            sb.append(String.format("%s. %s\n", index, task));
            index++;
        }
        // Removed the last \n for nicer output
        Ui.formatAndPrint(sb.substring(0, sb.length() - 1));
    }

    public void markAsDone(int index) {
        Task taskToMark = listOfTasks.get(index - 1);
        taskToMark.setCompleted();
        String outputLine1 = "Nice! I've marked this task as done:\n";
        String output = outputLine1 + taskToMark;
        Ui.formatAndPrint(output);
    }

    public void delete(int index) {
        Task taskToRemove = listOfTasks.get(index - 1);
        listOfTasks.remove(index - 1);
        String outputLine1 = "Noted. I've removed this task: \n";
        String outputLine2 = taskToRemove.toString() + "\n";
        String outputLine3 = String.format("Now you have %s tasks in the list.", listOfTasks.size());
        String output = outputLine1 + outputLine2 + outputLine3;
        Ui.formatAndPrint(output);
    }

    public int getSize() {
        return listOfTasks.size();
    }
}
