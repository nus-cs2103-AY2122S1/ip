package duke.core;

import duke.gui.Ui;
import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
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

    public void findAndListTasks(String keyword) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        int index = 1;
        String regex = " ";
        for (Task task : listOfTasks) {
            String[] splittedTaskString = task.toString().split(regex);
            for (String s : splittedTaskString) {
                // Check whether final character of string is ')'. If so, remove the ')'.
                if (s.charAt(s.length() - 1) == ')') {
                    s = s.substring(0, s.length() - 1);
                }
                if (s.equals(keyword)) {
                    sb.append(String.format("%s. %s\n", index, task));
                    index++;
                    break;
                }
            }
        }
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

    public void saveContents(File file) {
        try {
            FileWriter fw = new FileWriter(file.getPath());
            for (Task task : listOfTasks) {
                fw.write(task.toStorageFormat() +"\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
