import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents a personal assistant chatbot that responds to command line inputs.
 *
 * @author felix-ong
 */
public class Duke {
    /**
     * Adds the given task to the given list of tasks.
     *
     * @param tasks The list of tasks to add a new task to.
     * @param task The task to be added to the list of tasks.
     */
    public static void addTask(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
        System.out.println("Added task:\n " + task);
        int taskCount = tasks.size();
        System.out.printf("You have %d %s in the list.%n", taskCount, taskCount > 1 ? "tasks" : "task");
    }

    public static void main(String[] args) {
        System.out.println("Hello, I am Duke!(≧◡≦)\nHow may I help you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        String command = sc.next();

        while (!command.equals("bye")) {
            String taskInput = sc.nextLine().stripLeading();

            switch (command) {
            case "todo":
                Task todo = new Todo(taskInput);
                addTask(tasks, todo);
                break;
            case "deadline":
                String[] deadlineParts = taskInput.split(" /by ");
                Task deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                addTask(tasks, deadline);
                break;
            case "event":
                String[] eventParts = taskInput.split(" /at ");
                Task event = new Event(eventParts[0], eventParts[1]);
                addTask(tasks, event);
                break;
            case "list":
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, tasks.get(i));
                }
                break;
            case "done":
                int taskIndex = Integer.parseInt(taskInput) - 1;
                Task doneTask = tasks.get(taskIndex);
                doneTask.markAsDone();
                System.out.printf("Good job!d(≧◡≦)b I have marked the following task as done:%n %s%n", doneTask);
                break;
            default:
                System.out.println("Please enter one of the following commands: \n todo <task>" +
                        "\n deadline <task> /by <deadline>\n event <event> /at <date time>\n list\n bye(to quit)");
                break;
            }
            command = sc.next();
        }
        System.out.println("Bye! Feel free to ask me for help again anytime! (≧▽≦)/");
        sc.close();
    }
}
