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
            } else {
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
            System.out.printf("%d.%s\n", i + 1, taskName);
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

    /***
     * Marks the corresponding task as done and prints confirmation.
     * @param toMark The index of the task to be marked.
     */
    public static void markTaskAsDone(int toMark) {
        list[toMark - 1].markAsDone();
        System.out.printf("%s\nGreat job!\n" +
                "The following task is marked as done:\n", LINE_HORIZONTAL);
        System.out.printf("\t%s\n%s\n", list[toMark - 1].toString(), LINE_HORIZONTAL);
    }
}
