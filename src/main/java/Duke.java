import java.io.File;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // Data file path
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/duke.txt";


    // Initialize string array to store the list
    private static final ArrayList<Task> TASKS = new ArrayList<>();
    private static int numOfTask = 0;

    enum Command {
        LIST, DONE, TODO, EVENT, DEADLINE, DELETE
    }

    enum TaskType {
        TODO, EVENT, DEADLINE
    }

    // Handle data file reading
    private static void readData() {
        try {
            Path dirPath = Paths.get(DIRECTORY_PATH);
            File dataFile = new File(FILE_PATH);

            // Create data directory if it doesn't exist
            if (!Files.exists(dirPath))
                Files.createDirectory(dirPath);

            // Greet user and create data file if it doesn't exist
            Printer.prettyPrint(String.format("Welcome %s\n%s\tI'm Desmond,\n\thow may I serve you?\n"
                    , dataFile.createNewFile()? "to" : "back"
                    , Printer.logo));

            // Read the file and add to the list
            Scanner dataScanner = new Scanner(dataFile);
            while (dataScanner.hasNext()) {
                // Extract task details into three parts
                String[] taskDetails = dataScanner.nextLine().split(" \\| ", 3);
                TaskType type = TaskType.valueOf(taskDetails[0].toUpperCase());
                boolean isDone = taskDetails[1].equals("1");
                String description = taskDetails[2];

                // Create task based on the extracted details
                Task task = null;
                switch (type) {
                case TODO:
                    task = new Todo(description);
                    break;
                case EVENT:
                    String at = taskDetails[3];
                    task = new Event(description, at);
                    break;
                case DEADLINE:
                    String by = taskDetails[3];
                    task = new Deadline(description, by);
                    break;
                }

                // Add to the task list if and only if it is valid in data file
                if (task != null) {
                    if (isDone)
                        task.markAsDone();
                    TASKS.add(task);
                }
            }
        } catch (IOException |UnsupportedOperationException | SecurityException e) {
            Printer.prettyPrint("File reading failed: " + e);
        }
    }

    private static void printAddOrDelete(boolean isAdd, Task task, int numOfTask) {
        Printer.prettyPrint(String.format("%s. I've %s this task:\n\t %s\n\tNow you have %d tasks in the list.",
                isAdd ? "Got it" : "Noted",
                isAdd ? "added" : "deleted",
                task.toString(),
                numOfTask));
    }

    private static String[] extractCommand(String[] command) throws EmptyDescriptionException, IncompleteDescriptionException {
        if (command.length < 2 || command[1].trim().isEmpty())
            throw new EmptyDescriptionException(String.format("The description of a %s cannot be empty.", command[0]));
        String[] description = command[1].split(" /by | /at ", 2);
        if (!command[0].equals("todo") &&
                (description.length < 2 || description[0].trim().isEmpty() || description[1].trim().isEmpty()))
            throw new IncompleteDescriptionException(String.format("The description of a %s is incomplete.", command[0]));
        return description;
    }

    private static void addThenPrint(String[] command, int numOfTask) {
        try {
            String[] descriptions = extractCommand(command);
            Task task = null;

            switch (TaskType.valueOf(command[0].toUpperCase())) {
            case TODO:
                task = new Todo(descriptions[0]);
                break;
            case EVENT:
                task = new Event(descriptions[0], descriptions[1]);
                break;
            case DEADLINE:
                task = new Deadline(descriptions[0], descriptions[1]);
                break;
            }

            if (task != null) {
                TASKS.add(task);
                printAddOrDelete(true, task, ++numOfTask);
            }
        } catch (EmptyDescriptionException | IncompleteDescriptionException e) {
            Printer.prettyPrint(e.toString());
        }
    }

    public static void main(String[] args) {
        // Load data file
        readData();

        // Initialize scanner to get user input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Execute based on command (user input)
        // Exit when user commands "bye"
        while (!input.equals("bye")) {
            String[] command = input.split(" ", 2);
            try {
                switch (Command.valueOf(command[0].toUpperCase())) {
                case LIST:
                    Printer.prettyPrint("Here are the tasks in your list:\n" +
                            Printer.listTask(TASKS, numOfTask));
                    break;
                case DONE:
                    TASKS.get(Integer.parseInt(command[1]) - 1).markAsDone();
                     break;
                case DELETE:
                    printAddOrDelete(false, TASKS.get(Integer.parseInt(command[1]) - 1), --numOfTask);
                    TASKS.remove(Integer.parseInt(command[1]) - 1);
                    break;
                case TODO:
                    // Fallthrough
                case EVENT:
                    // Fallthrough
                case DEADLINE:
                    addThenPrint(command, numOfTask++);
                    break;
                default:
                    throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (UnknownCommandException e) {
                Printer.prettyPrint(e.toString());
            }
            input = scanner.nextLine();
        }
        Printer.prettyPrint("Bye (*´▽｀)ノシ. Have a good day!\n");
    }
}
