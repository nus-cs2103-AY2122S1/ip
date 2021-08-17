import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static final String LINE_HORIZONTAL = "__________________________________";
    private static Task[] list = new Task[100];

    /***
     * Initializes the bot.
     */
    public static void main(String[] args) {

        // Prints initial message as prompt.
        System.out.printf("%s\nGreetings! This is Elsa.\nWhat can I do for you?\n%s\n", LINE_HORIZONTAL, LINE_HORIZONTAL);

        // Initializes scanner to take input from user.
        Scanner scanner = new Scanner(System.in);
        String input;

        // Tracks the number of tasks added to list.
        int numTasks = 0;

        // Takes in input and performs actions accordingly.
        while(!(input = scanner.nextLine()).equalsIgnoreCase("bye")) {
            switch (input.toLowerCase()) {
                case "list":
                    printList(numTasks);
                    break;
                default:
                    numTasks = addTask(input, numTasks);
            }
        }

        // Bids farewell to user upon "bye" input.
        System.out.printf("%s\nGoodbye. Hope to see you again!\n", LINE_HORIZONTAL);

        scanner.close();
    }

    /***
     * Prints out every task in the list.
     * @param numTasks The number of tasks already in the list.
     */
    public static void printList(int numTasks) {
        System.out.println(LINE_HORIZONTAL);
        for (int i = 0; i < numTasks; i++) {
            String taskName = list[i].toString();
            System.out.printf("%d. %s\n", i + 1, taskName);
        }
        System.out.println(LINE_HORIZONTAL);
    }


    /***
     * Adds the task entered by the user to the list and prints it.
     * @param input The task inputted by the user.
     * @param numTasks The number of tasks already in the list.
     * @return The number of tasks in the list after adding this task.
     */
    public static int addTask(String input, int numTasks) {
        list[numTasks] = new Task(input);
        System.out.printf("%s\nGotcha!\nadded: %s\n%s\n", LINE_HORIZONTAL, input, LINE_HORIZONTAL);
        return numTasks + 1;
    }
}
