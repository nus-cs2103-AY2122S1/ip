import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<String> tasks = new ArrayList<String>();

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

    public void addTask(String task) {
        this.tasks.add(task);
        String output = String.format("Added: %s", task);
        System.out.println(output);
    }

    public void displayTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
