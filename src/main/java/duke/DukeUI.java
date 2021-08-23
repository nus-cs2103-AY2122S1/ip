package duke;

public class DukeUI {

    public void goodBye() {
        System.out.println("Goodbye!");
    }

    public void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from \n" + logo);
        System.out.println("What can I do for you?");
    }

    public void markTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showDeleteTaskMessage(int tasksLength) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("Now you have " + tasksLength + " tasks in the list.");
    }

    public void showTaskAddedMessage(int tasksLength, String task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + tasksLength + " tasks in the list.");
    }
}
