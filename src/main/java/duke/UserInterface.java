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

    public String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String s = "";
        s += "Hello from\n" + logo + "\n" + "What can I do for you?";
        return s;
    }

    public String showLoadingError() {
        return "Problem loading";
    }

    public String showList() {
        String s = "Here are the tasks in your list:\n";
        for (int i = 1; i <= taskList.getSize(); i++) {
            Task t = taskList.getTask(i - 1);
            s += i + "." + t.toString() + "\n";
        }
        return s;
    }

    public String showCompletedTask(Task t) {
        String s = "Nice! I've marked this task as done:\n" + t.toString();
        return s;
    }

    public String showAddedTask(Task t) {
        String s = "Got it. I've added this task:" + "\n" + t.toString()
                + "\nNow you have " + taskList.getSize() + " tasks in the list.";

        return s;
    }

    public String showDeletedTask(Task t) {
        String s = "Noted. I've removed this task:\n" + t.toString() + "\nNow you have " + taskList.getSize()
                + " tasks in the list.";

        return s;
    }

    public String showResults(List<Task> results) {
        String s = "Here are the matching tasks in your list:\n";
        for (int i = 1; i <= results.size(); i++) {
            Task t = results.get(i - 1);
            s += i + "." + t.toString() + "\n";
        }

        return s;
    }
}
