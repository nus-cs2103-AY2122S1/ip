import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<Task> tasks = new ArrayList<Task>();

    /**
     * Greet the user.
     */
    public void greet() {
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greet);
    }

    /**
     * Echo the command entered.
     *
     * @param command a command said by the user
     */
    public void echo(String command) {
        System.out.println(command);
    }

    /**
     * exit behavior.
     */
    public void bye() {
        String byeCommand = "Bye. Hope to see you again soon!";
        System.out.println(byeCommand);
    }

    /**
     * Add a task.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        String output = "Added: " + task.toString();
        System.out.println(output);
    }

    /**
     * display a list of tasks added.
     *
     */
    public void displayTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = tasks.get(i);
            boolean isDone = task.isDone;
            System.out.println((i + 1) + ". " + (isDone ? "[X] " : "[ ] ")  + tasks.get(i).toString());
        }
    }

    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    public void markTaskAsDone(int taskIdx) {
        Task task = this.tasks.get(taskIdx);
        task.markAsDone();
        System.out.println(" Nice! I've marked this task as done: \n" + "[X] " + task.toString());
    }

    public void noTaskExist() {
        System.out.println("This task does not exist");
    }
}
