import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    enum Command {
        LIST, DONE, TODO, DEADLINE, EVENT, DELETE
    }

    private static final String FILE_PATH = "data/duke.txt";
    private static final String LINE_SEPARATOR = "\t____________________________________________________________\n";
    private static final ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        try {
            start();
        } catch (DukeException e) {
            print(e.getMessage());
        }

        while (!(input = scanner.nextLine().trim()).equals("bye")) {
            try {
                handleUserInput(input);
            } catch (DukeException e) {
                print(e.getMessage());
            }
        }

        try {
            exit();
        } catch (DukeException e) {
            print(e.getMessage());
        }

        scanner.close();
    }

    /**
     * Handles user input conditionally based on the command.
     */
    private static void handleUserInput(String input) throws DukeException {
        Command command;
        String[] userInput = input.split(" ", 2);

        if (!userInput[0].equals(userInput[0].toLowerCase())) {
            throw new DukeException(
                    String.format("Please input command with lowercase! Did you mean %s?", userInput[0].toLowerCase()));
        }

        try {
            command = Command.valueOf(userInput[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        switch (command) {
        case LIST:
            printTaskList();
            break;
        case DONE:
            setTaskDone(userInput);
            break;
        case TODO:
            addTodo(userInput);
            break;
        case DEADLINE:
            addDeadline(userInput);
            break;
        case EVENT:
            addEvent(userInput);
            break;
        case DELETE:
            deleteTask(userInput);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Loads task list from file path.
     *
     * @param filePath The file path where the list of tasks reside.
     */
    private static void loadTasks(String filePath) throws FileNotFoundException, DukeException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] taskString = s.nextLine().trim().split(" \\| ");
            Task task;
            switch (taskString[0]) {
            case "T":
                task = new Todo(taskString[2]);
                break;
            case "D":
                task = new Deadline(taskString[2], taskString[3]);
                break;
            case "E":
                task = new Event(taskString[2], taskString[3]);
                break;
            default:
                throw new DukeException("Invalid task type found!");
            }
            if (taskString[1].equals("1")) {
                task.setDone();
            }
            taskList.add(task);
        }
        s.close();
    }

    /**
     * Saves task list to file path.
     *
     * @param filePath The target file path to save the tasks in.
     */
    private static void saveTasks(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        StringBuilder fileData = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            fileData.append(task.toDataFormat());
            if (i != taskList.size() - 1) {
                fileData.append(System.lineSeparator());
            }
        }
        fw.write(fileData.toString());
        fw.close();
    }

    /**
     * Creates file at the specified file path.
     *
     * @param filePath The file path where the file will be created.
     */
    private static void createFile(String filePath) throws IOException {
        File f = new File(filePath);
        f.getParentFile().mkdirs();
        f.createNewFile();
    }

    /**
     * Initialises Duke and prints starting message when Duke is first started.
     */
    private static void start() throws DukeException {
        try {
            loadTasks(FILE_PATH);
        } catch (FileNotFoundException e) {
            try {
                createFile(FILE_PATH);
            } catch (IOException ei) {
                throw new DukeException("Oh no! I could not load and create the task list!");
            }
        }
        print("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints exit message upon exit.
     */
    private static void exit() throws DukeException {
        try {
            saveTasks(FILE_PATH);
        } catch (IOException e) {
            throw new DukeException("Oh no! I was not able to save your tasks.");
        }
        print("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a formatted message with line separator on top and bottom.
     *
     * @param message String to be printed.
     */
    private static void print(String message) {
        String indentedMessage = "\t " + message.replaceAll("\n", "\n\t ") + "\n";
        System.out.println(LINE_SEPARATOR + indentedMessage + LINE_SEPARATOR);
    }

    /**
     * Adds the input to the list of tasks and prints out the input.
     *
     * @param input to be added and printed.
     */
    public static void addTask(Task input) {
        taskList.add(input);
        print(String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.",
                input, taskList.size(), taskList.size() == 1 ? "task" : "tasks"));
    }

    /**
     * Adds a todo task to the list of tasks.
     *
     * @param userInput given by user.
     */
    public static void addTodo(String[] userInput) throws DukeException {
        try {
            addTask(new Todo(userInput[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Todo description cannot be empty");
        }
    }

    /**
     * Adds a deadline task to the list of tasks.
     *
     * @param userInput given by user.
     */
    public static void addDeadline(String[] userInput) throws DukeException {
        try {
            String deadlineDescription = userInput[1].split(" /by ")[0];
            String by = userInput[1].split(" /by ")[1];
            addTask(new Deadline(deadlineDescription, by));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Deadline description and time by cannot be empty");
        }
    }

    /**
     * Adds a event task to the list of tasks.
     *
     * @param userInput given by user.
     */
    public static void addEvent(String[] userInput) throws DukeException {
        try {
            String eventDescription = userInput[1].split(" /at ")[0];
            String by = userInput[1].split(" /at ")[1];
            addTask(new Event(eventDescription, by));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Event description and time at cannot be empty");
        }
    }

    /**
     * Deletes a task given it's index from the list of tasks.
     *
     * @param userInput given by user.
     */
    public static void deleteTask(String[] userInput) throws DukeException {
        try {
            int i = Integer.parseInt(userInput[1]);
            Task task = taskList.get(i - 1);
            taskList.remove(i - 1);
            print(String.format("Noted. I've removed this task:\n  %s\nNow you have %d %s in the list.",
                    task, taskList.size(), taskList.size() == 1
                            ? "task"
                            : "tasks"));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please give a valid number!");
        }
    }

    /**
     * Set i-th task to be done and prints confirmation message.
     *
     * @param userInput given by user.
     */
    private static void setTaskDone(String[] userInput) throws DukeException {
        try {
            int i = Integer.parseInt(userInput[1]);
            Task task = taskList.get(i - 1);
            task.setDone();
            print(String.format("Nice! I've marked this task as done:\n  %s", task.toString()));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please give a valid number!");
        }
    }

    /**
     * Prints out the task list formatted and indented.
     */
    private static void printTaskList() {
        StringBuilder tasksString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            String taskAsString = i == taskList.size() - 1
                    ? String.format("%d.%s", i + 1, taskList.get(i))
                    : String.format("%d.%s\n", i + 1, taskList.get(i));
            tasksString.append(taskAsString);
        }
        print(tasksString.toString());
    }
}
