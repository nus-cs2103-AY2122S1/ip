import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        String input;
        List<Task> tasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?\n");

        while(true) {
            System.out.print("You: ");
            command = sc.next();
            input = sc.nextLine();
            System.out.print("Duke: ");
            switch(command) {
                case "bye":
                    System.out.println("Nice talking to you, goodbye!");
                    return;
                case "list":
                    printList(tasks);
                    break;
                case "done":
                    doTask(Integer.parseInt(input.trim())- 1, tasks);
                    printList(tasks);
                    break;
                case "todo":
                    tasks.add(new ToDo(command));
                    System.out.println("\"" + command + "\" has been added to your todo list.\n");
                    break;
                default:
                    tasks.add(new Task(command));
                    System.out.println("\"" + command + "\" has been added to your todo list.\n");
            }
        }
    }

    /**
     * Prints out the todo list to the console
     * @param tasks The list of tasks.
     */
    private static void printList(List<Task> tasks) {
        System.out.println("Here's your todo list!");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println();
    }

    /**
     * Marks the taskNum-th item in the tasks list as completed.
     * @param taskNum The index of the task to be marked.
     * @param tasks The list of tasks.
     */
    private static void doTask(int taskNum, List<Task> tasks) {
        tasks.get(taskNum).doTask();
    }
}
