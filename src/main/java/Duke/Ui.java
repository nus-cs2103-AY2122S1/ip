package Duke;

import Duke.task.Task;
import java.util.ArrayList;

public class Ui {

    public static String greet() {
        return "Hello! I'm Duke.\n" +
                "What can I do for you?";
    }

    public static String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public static String addTask(Task task, int size) {
        String reply = "Got it. I've added this task:\n  " +
                task + "\n" +
                "Now you have " + size + " tasks" + " in the list.";
        return reply;
    }

    public static String doneTask(Task task) {
        String reply = "Nice! I've marked this task as done:\n" +
                task;
        return reply;
    }

    public static String deleteTask(Task task, int size) {
        String reply = "Noted. I've removed this task:\n" +
                task + "\n"
                + "Now you have " + size + " tasks" + " in the list.\n";
        return reply;
    }

    public static String findTasks(ArrayList<Task> matches) {
        String reply = "Here are the matching tasks in your list:\n";
        int count = 1;
        for (Task task : matches) {
            reply += "  " + count + ". " + task + "\n";
            count++;
        }
        return reply;
    }
}
