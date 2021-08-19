import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * scans for user input and outputs corresponding Duke chatbot responses.
 */
public class Duke {

    private static final String SAVE_FILENAME = "dukeSave.txt";

    // | is a special character that has to be escaped.
    private static final String SAVE_SEPARATOR = " ~ ";

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
            tasks = readTasksFromData();
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
                } else {
                    // Add a task to tasks.
                    addTask(tasks, userInput, '/');
                }

                // Save tasks to save file after each change.
                saveTasksToData(tasks);
            } catch (DukeException dukeException) {
                System.out.println(dukeException);
            }
        }
    }

    private static String toSaveFormat(Task task) {
        // Initialize StringBuilder.
        StringBuilder stringBuilder = new StringBuilder();

        // Get task type, done status and time of task.
        String taskType = task.getTaskType();
        int done = task.isDone ? 1 : 0;
        String time = task.getTime();

        // Build corresponding save string from task.
        stringBuilder.append(taskType).append(SAVE_SEPARATOR);
        stringBuilder.append(done).append(SAVE_SEPARATOR);
        stringBuilder.append(task.description);

        // Deadline and Event tasks have time, so they are also appended via stringBuilder.
        if (taskType.equals("D") || taskType.equals("E")) {
            stringBuilder.append(SAVE_SEPARATOR);
            stringBuilder.append(time);
        }

        return stringBuilder.toString();
    }

    private static Task parseSaveFormat(String save) throws DukeException {
        // Split save string by the save separator.
        String[] saveSplit = save.split(SAVE_SEPARATOR);

        try {
            // Get the task type, done status, description of task from saveSplit.
            String taskType = saveSplit[0];
            // Save would be corrupt if done status cannot be parsed to int.
            int isDone = Integer.parseInt(saveSplit[1]);
            String description = saveSplit[2];

            // Create corresponding Task object.
            // Save would be corrupt if Deadline and Event tasks do not have time.
            Task task;
            switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String by = saveSplit[3];
                task = new Deadline(description, by);
                break;
            case "E":
                String at = saveSplit[3];
                task = new Event(description, at);
                break;
            default:
                throw new DukeException("Save files corrupted. Failed to read tasks from save file.");
            }

            if (isDone == 1) {
                task.markAsDone();
            }

            return task;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Save files corrupted. Failed to read tasks from save file.");
        }

    }

    private static ArrayList<Task> readTasksFromData() throws DukeException {
        // Initialize an ArrayList for Task objects.
        ArrayList<Task> tasks = new ArrayList<>();

        // Get absolute path to save file.
        String cwd = System.getProperty("user.dir");
        Path absolutePathToSaveFile = Paths.get(cwd, "data", SAVE_FILENAME);

        // Check if save file exists.
        boolean isSaveFileExist = Files.exists(absolutePathToSaveFile);

        try {
            // If save file does not exist, create save file;
            if (!isSaveFileExist) {
                saveTasksToData(tasks);
            }

            // Read from save file.
            List<String> rawTasks = Files.readAllLines(absolutePathToSaveFile);

            // Parse each line into a Task object and save to tasks.
            for (int i = 0; i < rawTasks.size(); i++) {
                Task task = parseSaveFormat(rawTasks.get(i));
                tasks.add(task);
            }
        } catch (IOException ioException) {
            // Failure to read from save file.
            throw new DukeException("Failed to read tasks from save file.");
        }

        return tasks;
    }

    private static void saveTasksToData(ArrayList<Task> tasks) throws DukeException {

        // Get the absolute path to data subdirectory of project directory.
        String cwd = System.getProperty("user.dir");
        Path absolutePathToDataDir = Paths.get(cwd, "data");

        // Check if data directory exists.
        boolean isDirectoryExist = Files.exists(absolutePathToDataDir);

        try {
            // If data directory does not exist, create one.
            if (!isDirectoryExist) {
                Files.createDirectory(absolutePathToDataDir);
            }

            // Get absolute path to save file.
            Path absolutePathToSaveFile = Paths.get(absolutePathToDataDir.toString(), SAVE_FILENAME);

            // Check if file exists.
            boolean isSaveFileExist = Files.exists(absolutePathToSaveFile);

            // If file does not exist, create it.
            if (!isSaveFileExist) {
                Files.createFile(absolutePathToSaveFile);
            }

            // Stop if there are no tasks to be saved.
            if (tasks.size() == 0) {
                return;
            }

            // Generate string to be saved to save file.
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                String save = toSaveFormat(tasks.get(i));
                stringBuilder.append(save);
                // If last task in tasks, no need to append newline.
                if (i < (tasks.size() - 1)) {
                    stringBuilder.append("\n");
                    ;
                }
            }
            String textToSave = stringBuilder.toString();

            // Write to save file.
            byte[] textToSaveToBytes = textToSave.getBytes();
            Files.write(absolutePathToSaveFile, textToSaveToBytes);
        } catch (IOException ioException) {
            throw new DukeException("Failed to save tasks");
        }
    }

    private static int findIndex(String s, Character c) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    private static int parseUserNumInput(String userInput, Commands command) throws DukeException {
        // Parses integer in user input. Invalid user input could throw NumberFormatException.
        try {
            // Add 1 as user's number input is separated from command by 1 space.
            return Integer.parseInt(userInput.substring(command.getLength() + 1));
        } catch (NumberFormatException nfe) {
            // Invalid user input cannot be parsed into Integer.
            throw new DukeException("Index for " + command.getCommand() + " must be an integer.");
        }
    }

    private static void deleteTask(ArrayList<Task> tasks, String userInput) throws DukeException {
        if (userInput.length() <= (Commands.DELETE.getLength() + 1)) {
            // Missing user input for index of task to be deleted.
            throw new DukeException("An index must be provided to delete task at index.");
        } else {
            // Parses integer in user input. 1 space is accounted for as it separates command and index.
            int userNumInput = parseUserNumInput(userInput, Commands.DELETE);

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
            int userNumInput = parseUserNumInput(userInput, Commands.DONE);

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

    private static String[] parseUserDescriptionInput(String userDescription, Descriptors descriptor,
            Character separator, Commands command) throws DukeException {
        // Index of separator in userDescription.
        int separatorIdx = findIndex(userDescription, separator);

        // Index of space after descriptor
        int indexDescriptorSpace = separatorIdx + descriptor.getLength() + 1;
        if ((separatorIdx == -1) || (userDescription.length() <= indexDescriptorSpace)) {
            throw new DukeException("/" + descriptor.getDescriptor() +
                    " must be provided and not empty for " + command.getCommand() + ".");
        }

        // Index of first character following space after descriptor.
        int indexAfterDescriptorSpace = separatorIdx + descriptor.getLength() + 2;

        // User's time input.
        String time = userDescription.substring(indexAfterDescriptorSpace);

        // User's task description. Decrement by 1 as there is a space between task description and separator
        String commandDescription = userDescription.substring(0, separatorIdx - 1);

        // Returns a String array with the task description and user time input.
        return new String[] {commandDescription, time};
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
                    parseUserDescriptionInput(description, Descriptors.BY, separator, Commands.DEADLINE);

            // Adds Deadline task to tasks.
            tasks.add(new Deadline(descriptions[0], descriptions[1]));
        } else {
            // Parses description into task description and time.
            String[] descriptions =
                    parseUserDescriptionInput(description, Descriptors.AT, separator, Commands.EVENT);

            // Adds Event task to tasks.
            tasks.add(new Event(descriptions[0], descriptions[1]));
        }

        // Prints response to user after successfully adding task to tasks.
        System.out.println("Got it. I have added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
