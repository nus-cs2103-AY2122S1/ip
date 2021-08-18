import java.util.Scanner;

public class Duke {
    private static final String GREETING_MESSAGE = "____________________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________________\n";
    public static final String EXITING_MESSAGE = "____________________________________________________________\n" +
            "Bye. Hope to see you again soon!\n" +
            "____________________________________________________________";
    // List of tasks.
    private static Task[] tasks = new Task[100];
    private static int numOfTasks = 0;
    private boolean isRunning;
    private Scanner input;

    /**
     * Constructor of the class 'Duke'.
     */
    public Duke() {
        // Start the program and greet the user.
        this.isRunning = true;
        System.out.println(Duke.GREETING_MESSAGE);
        this.input = new Scanner(System.in);
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
     * Returns the number of tasks added.
     *
     * @return Number of tasks.
     */
    public static int getNumOfTasks() {
        return Duke.numOfTasks;
    }

    /**
     * Given the appropriate processor, process the command and print the result.
     *
     * @param processor The processor provided.
     */
    private void processCommand(Processor processor) {
        processor.process();
        System.out.println(processor);
    }

    /**
     * Based on the command received, either quit the program or process an event.
     */
    private void readCommand() {
        // Read the command.
        String command = this.input.nextLine().trim();
        if (command.equals("bye")) {
            // Print exiting message and end the program.
            System.out.println(Duke.EXITING_MESSAGE);
            this.isRunning = false;
        } else if (command.equals("list")) {
            // List all added tasks.
            processCommand(new GetListProcessor());
        } else {
            String[] splitted = command.split(" ", 2);
            if (splitted[0].equals("done")) {
                // Detect a done command.
                try {
                    // Finish a task, mark it as done.
                    int index = Integer.parseInt(splitted[1]) - 1;
                    processCommand(new TaskDoneProcessor(Duke.tasks[index]));
                } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
                    // Add the task to list and print the action.
                    processCommand(new AddATaskProcessor(command, new ToDo(command)));
                }
            } else if (splitted[0].equals("deadline")) {
                // Detect a deadline command.
                String[] information = splitted[1].split("/by", 2);
                processCommand(new AddATaskProcessor(command, new Deadline(information[0], information[1])));
            } else if (splitted[0].equals("event")) {
                // Detect an event command.
                String[] information = splitted[1].split("/at", 2);
                processCommand(new AddATaskProcessor(command, new Event(information[0], information[1])));
            } else {
                // Detect a todo command.
                processCommand(new AddATaskProcessor(command, new ToDo(splitted[1])));
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
