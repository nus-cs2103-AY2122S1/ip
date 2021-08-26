package lawbringer;

import java.util.List;

public class Ui {

    public Ui() {
    }

    protected void showWelcomeMessage() {
        String message = "Hello! I'm Lawbringer!\n" +
                "What can i do for you?";
        System.out.println(message);
    }

    protected void showByeMessage() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
    }

    protected void showTodoMessage(ToDo todo, List<Task> tasks) {
        String message = "Got it. I've added this task:\n" + "  " +
                todo + "\nNow you have " + tasks.size() +
                " task(s) in the list.";
        System.out.println(message);
    }

    protected void showDeadlineMessage(Deadline deadline, List<Task> tasks) {
        String message = "Got it. I've added this task:\n" + "  " +
                deadline + "\nNow you have " + tasks.size() +
                " task(s) in the list.";
        System.out.println(message);
    }

    protected void showEventMessage(Event event, List<Task> tasks) {
        String message = "Got it. I've added this task:\n" + "  " +
                event + "\nNow you have " + tasks.size() +
                " task(s) in the list.";
        System.out.println(message);
    }

    protected void showDoneMessage(Task task) {
        String message = "Nice! I've marked this task as done:\n" +
                "  " + task.toString();
        System.out.println(message);
    }

    protected void showDeleteMessage(Task task, List<Task> tasks) {
        String message = "Noted. I've removed this task:\n" +
                "  " + task + "\nNow you have " + tasks.size() +
                " task(s) in the list.";
        System.out.println(message);
    }
}
