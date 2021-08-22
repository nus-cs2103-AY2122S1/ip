import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Chng Zi Hao
 */
public class Duke {
    // Constants for the program
    static final String DIVIDER = "--------------------------------------------------------------------------------";
    static final String PROMPT = "Enter Command: ";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String WELCOMEMESSAGE = String.format("%s\n%s\n%s\n%s\n%s", DIVIDER, LOGO,
            "Hello! I'm Duke :)\nWhat can I do for you? (Type 'help' to see what I can do!)", DIVIDER, PROMPT);
    private static final String GOODBYEMESSAGE = String.format("%s\n%s\n%s", DIVIDER,
            "Bye :< Hope to see you again soon!", DIVIDER);
    private static final String DIRECTORY = "./data/";
    private static final String FILEPATH = DIRECTORY + "data.txt";

    private static TaskList taskList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Set up files and intialise TaskList
        File file = new File(FILEPATH);
        if (file.exists()) {
            try {
                taskList = new TaskList(retrieveData(file));
            } catch (FileNotFoundException | DukeException e) {
                formatPrint(e.getMessage());
            }
        } else {
            try {
                System.out.println("data.txt file does not exist. Creating new file...");
                File directory = new File(DIRECTORY);
                if (!directory.exists()) {
                    directory.mkdir();
                }
                file.createNewFile();
                System.out.println("data.txt created successfully! :>");
                taskList = new TaskList();
            } catch (IOException e) {
                formatPrint(e.getMessage());
            }
        }

        // Welcome message
        System.out.println(WELCOMEMESSAGE);

        // Logic of program based on user input
        String input = sc.nextLine();
        Command command = Command.valueOfLabel(input.split(" ")[0]);
        while (command != Command.BYE) {
            try {
                switch (command) {
                case LIST:
                    taskList.printTaskList();
                    break;
                case DONE:
                    int doneIndex = extractIndex(input, Command.DONE);
                    formatPrint(taskList.markTaskDone(doneIndex));
                    break;
                case HELP:
                    printHelp();
                    break;
                case TODO:
                    String toDoInfo = extractInfo(input, Command.TODO);
                    formatPrint(taskList.addTask(toDoInfo, Command.TODO));
                    break;
                case DEADLINE:
                    String deadlineInfo = extractInfo(input, Command.DEADLINE);
                    formatPrint(taskList.addTask(deadlineInfo, Command.DEADLINE));
                    break;
                case EVENT:
                    String eventInfo = extractInfo(input, Command.EVENT);
                    formatPrint(taskList.addTask(eventInfo, Command.EVENT));
                    break;
                case DELETE:
                    int deleteIndex = extractIndex(input, Command.DELETE);
                    formatPrint(taskList.deleteTask(deleteIndex));
                    break;
                default:
                    throw new DukeException("Invalid command @_@ Try typing 'help' to see my list of commands!");
                }
            } catch (DukeException e) {
                formatPrint(e.getMessage());
            } finally {
                System.out.println(PROMPT);
                input = sc.nextLine();
                command = Command.valueOfLabel(input.split(" ")[0]);
            }
        }

        // Goodbye message for user and program stops
        System.out.println(GOODBYEMESSAGE);
    }

    /**
     * Formats the input and prints it in a formatted version.
     *
     * @param input Input to be printed.
     */
    public static void formatPrint(String input) {
        System.out.println(DIVIDER);
        System.out.println(input);
        System.out.println(DIVIDER);
    }

    /**
     * Prints the help page for users.
     */
    public static void printHelp() {
        System.out.println(DIVIDER);
        System.out.println("Here is the list of my available commands!\n"
                + "1. todo [description] - Adds a ToDo task to task list\n"
                + "2. deadline [description] /by [deadline] - Adds a Deadline to task list\n"
                + "3. event [description] /at [event duration] - Adds a Event to task list\n"
                + "4. list - Display list of items you have added\n"
                + "5. done [index of completed task] - Marks specified tasks as completed\n"
                + "6. delete [index of task to be deleted] - Deletes specified task\n"
                + "7. bye - End the program");
        System.out.println(DIVIDER);
    }

    /**
     * Extract out the information given in user input by separating out command.
     *
     * @param input   User raw input.
     * @param command The specific command given by user.
     * @return String containing information that we need.
     * @throws DukeException Prevent empty descriptions.
     */
    public static String extractInfo(String input, Command command) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length < 2) {
            throw new DukeException(String.format("Error: OOPS!!! The description of %s cannot be empty.",
                    command.label));
        }
        return info[1];
    }

    /**
     * Extract out the index given in user input by separating out command.
     *
     * @param input   User raw input.
     * @param command The specific command given by user.
     * @return int of the task user wants to select.
     * @throws DukeException Prevent empty indexes.
     */
    public static int extractIndex(String input, Command command) throws DukeException {
        String[] info = input.split(" ", 2);
        if (info.length < 2 || info[1].equals("")) {
            throw new DukeException(String.format("Error: OOPS!!! The index argument for %s cannot be empty.",
                    command.label));
        }
        return Integer.parseInt(info[1]) - 1;
    }

    /**
     * Returns an ArrayList containing all the tasks in the data.txt file.
     * If data.txt is empty, an Empty ArrayList is returned.
     *
     * @param file File Object for data.txt.
     * @return ArrayList containing tasks.
     * @throws FileNotFoundException If file is missing.
     * @throws DukeException If file is corrupted.
     */
    public static ArrayList<Task> retrieveData(File file) throws FileNotFoundException, DukeException {
        Scanner fileReader = new Scanner(file);
        ArrayList<Task> taskList = new ArrayList<>();
        if (file.length() == 0) {
            System.out.println("No tasks to load!");
            return taskList;
        }
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            String[] dataBreakdown = data.split(" \\| ");
            Task task;
            switch (dataBreakdown[0]) {
            case "T":
                task = new ToDo(dataBreakdown[2]);
                taskList.add(task);
                break;
            case "D":
                task = new Deadline(dataBreakdown[2], dataBreakdown[3]);
                taskList.add(task);
                break;
            case "E":
                task = new Event(dataBreakdown[2], dataBreakdown[3]);
                taskList.add(task);
                break;
            default:
                throw new DukeException("File has been corrupted @_@");
            }
            if (dataBreakdown[1].equals("1")) {
                task.markDone();
            }
        }
        fileReader.close();
        System.out.println("YAY! File has been loaded Successfully! :>");
        return taskList;
    }

    /**
     * Saves data into data.txt files after changes have been made by user.
     *
     * @param data Formatted TaskList to be stored in data.txt.
     */
    public static void save(String data) {
        try {
            FileWriter fileWriter = new FileWriter("./data/data.txt");
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            formatPrint(e.getMessage());
        }
    }
}
