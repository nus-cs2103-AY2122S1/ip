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

        // Initialize ArrayList for Task objects.
        ArrayList<Task> tasks;

        // Read tasks from save file.
        try {
            tasks = Storage.readTasksFromData();
        } catch (DukeException dukeException) {
            System.out.println(dukeException);

            // If failed to read tasks from save, initialize a new Task ArrayList.
            tasks = new ArrayList<>();
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

    private static void handleUserInput(String userInput, ArrayList<Task> tasks) {
        if (userInput.equals(Commands.LIST.getCommand())) {
            // Print tasks
            printTasks(tasks);
        } else {
            // Catches thrown DukeException if any.
            try {
                if (userInput.startsWith(Commands.DONE.getCommand())) {
                    // Mark task as done.
                    markTask(tasks, userInput);
                } else if (userInput.startsWith(Commands.DELETE.getCommand())) {
                    // Delete a task.
                    deleteTask(tasks, userInput);
                } else if (userInput.startsWith(Commands.DATE.getCommand())) {
                    // Print tasks that fall on given date.
                    printTaskAtDate(tasks, userInput);
                } else {
                    // Add a task to tasks.
                    addTask(tasks, userInput, '/');
                }

                // Save tasks to save file after each change.
                Storage.saveTasksToData(tasks);
            } catch (DukeException dukeException) {
                System.out.println(dukeException);
            }
        }
    }

    private static void printTaskAtDate(ArrayList<Task> tasks, String userInput) throws DukeException {
        int counter = 0;
        int events = 0;
        int deadlines = 0;
        String dateString = userInput.substring(Commands.DATE.getLength() + 1);
        LocalDate localDate = Parser.toLocalDate(dateString);
        String formattedDateString = Parser.parseLocalDate(localDate);

        System.out.println("Here are the Deadlines or Events that fall on " + formattedDateString + ":");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (localDate.equals(deadline.by)) {
                    counter++;
                    deadlines++;
                    System.out.println(counter + "." + deadline);
                }
            }

            if (task instanceof Event) {
                Event event = (Event) task;
                if (localDate.equals(event.at)) {
                    counter++;
                    events++;
                    System.out.println(counter + "." + event);
                }
            }
        }

        System.out.println("A total of " + counter + " events (" + deadlines + " deadlines and " +
                events + " events) fall on " + formattedDateString);
    }

    private static void deleteTask(ArrayList<Task> tasks, String userInput) throws DukeException {
        if (userInput.length() <= (Commands.DELETE.getLength() + 1)) {
            // Missing user input for index of task to be deleted.
            throw new DukeException("An index must be provided to delete task at index.");
        } else {
            // Parses integer in user input. 1 space is accounted for as it separates command and index.
            int userNumInput = Parser.parseUserNumInput(userInput, Commands.DELETE);

            // Decrement integer from user input to match indexing of tasks.
            int idx = userNumInput - 1;

            // Checks for invalid index.
            if (idx >= tasks.size() || idx < 0) {
                throw new DukeException("Index provided for delete is either less than 1 or exceeds the length of the list, hence invalid.");
            }

            // Assigns task to be deleted to tempTask for printing.
            Task tempTask = tasks.get(idx);

            // Deletes task at index.
            tasks.remove(idx);

            // Prints response to user after successfully deleting task at index.
            System.out.println("Noted. I've removed this task:");
            System.out.println(tempTask.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    private static void printTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            // Increment i by 1 so number matches display indexing which starts from 1.
            int idx = i + 1;

            // Format should be "?. taskDescription\n"
            System.out.printf("%d.%s%n", idx, tasks.get(i).toString());
        }
    }

    private static void markTask(ArrayList<Task> tasks, String userInput) throws DukeException {
        if (userInput.length() <= (Commands.DONE.getLength() + 1)) {
            // Missing user input for index of task to be marked as done.
            throw new DukeException("An index must be provided to mark task at the index as done.");
        } else {
            // Parses integer in user input.
            int userNumInput = Parser.parseUserNumInput(userInput, Commands.DONE);

            // Decrement integer from user input to match indexing of tasks.
            int idx = userNumInput - 1;

            // Checks for invalid index.
            if (idx >= tasks.size() || idx < 0) {
                throw new DukeException("Index provided for done is either less than 1 or exceeds the length of the list, hence invalid.");
            }

            // Marks task at index as done.
            tasks.get(idx).markAsDone();

            // Prints response to user after successfully marking task at index as done.
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(idx).toString());
        }
    }

    private static void addTask(ArrayList<Task> tasks, String userInput, Character separator) throws DukeException {
        // Checks for command given in user input.
        String userCommand;
        if (userInput.startsWith(Commands.TODO.getCommand())) {
            userCommand = Commands.TODO.getCommand();
        } else if (userInput.startsWith(Commands.DEADLINE.getCommand())) {
            userCommand = Commands.DEADLINE.getCommand();
        } else if (userInput.startsWith(Commands.EVENT.getCommand())) {
            userCommand = Commands.EVENT.getCommand();
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        // Checks if user input contains a task description and obtain it if it exists.
        if (userInput.length() <= userCommand.length() + 1) {
            throw new DukeException("The description of " + userCommand + " cannot be empty.");
        }
        String description = userInput.substring(userCommand.length() + 1);

        // Parses description and adds the corresponding task to tasks.
        if (userCommand.equals(Commands.TODO.getCommand())) {
            // Adds to-do task to tasks.
            tasks.add(new Todo(description));
        } else if (userCommand.equals(Commands.DEADLINE.getCommand())) {
            // Parses description into task description and time.
            String[] descriptions =
                    Parser.parseUserDescriptionInput(description, Descriptors.BY, separator, Commands.DEADLINE);

            // Convert time to LocalDate.
            LocalDate localDate = Parser.toLocalDate(descriptions[1]);

            // Adds Deadline task to tasks.
            tasks.add(new Deadline(descriptions[0], localDate));
        } else {
            // Parses description into task description and time.
            String[] descriptions =
                    Parser.parseUserDescriptionInput(description, Descriptors.AT, separator, Commands.EVENT);

            // Convert time to LocalDate.
            LocalDate localDate = Parser.toLocalDate(descriptions[1]);

            // Adds Event task to tasks.
            tasks.add(new Event(descriptions[0], localDate));
        }

        // Prints response to user after successfully adding task to tasks.
        System.out.println("Got it. I have added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
