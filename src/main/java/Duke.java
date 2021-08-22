import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the Duke program. Manages tasks based on commands received.
 */
public class Duke {
    /** Greeting message to be printed when the program starts */
    private static final String GREETING_MESSAGE =
            "____________________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________________\n";
    /** Message to be printed when the program exits */
    private static final String EXITING_MESSAGE =
            "____________________________________________________________\n" +
            "Bye. Hope to see you again soon!\n" +
            "____________________________________________________________";
    /** Path of the current folder as a string */
    private static final String DIRECTORY_PATH = System.getProperty("user.dir");
    /** Path of file containing data saved */
    private static File data;
    /** List of tasks */
    private static ArrayList<Task> tasks = new ArrayList<>();
    /** Whether the Duke program is running */
    private boolean isRunning;
    /** Scanner used to read commands */
    private Scanner input;

    /**
     * Constructor of the class 'Duke'.
     */
    public Duke() {
        Duke.data = Paths.get(Duke.DIRECTORY_PATH, "data", "duke.txt").toFile();
        Duke.readFile();
        this.isRunning = true;
        System.out.println(Duke.GREETING_MESSAGE);
        this.input = new Scanner(System.in);
    }

    /**
     * Reads the data in the file. If the file doesn't exist, create it.
     */
    private static void readFile() {
        try {
            Scanner fileScanner = new Scanner(Duke.data);
            while (fileScanner.hasNextLine()) {
                String task = fileScanner.nextLine();
                Duke.readData(task);
            }
            fileScanner.close();
        } catch (FileNotFoundException fileNotFoundException) { // if file doesn't exist, create it.
            try {
                Duke.data.createNewFile();
            } catch (IOException ioException) { // if directory doesn't exist, create it.
                File directory = Paths.get(Duke.DIRECTORY_PATH, "data").toFile();
                directory.mkdirs();
                Duke.readFile(); // run this method again to create a file.
            }
        }
    }

    /**
     * Reads a line of data, creates a task and adds it to the task list.
     *
     * @param line A line of data.
     */
    private static void readData(String line) {
        String[] splitted = line.split(" / ");
        Task task;
        if (splitted[0].equals("T")) { // a todo task
            task = new ToDo(splitted[2]);
        } else if (splitted[0].equals("D")) { // a task with deadline
            task = new Deadline(splitted[2], splitted[3]);
        } else if (splitted[0].equals("E")) { // an event
            task = new Event(splitted[2], splitted[3]);
        } else {
            task = new Task(splitted[2]);
        }
        if (splitted[1].equals("1")) {
            task.markAsDone();
        }
        Duke.tasks.add(task); // add to task list.
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The new task.
     */
    public static void addToList(Task task){
        Duke.tasks.add(task); // add to task list.
        try {
            FileWriter fileWriter = new FileWriter(Duke.data, true);
            fileWriter.append(task.toFileFormatString()); // write to file.
            fileWriter.close();
        } catch (IOException ioException) {
            Duke.readFile();
            DukeException dukeException = new DukeException(
                    "☹ OOPS!!! The file cannot be found. A new file has been created, please try again!");
            System.out.println(dukeException);
        }
    }

    /**
     * Removes a task from the task list.
     *
     * @param task The task to be removed.
     */
    public static void removeFromList(Task task) {
        try {
            List<String> lines = Files.readAllLines(Duke.data.toPath());
            FileWriter fileWriter = new FileWriter(Duke.data);
            int index = Duke.tasks.indexOf(task);
            for (int i = 0; i < Duke.getNumOfTasks(); i++) { // remove this task from file.
                if (i != index) {
                    fileWriter.append(lines.get(i) + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException ioException) {
            Duke.readFile();
            DukeException dukeException = new DukeException(
                    "☹ OOPS!!! The file cannot be found. A new file has been created, please try again!");
            System.out.println(dukeException);
        }
        Duke.tasks.remove(task); // remove from task list.
    }

    /**
     * Returns a list of string, which is a copy of `tasks` list.
     *
     * @return A copy of tasks list.
     */
    public static ArrayList<Task> getTasks() {
        ArrayList<Task> copy = new ArrayList<>();
        int len = Duke.getNumOfTasks();
        for (int i = 0; i < len; i++) {
            copy.add(Duke.tasks.get(i));
        }
        return copy;
    }

    /**
     * Returns the number of tasks added.
     *
     * @return Number of tasks.
     */
    public static int getNumOfTasks() {
        return Duke.tasks.size();
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
     *
     * @throws DukeException If command is invalid.
     */
    private void readCommand() throws DukeException {
        String command = this.input.nextLine().trim(); // read the command
        String[] splitted = command.split(" ", 2);

        if (command.equals("bye")) {
            System.out.println(Duke.EXITING_MESSAGE);
            this.isRunning = false;
        } else if (command.equals("list")) {
            processCommand(new GetListProcessor());
        } else if (splitted[0].equals("done")) {
            try {
                int index = Integer.parseInt(splitted[1]) - 1;
                processCommand(new TaskDoneProcessor(Duke.tasks.get(index)));
            } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The task number is invalid.");
            }
        } else if (splitted[0].equals("delete")) {
            try {
                int index = Integer.parseInt(splitted[1]) - 1;
                processCommand(new DeleteATaskProcessor(Duke.tasks.get(index)));
            } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The task number is invalid.");
            }
        } else if (splitted[0].equals("todo")) {
            if (splitted.length >= 2) {
                processCommand(new AddATaskProcessor(new ToDo(splitted[1])));
            } else {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (splitted[0].equals("deadline")) {
            if (splitted.length >= 2) {
                String[] information = splitted[1].split("/by");
                if (information.length == 2) {
                    processCommand(new AddATaskProcessor(new Deadline(information[0], information[1])));
                } else if (information.length < 2) {
                    throw new DukeException("☹ OOPS!!! The time of a deadline cannot be empty.");
                } else {
                    throw new DukeException("☹ OOPS!!! A deadline cannot occupy multiple time slots.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
        } else if (splitted[0].equals("event")) {
            if (splitted.length >= 2) {
                String[] information = splitted[1].split("/at");
                if (information.length == 2) {
                    processCommand(new AddATaskProcessor(new Event(information[0], information[1])));
                } else if (information.length < 2) {
                    throw new DukeException("☹ OOPS!!! The time of an event cannot be empty.");
                } else {
                    throw new DukeException("☹ OOPS!!! An event cannot occupy multiple time slots.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Runs the Duke program, prints out messages based on commands received.
     *
     * @param args The command line parameters.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        while (duke.isRunning) {
            try {
                duke.readCommand();
            } catch (DukeException dukeException) {
                System.out.println(dukeException);
                continue;
            }
        }
    }
}
