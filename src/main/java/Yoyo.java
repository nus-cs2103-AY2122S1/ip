import java.util.Scanner;

public class Yoyo {
    private static Task[] tasks = new Task[100];
    private static int numTasks = 0;


    private enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

    /**
     * Prints a decoration line for output.
     */
    private static void printLineDecoration() {
        System.out.println("========================================================================");
    }

    public static void main(String[] args) {
        String greetings = "Hello! I'm Yoyo\n"
                + "What can I do for you?\n";
        System.out.println(greetings);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String[] inputWords = input.split(" ", 2);
            String command = inputWords[0];
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else if (command.equals("list")) {
                printLineDecoration();
                for (int i = 0; i < numTasks; i++) {
                    System.out.println(i + 1 + "." + tasks[i].showStatus());
                }
                printLineDecoration();
                System.out.println("\n");
            } else if (command.equals("done")) {
                try {
                    int taskIndex = Integer.parseInt(inputWords[1]) - 1;
                    tasks[taskIndex].toggleDone();
                    printLineDecoration();
                    System.out.println("Nice! I've marked this task as done:\n"
                            + tasks[taskIndex].showStatus());
                    printLineDecoration();
                    System.out.println();

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please enter a valid index!\n");
                }
            } else {
                if (command.equals("todo")) {
                    Task newTask = new Todo(inputWords[1]);
                    tasks[numTasks] = newTask;
                    numTasks++;
                    printAddMessage(newTask);
                } else if (command.equals("event")) {
                    String[] taskInfo = inputWords[1].split(" /");
                    if (taskInfo.length < 2) {
                        System.out.println("Please add a timing for the event!\n");
                    } else {
                        Task newTask = new Event(taskInfo[0], taskInfo[1]);
                        tasks[numTasks] = newTask;
                        numTasks++;
                        printAddMessage(newTask);
                    }
                } else if (command.equals("deadline")) {
                    String[] taskInfo = inputWords[1].split(" /");
                    if (taskInfo.length < 2 ) {
                        System.out.println("Please add a timing for the event!\n");
                    } else {
                        Task newTask = new Deadline(taskInfo[0], taskInfo[1]);
                        tasks[numTasks] = newTask;
                        numTasks++;
                        printAddMessage(newTask);
                    }
                } else {
                    System.out.println("Invalid Command\n");
                }
            }
        }
    }

    /**
     * prints success message for adding task.
     *
     * @param newTask The task that has been created.
     */
    private static void printAddMessage(Task newTask) {
        printLineDecoration();
        System.out.print("Got it. I've added this task:\n   "
                + newTask.showStatus()
                + "\nNow you have "
                + numTasks
                + " tasks in the list.\n");
        printLineDecoration();
        System.out.println();
    }
}
