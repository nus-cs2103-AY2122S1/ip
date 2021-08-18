import java.util.Scanner;

public class Duke {

    private static final String LINE_HORIZONTAL = "__________________________________";
    private static Task[] list = new Task[100];

    /***
     * Initializes the bot.
     */
    public static void main(String[] args) {

        // Prints initial message as prompt.
        System.out.printf("%s\nGreetings! This is Elsa.\n" +
                "What can I do for you?\n%s\n", LINE_HORIZONTAL, LINE_HORIZONTAL);

        // Initializes scanner to take input from user.
        Scanner scanner = new Scanner(System.in);
        String input;

        // Tracks the number of tasks added to list.
        int numTasks = 0;

        // Takes in input and performs actions accordingly.
        while(!(input = scanner.nextLine()).equalsIgnoreCase("bye")) {
            // If user asks for list of tasks.
            if (input.equalsIgnoreCase("list")) {
                printList(numTasks);
            // If user wants to mark a task as done.
            } else if (input.toLowerCase().indexOf("done") != -1){
                int toMark = Integer.parseInt(input.substring(5));
                markTaskAsDone(toMark);
            // If user wants to create a to do.
            } else if (input.toLowerCase().indexOf("todo") != -1) {
                numTasks = addToDo(input, numTasks);
            // If user wants to create a deadline.
            } else if (input.toLowerCase().indexOf("deadline") != -1) {
                numTasks = addDeadline(input, numTasks);
            // If user wants to create an event.
            } else if (input.toLowerCase().indexOf("event") != -1) {
                numTasks = addEvent(input, numTasks);
            // If user enters an unspecified request.
            } else {
                System.out.println("I don't quite understand what that means.");
                System.out.println("Could you please rephrase it?");
            }
        }

        // Bids farewell to user upon "bye" input.
        System.out.printf("%s\nGoodbye. Hope to see you again!\n", LINE_HORIZONTAL);

        scanner.close();
    }

    /***
     * Prints out every task in the list.
     *
     * @param numTasks The number of tasks already in the list.
     */
    public static void printList(int numTasks) {
        System.out.println(LINE_HORIZONTAL);
        for (int i = 0; i < numTasks; i++) {
            String taskName = list[i].toString();
            System.out.printf("%d.%s\n", i + 1, taskName);
        }
        System.out.println(LINE_HORIZONTAL);
    }

    /***
     * Marks the corresponding task as done and prints confirmation.
     *
     * @param toMark The index of the task to be marked.
     */
    public static void markTaskAsDone(int toMark) {
        list[toMark - 1].markAsDone();
        System.out.printf("%s\nGreat job!\n" +
                "The following task is marked as done:\n", LINE_HORIZONTAL);
        System.out.printf("\t%s\n%s\n", list[toMark - 1].toString(), LINE_HORIZONTAL);
    }

    /***
     * Adds the to do entered by the user to the list and prints it.
     *
     * @param input The to do inputted by the user.
     * @param numTasks The number of tasks already in the list.
     * @return The number of tasks in the list after adding this task.
     */
    public static int addToDo(String input, int numTasks) {
        String taskName = input.substring(5);
        list[numTasks] = new ToDo(taskName);
        printTaskAdded(taskName, numTasks);
        return numTasks + 1;
    }

    /***
     * Adds the deadline entered by the user to the list and prints it.
     *
     * @param input The deadline inputted by the user.
     * @param numTasks The number of tasks already in the list.
     * @return The number of tasks in the list after adding this task.
     */
    public static int addDeadline(String input, int numTasks) {
        int separation = input.indexOf('/');
        String taskName = input.substring(9, separation - 1);
        String time = input.substring(separation + 4);
        list[numTasks] = new Deadline(taskName, time);
        printTaskAdded(taskName, numTasks);
        return numTasks + 1;
    }

    /***
     * Adds the event entered by the user to the list and prints it.
     *
     * @param input The event inputted by the user.
     * @param numTasks The number of tasks already in the list.
     * @return The number of tasks in the list after adding this task.
     */
    public static int addEvent(String input, int numTasks) {
        int separation = input.indexOf('/');
        String taskName = input.substring(6, separation - 1);
        String time = input.substring(separation + 4);
        list[numTasks] = new Event(taskName, time);
        printTaskAdded(taskName, numTasks);
        return numTasks + 1;
    }

    /***
     * Prints the confirmation of the addition of the task.
     *
     * @param taskName The name of the task added.
     * @param numTasks The number of tasks prior to adding this task.
     */
    public static void printTaskAdded(String taskName, int numTasks) {
        System.out.printf("%s\nGotcha! The following task has been added:\n", LINE_HORIZONTAL);
        System.out.printf("\t%s\n", taskName);
        System.out.printf("You now have %d tasks in your list!\n%s\n",
                numTasks + 1, LINE_HORIZONTAL);
    }
}
