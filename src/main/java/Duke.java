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
            switch(command) {
                case "bye":
                    System.out.println("Nice talking to you, goodbye!");
                    return;
                case "list":
                    printList();
                    break;
                case "done":
                    doTask(Integer.parseInt(input)- 1);
                    printList();
                    break;
                case "todo":
                    ToDo newToDo = new ToDo(input);
                    add(newToDo);
                    break;
                case "event":
                    String[] message_and_timePeriod = input.split("/at ");
                    Event newEvent = new Event(message_and_timePeriod[0], message_and_timePeriod[1]);
                    add(newEvent);
                    break;
                case "deadline":
                    String[] message_and_endTime = input.split("/by ");
                    Deadline newDeadline = new Deadline(message_and_endTime[0], message_and_endTime[1]);
                    add(newDeadline);
                    break;
                default:
                    tasks.add(new Task(command));
                    System.out.println("\"" + command + "\" has been added to your todo list.\n");
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
        System.out.println("Alright, I've added the following task:");
        System.out.println(tasks.get(tasks.size() - 1) + "\n");
    }
}
