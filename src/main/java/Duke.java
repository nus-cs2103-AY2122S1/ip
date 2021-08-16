import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /**
     * Our main method. Starts up the chatbot and waits for user inputs
     * @param args Command Line Arguments
     */
    public static void main(String[] args) {
        String div = "____________________________________________________________\n";
        System.out.println(div + "Hello! I'm Bobby\nWhat can I do for you?\n" + div);
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int totalTasks = 0;
        label:
        while (true) {
            String userInput = sc.nextLine();
            List<String> userInputList = new LinkedList<>(Arrays.asList(userInput.split(" ")));
            String command = userInputList.get(0);
            switch (command) {
            case "bye":
                System.out.println(div + "Bye. Hope to see you again soon!\n" + div);
                break label;
            case "list":
                System.out.println(div + "Here are the tasks in your list:");
                for (int i = 0; i < totalTasks; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(div);
                break;
            case "done":
                Task taskCompleted = tasks[Integer.parseInt(userInputList.get(1)) - 1];
                taskCompleted.markAsDone();
                System.out.println(div + "Nice! I've marked this task as done:");
                System.out.println("  " + taskCompleted + "\n" + div);
                break;
            default:
                // if not a specified command, add new task to list
                // remove first command string
                userInputList.remove(0);
                String description;
                String[] userInputArgs;

                switch (command) {
                case "todo":
                    description = String.join(" ", userInputList);
                    tasks[totalTasks] = new ToDo(description);
                    break;
                case "deadline":
                    //split description and by
                    userInputArgs = String.join(" ", userInputList).split("/by ");
                    description = userInputArgs[0];
                    String by = userInputArgs[1];
                    tasks[totalTasks] = new Deadline(description, by);
                    break;
                case "event":
                    //split description and at
                    userInputArgs = String.join(" ", userInputList).split("/at ");
                    description = userInputArgs[0];
                    String at = userInputArgs[1];
                    tasks[totalTasks] = new Event(description, at);
                    break;
                }
                totalTasks++;
                System.out.println(div + "Got it. I've added this task:\n  " + tasks[totalTasks - 1] + "\n"
                        + "Now you have " + totalTasks + " tasks in the list.\n" + div);
            }
        }
    }
}
