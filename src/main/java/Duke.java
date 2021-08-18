import java.util.Scanner;

public class Duke {
    private static final String GREETING_MESSAGE = "____________________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________________";
    public static final String EXITING_MESSAGE = "____________________________________________________________\n" +
            "Bye. Hope to see you again soon!\n" +
            "____________________________________________________________";
    private static String[] tasks = new String[100];
    private static int numOfTasks = 0;
    private Processor processor;
    private boolean isRunning;

    /**
     * Constructor of the class 'Duke'.
     */
    public Duke() {
        // Start the program and greet the user.
        this.isRunning = true;
        System.out.println(Duke.GREETING_MESSAGE);
    }

    /**
     * Updates a new task to the task list.
     *
     * @param task The new task as a string.
     */
    public static void updateList(String task) {
        Duke.tasks[numOfTasks] = task;
        Duke.numOfTasks++;
    }

    /**
     * Returns a list of string, which is a copy of `tasks` list.
     *
     * @return A copy of tasks list.
     */
    public static String[] getTasks() {
        String[] copy = new String[100];
        for (int i = 0; i < numOfTasks; i++) {
            copy[i] = Duke.tasks[i];
        }
        return copy;
    }

    /**
     * Based on the command received, either quit the program or process an event.
     */
    private void readCommand() {
        // Read the command.
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        if (command.equals("bye")) {
            // Print exiting message and end the program.
            System.out.println(Duke.EXITING_MESSAGE);
            this.isRunning = false;
        } else if (command.equals("list")) {
            // List all added tasks.
            this.processor = new GetListProcessor();
            this.processor.process();
            System.out.println(this.processor);
        } else {
            // Add the task to list and print the action.
            this.processor = new AddATaskProcessor(command);
            this.processor.process();
            System.out.println(this.processor);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        // Process the commands received.
        while (duke.isRunning) {
            duke.readCommand();
        }
    }
}
