import java.util.Scanner;

public class Duke {
    private static final String GREETING_MESSAGE = "____________________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________________";
    public static final String EXITING_MESSAGE = "____________________________________________________________\n" +
            "Bye. Hope to see you again soon!\n" +
            "____________________________________________________________";
    // List of tasks.
    private static Task[] tasks = new Task[100];
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
     * @param task The new task.
     */
    public static void updateList(Task task) {
        Duke.tasks[numOfTasks] = task;
        Duke.numOfTasks++;
    }

    /**
     * Returns a list of string, which is a copy of `tasks` list.
     *
     * @return A copy of tasks list.
     */
    public static Task[] getTasks() {
        Task[] copy = new Task[100];
        for (int i = 0; i < numOfTasks; i++) {
            copy[i] = Duke.tasks[i];
        }
        return copy;
    }

    /**
     * Given the appropriate processor, process the command and print the result.
     *
     * @param processor The processor provided.
     */
    private void processCommand(Processor processor) {
        this.processor = processor;
        this.processor.process();
        System.out.println(this.processor);
    }

    /**
     * Based on the command received, either quit the program or process an event.
     */
    private void readCommand() {
        // Read the command.
        Scanner input = new Scanner(System.in);
        String command = input.nextLine().trim();
        if (command.equals("bye")) {
            // Print exiting message and end the program.
            System.out.println(Duke.EXITING_MESSAGE);
            this.isRunning = false;
        } else if (command.equals("list")) {
            // List all added tasks.
            processCommand(new GetListProcessor());
        } else {
            String[] splitted = command.split(" ");
            if (splitted.length == 2 && splitted[0].equals("done")) {
                try {
                    // Finish a task, mark it as done.
                    int index = Integer.parseInt(splitted[1]) - 1;
                    processCommand(new TaskDoneProcessor(Duke.tasks[index]));
                } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
                    // Add the task to list and print the action.
                    processCommand(new AddATaskProcessor(command));
                }
            } else {
                // Add the task to list and print the action.
                processCommand(new AddATaskProcessor(command));
            }
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
