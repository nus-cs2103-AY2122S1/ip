package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Locale;

public class Ui {
    public void display(String msg) {
        System.out.println(msg);
    }

    public void display(Exception e) {
        display(e.getMessage());
    }

    public void addTask(Task task, TaskList taskList) {
        display("is added.");
        display(task.toString());
        display("now is have " + taskList.size() + " task" +
                (taskList.size() == 1 ? "" : "s") + ".");
    }

    public void completeTask(Task task) {
        display("is done!");
        display(task.toString());
    }

    public void deleteTask(Task task, TaskList taskList) {
        display("is deleted!");
        display(task.toString());
        display("now is have " + taskList.size() + " task" +
                (taskList.size() == 1 ? "" : "s") + ".");
    }

    /**
     * Lists out all Tasks numbered and on individual lines.
     * Calls the toString() method of each Task to display them
     * and their type/status.
     */
    public void listTasks(TaskList taskList) {
        if (taskList.size() == 0) {
            display("is no tasks today.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                String taskDescription = taskList.getTask(i).toString();
                display((i + 1) + "." + taskDescription + ".");
            }
        }
    }

    /**
     * Finds and displays tasks from the given TaskList containing the given string.
     *
     * @param taskList The TaskList to search.
     * @param str The string to search for.
     */
    public void find(TaskList taskList, String str) {
        ArrayList<String> tasksFound = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            String taskDescription = taskList.getTask(i).toString();
            boolean hasKeyword = taskDescription.toUpperCase().contains(str.toUpperCase());
            if (hasKeyword) {
                tasksFound.add((i + 1) + "." + taskDescription + ".");
            }
        }

        if (tasksFound.size() == 0) {
            display("is didn't find matching task.");
        } else {
            for (int i = 0; i < tasksFound.size(); i++) {
                display(tasksFound.get(i));
            }
        }
    }

    public void init() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        display("Hello from\n" + logo);
        display("hello name is duke");
        display("how is help today; （´・｀ ）♡");
    }

    public void exit() {
        display("okay is bye!!");
    }
}
