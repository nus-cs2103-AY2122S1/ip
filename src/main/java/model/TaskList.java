package model;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public String addTask(Task task) {
        tasks.add(task);
        return "I Understand. I Have Added:\n   " + task.toString() + "\nYou Have " + tasks.size() + " Tasks.";
    }

    public String listTasks() {
        String taskListString = "Here Are The Tasks In Your List:\n";
        for(int i = 0; i < tasks.size(); i++) {
            taskListString += (i+1) + "." + tasks.get(i).toString() + "\n";
        }
        taskListString += "I Wish You Luck With Your Tasks And Doings.";
        return taskListString;
    }

    public String markAsDone(int index) {
        String doneString = "Good Work. I Mark This Task As Done:\n";
        Task doneTask = tasks.get(index - 1);
        doneTask.markAsDone();
        doneString += "   " + doneTask.toString();
        return doneString;
    }

    public String removeTask(int index, boolean logOutput) {
        try {
            Task deadTask = tasks.get(index - 1);
            tasks.remove(index - 1);
            return "   I Have Removed This Task:\n   " + deadTask.toString();
        } catch (IndexOutOfBoundsException e) {
            return "   You Cannot Delete A Task That Does Not Exist. Do Better.";
        }
    }

    public String saveTasks() {
        String outputString = "";
        for(int i = 0; i < tasks.size(); i++) {
            outputString += (i + 1) + " TTT " + tasks.get(i).toString() + "\n";
        }
        return outputString;
    }

    public String findTasks(String keyword) {
        String taskListString = "Let Me See If I Can Search What You Seek:\n";
        for(int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                taskListString += (i+1) + "." + tasks.get(i).toString() + "\n";
            }
        }
        taskListString += "What I Found, I Present Above.";
        return taskListString;
    }

}
