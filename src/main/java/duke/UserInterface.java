package duke;

import java.util.*;

public class UserInterface {
    TaskList taskList;

    public UserInterface() {
        this.taskList = new TaskList();
    }

    public UserInterface(TaskList taskList) {
        this.taskList = taskList;
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("Problem loading ");
    }

    public void showList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= taskList.getSize(); i++) {
            Task t = taskList.getTask(i - 1);
            System.out.println(i + "." + t.toString());
        }
    }

    public void showCompletedTask(Task t) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(t.toString());
    }

    public void showAddedTask(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }

    public void showDeletedTask(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }

    public void showResults(List<Task> results) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= results.size(); i++) {
            Task t = results.get(i - 1);
            System.out.println(i + "." + t.toString());
        }
    }
}
