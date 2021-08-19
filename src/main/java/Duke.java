import java.util.Scanner;

public class Duke {
    private static int taskCount = 0;
    private static final int MAX = 100;
    private static Task[] tasks = new Task[MAX];

    public static void main(String[] args) {
        System.out.println("Hello...\nWhat do you want?\n");
        Scanner myScanner = new Scanner(System.in);
        String answer = myScanner.nextLine();

        while (!answer.equals("bye")) {
            String[] parts = answer.split(" ");
            String taskType = parts[0];
            String taskDetails = answer.substring(answer.indexOf(" ") + 1);
            if (taskType.equals("done")) {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("I've marked this task as done: ");
                System.out.println("\t" + tasks[taskIndex]);
            } else if (taskType.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                addNewTask(taskType, taskDetails);
            }
            answer = myScanner.nextLine();
        }
        System.out.println("Whatever...");
    }

    private static void addNewTask(String taskType, String taskDetails) {
        if (taskType.equals("todo")) {
            tasks[taskCount] = new Todo(taskDetails);
        } else if (taskType.equals("event")) {
            String[] parts = taskDetails.split(" /at ");
            String description = parts[0];
            String at = parts[1]; 
            tasks[taskCount] = new Event(description, at);
        } else if (taskType.equals("deadline")) {
            String[] parts = taskDetails.split(" /by ");
            String description = parts[0];
            String by = parts[1]; 
            tasks[taskCount] = new Event(description, by);
        } else {
            return;
        }
        System.out.println("Got it. I've added this task:\n\t" + tasks[taskCount]);
        taskCount += 1;
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
}
