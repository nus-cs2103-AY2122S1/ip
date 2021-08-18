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
        System.out.println("Hello, I am Duke!\nHow may I help you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        String command = sc.next();

        while (!command.equals("bye")) {
            try {
                switch (command) {
                case "todo":
                    String todoInput = sc.nextLine().strip();
                    if (todoInput.isBlank()) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task todo = new Todo(todoInput);
                    addTask(tasks, todo);
                    break;
                case "deadline":
                    String deadlineInput = sc.nextLine().strip();
                    if (deadlineInput.isBlank()) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] deadlineParts = deadlineInput.split(" /by ");
                    Task deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                    addTask(tasks, deadline);
                    break;
                case "event":
                    String eventInput = sc.nextLine().strip();
                    if (eventInput.isBlank()) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] eventParts = eventInput.split(" /at ");
                    Task event = new Event(eventParts[0], eventParts[1]);
                    addTask(tasks, event);
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf(" %d. %s%n", i + 1, tasks.get(i));
                    }
                    break;
                case "done":
                    String doneInput = sc.nextLine().strip();
                    if (doneInput.isBlank()) {
                        throw new DukeException("☹ OOPS!!! Please provide the index of the " +
                                "task you want to mark as done.");
                    }
                    int taskIndex = Integer.parseInt(doneInput) - 1;
                    Task doneTask = tasks.get(taskIndex);
                    doneTask.markAsDone();
                    System.out.printf("Good job! I have marked the following task as done:%n %s%n", doneTask);
                    break;
                case "delete":
                    String deleteInput = sc.nextLine().strip();
                    if (deleteInput.isBlank()) {
                        throw new DukeException("☹ OOPS!!! Please provide the index of the " +
                                "task you want to delete.");
                    }
                    int deleteTaskIndex = Integer.parseInt(deleteInput) - 1;
                    Task toBeDeleted = tasks.get(deleteTaskIndex);
                    tasks.remove(deleteTaskIndex);
                    System.out.printf("Noted! I have removed the following task:%n %s%n", toBeDeleted);
                    int taskCount = tasks.size();
                    System.out.printf("You have %d %s in the list.%n", taskCount, taskCount > 1 ? "tasks" : "task");
                    break;
                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means (X_X)" +
                            "\nPlease enter one of the following commands:\n todo <task>" +
                            "\n deadline <task> /by <deadline>\n event <event> /at <date time>\n list\n bye(to quit)");
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            command = sc.next();
        }
        System.out.println("Bye! Feel free to ask me for help again anytime!");
        sc.close();
    }    
}

