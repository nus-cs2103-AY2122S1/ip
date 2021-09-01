package duke;

public class Ui {

    public Ui() {

    }

    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void showLine() {
        System.out.println("_________________________________");
    }

    public void showError(DukeException error) {
        System.out.println(error);
    }

    public void bye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    public void list(TaskList tasklist) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasklist);
    }

    public void done(Task task) {
        System.out.println("Nice! I have marked this task as done!");
        System.out.println(task);
    }

    public void addTask(Task task, TaskList tasklist) {
        int length = tasklist.noOfTask();

        System.out.println("Added task:");
        System.out.println(task);
        System.out.println("You have " + length + " tasks in the list");
    }

    public void deleteTask(Task task, TaskList tasklist) {
        int length = tasklist.noOfTask();

        System.out.println("Ok! I have removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + length + " tasks in the list.");
    }
}
