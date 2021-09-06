package duke;

import duke.task.Task;
import duke.task.TaskList;

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
            for (int i = 1; i <= taskList.size(); i++) {
                String taskDescription = taskList.getTask(i - 1).toString();
                display(i + "." + taskDescription + ".");
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
