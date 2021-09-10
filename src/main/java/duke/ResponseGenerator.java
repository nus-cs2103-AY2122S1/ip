package duke;

import java.util.*;

public class ResponseGenerator {
    public String greet() {
        String s = "Hello from Duke" + "\n" + "What can I do for you?";
        return s;
    }

    public String showLoadingError() {
        return "Problem loading";
    }

    public String showGoodbyeMessage() { return "Bye. Hope to see you again soon!"; }

    public String showDuplicateTaskMessage() {
        return "You can't add a duplicate task.";
    }

    public String showList(TaskList taskList) {
        String s = "Here are the tasks in your list:\n";
        for (int i = 1; i <= taskList.getSize(); i++) {
            Task t = taskList.getTask(i - 1);
            s += i + "." + t.toString() + "\n";
        }
        return s;
    }

    public String showCompletedTask(Task t) {
        return "Nice! I've marked this task as done:\n" + t.toString();
    }

    public String showAddedTask(Task t, TaskList taskList) {
        return "Got it. I've added this task:" + "\n" + t.toString()
                + "\nNow you have " + taskList.getSize() + " tasks in the list.";
    }

    public String showDeletedTask(Task t, TaskList taskList) {
        return "Noted. I've removed this task:\n" + t.toString() + "\nNow you have " + taskList.getSize()
                + " tasks in the list.";
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
