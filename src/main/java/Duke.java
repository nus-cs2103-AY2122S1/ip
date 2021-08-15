import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * The list of tasks that are inputted by the user.
     */
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * The number of tasks.
     */
    private static int noOfTasks = 0;

    /**
     * Prints a line.
     */
    private static void printLine() {
        System.out.println("_________________________________________");
    }

    /**
     * Prints the greeting message.
     */
    private static void greetUser() {
        printLine();
        System.out.println("Greetings! I am Duke.");
        System.out.println("How can I assist you?");
        printLine();
    }

    /**
     * Prints the farewell message.
     */
    private static void farewellUser() {
        printLine();
        System.out.println("Bye! See you soon!");
        printLine();
    }

    /**
     * Adds the task to the list and prints the added task.
     *
     * @param task the task that will be added to the list
     */
    private static void addToList(Task task) {
        taskList.add(task);
        noOfTasks++;
        System.out.printf("Got it. I've added this task:%n  %s%nNow you have %d tasks in the list.%n",
            task, noOfTasks);
    }

    /**
     * Prints all the tasks in the list in order.
     */
    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, taskList.get(i));
        }
    }

    /**
     * Marks the task with taskNo specified and prints the task completed.
     *
     * @param taskNo the task number.
     */
    private static void markTaskAsDone(int taskNo) {
        Task task = taskList.get(taskNo - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("  %s%n", task);
    }

    /**
     * Creates deadline when given userInput.
     */
    public static Deadline createDeadline(String userInput) {
        String[] splitBySlash = userInput.split("/");
        String description = splitBySlash[0].substring(9, splitBySlash[0].length() - 1);
        String by = splitBySlash[1].substring(3);
        return new Deadline(description, by);
    }

    /**
     * Creates event when given userInput.
     */
    public static Event createEvent(String userInput) {
        String[] splitBySlash = userInput.split("/");
        String description = splitBySlash[0].substring(6, splitBySlash[0].length() - 1);
        String dayTime = splitBySlash[1].substring(3);
        return new Event(description, dayTime);
    }

    /**
     * Interacts with the user.
     *
     * @param args array of strings.
     */
    public static void main(String[] args) {
        greetUser();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String[] splitInput = userInput.split(" ");
        while (!userInput.equalsIgnoreCase("bye")) {
            printLine();
            if (userInput.equals("list")) {
                printTasks();
            } else {
                switch (splitInput[0]) {
                    case "done":
                        markTaskAsDone(Integer.parseInt(splitInput[1]));
                        break;
                    case "todo":
                        addToList(new Todo(userInput.substring(5)));
                        break;
                    case "deadline":
                        addToList(createDeadline(userInput));
                        break;
                    case "event":
                        addToList(createEvent(userInput));
                        break;
                }
            }
            printLine();
            userInput = scanner.nextLine();
            splitInput = userInput.split(" ");
        }
        scanner.close();

        farewellUser();
    }
}
