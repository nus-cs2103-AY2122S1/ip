import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        String input;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?\n");

        while(true) {
            System.out.print("You: ");
            command = sc.next();
            input = sc.nextLine().trim();
            System.out.print("Duke: ");
            switch (command) {
                case "bye":
                    System.out.println("Nice talking to you, goodbye!");
                    return;
                case "list":
                    printList();
                    break;
                case "done":
                    doTask(Integer.parseInt(input) - 1);
                    printList();
                    break;
                default:
                    try {
                        add(Task.createTask(command, input));
                    } catch (NoSuchCommandException e) {
                        System.out.println(e.getMessage());
                    }
            }
        }
    }

    /**
     * Prints out the todo list to the console
     */
    private static void printList() {
        System.out.println("Here's your todo list!");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println();
    }

    /**
     * Marks the taskNum-th item in the tasks list as completed.
     * @param taskNum The index of the task to be marked.
     */
    private static void doTask(int taskNum) {
        tasks.get(taskNum).doTask();
    }

    /**
     * Adds a task to the tasks list and prints a success message.
     * @param task The task to be added.
     */
    private static void add(Task task) {
        tasks.add(task);
        System.out.println("Alright, I've added the following task:\n");
        System.out.println(tasks.get(tasks.size() - 1) + "\nNow you have " + tasks.size() + " tasks in the list.\n");
    }
}
