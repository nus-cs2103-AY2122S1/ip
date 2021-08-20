import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * scans for user input and outputs corresponding Duke chatbot responses.
 */
public class Duke {

    // Enums for Duke chatbot descriptors
    protected enum Descriptors {
        AT("at"),
        BY("by");

        private final String DESCRIPTOR;

        public String getDescriptor() {
            return this.DESCRIPTOR;
        }

        public int getLength() {
            return this.DESCRIPTOR.length();
        }

        Descriptors(String descriptor) {
            this.DESCRIPTOR = descriptor;
        }

        @Override
        public String toString() {
            return this.DESCRIPTOR;
        }
    }

    // Enums for Duke chatbot commands
    protected enum Commands {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DONE("done"),
        DELETE("delete"),
        LIST("list"),
        DATE("date"),
        BYE("bye");

        private final String COMMAND;

        public String getCommand() {
            return this.COMMAND;
        }

        public int getLength() {
            return this.COMMAND.length();
        }

        Commands(String command) {
            this.COMMAND = command;
        }

        @Override
        public String toString() {
            return this.COMMAND;
        }
    }

    /**
     * The main method is runs the Duke chatbot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Initialize scanner object.
        Scanner sc = new Scanner(System.in);

        // Prints greeting to user.
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        // Initialize TaskList.
        TaskList tasks;

        // Read tasks from save file.
        try {
            tasks = Storage.readTasksFromData();
        } catch (DukeException dukeException) {
            System.out.println(dukeException);

            // If failed to read tasks from save, initialize a new Task ArrayList.
            tasks = new TaskList();
        }

        // Scans user inputs and prints corresponding outputs until a "Bye" input is received.
        String userInput = sc.nextLine();
        while (!userInput.equals(Commands.BYE.getCommand())) {
            handleUserInput(userInput, tasks);
            userInput = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

        // Closes scanner object.
        sc.close();
    }

    private static void handleUserInput(String userInput, TaskList tasks) {
        if (userInput.equals(Commands.LIST.getCommand())) {
            // Print tasks
            tasks.printTasks();
        } else {
            // Catches thrown DukeException if any.
            try {
                if (userInput.startsWith(Commands.DONE.getCommand())) {
                    // Mark task as done.
                    tasks.markTask(userInput);
                } else if (userInput.startsWith(Commands.DELETE.getCommand())) {
                    // Delete a task.
                    tasks.deleteTask(userInput);
                } else if (userInput.startsWith(Commands.DATE.getCommand())) {
                    // Print tasks that fall on given date.
                    tasks.printTaskAtDate(userInput);
                } else {
                    // Add a task to tasks.
                    tasks.addTask(userInput, '/');
                }

                // Save tasks to save file after each change.
                Storage.saveTasksToData(tasks.getTasks());
            } catch (DukeException dukeException) {
                System.out.println(dukeException);
            }
        }
    }

}
