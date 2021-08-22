import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Duke class encapsulates the Duke project's chat-bot for CS2103T individual project 1.
 *
 * @author Chesterwongz
 */
public class Duke {
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static final String FILEPATH = "DukeList.txt";
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";

    /**
     * Prints the Duke logo.
     */
    private static void logo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        echo("Hello from\n" + logo);
    }

    /**
     * Prints our greeting.
     */
    private static void greet() {
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?";
        echo(greeting);
    }

    /**
     * Prints our goodbye.
     */
    private static void bye() {
        String bye = "Bye! Hope to see you again soon!";
        echo(bye);
    }

    /**
     * Adds a task to the taskList.
     *
     * @param task the task to be added to the list.
     */
    private static void addToList(Task task) {
        taskList.add(task);
        writeFile();
        echo("Got it. I've added this task:\n  "
                + task + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Adds a To-do task to the taskList.
     *
     * @param input The String array containing the to-do description at index 1.
     * @throws DukeException The exception to be thrown when input is not as expected.
     */
    private static void addToDo(String[] input) throws DukeException {
        if (input.length > 1) {
            addToList(new ToDo(input[1]));
        } else {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty ☹");
        }
    }

    /**
     * Adds a deadline task to the taskList.
     *
     * @param input The String array containing the deadline description at index 1.
     * @throws DukeException The exception to be thrown when input is not as expected.
     */
    private static void addDeadline(String[] input) throws DukeException {
        String formatErrorMsg = "OOPS!!! Please add the deadline with the right format:\n"
                + "  deadline <description> /by <" + DATE_TIME_FORMAT + ">";
        if (input.length > 1) {
            String[] deadline = input[1].split("/", 2);
            if (deadline.length > 1 && deadline[1].length() > 3) {
                try {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
                    addToList(new Deadline(deadline[0], LocalDateTime.parse(deadline[1].substring(3), format)));
                } catch (DateTimeParseException e) {
                    throw new DukeException(formatErrorMsg);
                }
            } else {
                throw new DukeException(formatErrorMsg);
            }
        } else {
            throw new DukeException(formatErrorMsg);
        }
    }

    /**
     * Adds an event task to the taskList.
     *
     * @param input The String array containing the event description at index 1.
     * @throws DukeException The exception to be thrown when input is not as expected.
     */
    private static void addEvent(String[] input) throws DukeException {
        String formatErrorMsg = "OOPS!!! Please add the event with the right format:\n"
                + "  event <description> /at <" + DATE_TIME_FORMAT + ">";
        if (input.length > 1) {
            String[] event = input[1].split("/", 2);
            if (event.length > 1 && event[1].length() > 3) {
                try {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
                    addToList(new Event(event[0], LocalDateTime.parse(event[1].substring(3), format)));
                } catch (DateTimeParseException e) {
                    throw new DukeException(formatErrorMsg);
                }
            } else {
                throw new DukeException(formatErrorMsg);
            }
        } else {
            throw new DukeException(formatErrorMsg);
        }
    }

    /**
     * Marks task as done in taskList.
     *
     * @param input The String array with the index of the task to be done in input[1], if it is there.
     * @throws DukeException The exception to be thrown when input is not as expected.
     */
    private static void doTask(String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("OOPS!!! Please input the task number to be marked as done ☹");
        }
        try {
            int taskID = Integer.parseInt(input[1]) - 1;
            if (0 <= taskID && taskID < taskList.size()) {
                taskList.get(taskID).markAsDone();
                echo("Nice! I've marked this task as done:\n  "
                        + taskList.get(taskID).toString());
            } else {
                throw new DukeException("OOPS!!! Please enter a valid task number ☹");

            }
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task number ☹");
        }
    }

    /**
     * Removes a task from the task list.
     *
     * @param input The String array with the index of the task to be removed in input[1], if it is there.
     * @throws DukeException The exception to be thrown when input is not as expected.
     */
    private static void deleteTask(String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("OOPS!!! Please input the task number to be removed ☹");
        }
        try {
            int index = Integer.parseInt(input[1]) - 1;
            if (0 <= index && index < taskList.size()) {
                echo("Got it. I've removed this task:\n  "
                        + taskList.remove(index) + "\nNow you have " + taskList.size() + " tasks in the list.");
                writeFile();
            } else {
                throw new DukeException("OOPS!!! Please enter a valid task number ☹");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task number ☹");
        }
    }

    /**
     * Frames the message with underscore lines and prints it.
     *
     * @param msg The String we want to frame.
     */
    private static void echo(String msg) {
        String line = "__________________________________________________________";
        System.out.println(line);
        System.out.println(msg);
        System.out.println(line);
        System.out.println();
    }

    /**
     * Prints out the todolist.
     */
    private static void printList() {
        String line = "__________________________________________________________";
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : taskList) {
            System.out.println((index++) + "." + task);
        }
        System.out.println(line);
        System.out.println();
    }

    /**
     * Reads and loads the taskList from txt file. Creates new file if it does not exist.
     */
    private static void readFile() {
        try {
            File myObj = new File(FILEPATH);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                switch (data.charAt(0)) {
                case 'T':
                    Task toDoTask = new ToDo(data.substring(4));
                    if (data.charAt(2) == 'X') {
                        toDoTask.markAsDone();
                    }
                    taskList.add(toDoTask);
                    break;
                case 'D':
                    readDeadline(data);
                    break;
                case 'E':
                    readEvent(data);
                    break;
                default:
                    // Do nothing and continue to scan the next line.
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            createFile();
        }
    }

    private static void readEvent(String data) {
        try {
            String[] splitEvent = data.split("\\|", 4);
            if (splitEvent.length == 4) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
                LocalDateTime at = LocalDateTime.parse(splitEvent[3], format);
                Task eventTask = new Event(splitEvent[2], at);
                if (data.charAt(2) == 'X') {
                    eventTask.markAsDone();
                }
                taskList.add(eventTask);
            } // Else invalid data, do nothing.
        } catch (DateTimeParseException e) {
            // Invalid data, do nothing.
            System.out.println("Date format wrong");
        }
    }
    private static void readDeadline(String data) {
        try {
            String[] splitDeadline = data.split("\\|", 4);
            if (splitDeadline.length == 4) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
                LocalDateTime by = LocalDateTime.parse(splitDeadline[3], format);
                Task deadlineTask = new Deadline(splitDeadline[2], by);
                if (data.charAt(2) == 'X') {
                    deadlineTask.markAsDone();
                }
                taskList.add(deadlineTask);
            } // Else, invalid data, do nothing.
        } catch (DateTimeParseException e) {
            // Invalid data, do nothing.
            System.out.println("Date format wrong");
        }
    }
    /**
     * Creates a file. Called in readFile().
     */
    private static void createFile() {
        try {
            File myObj = new File(FILEPATH);
            if (myObj.createNewFile()) {
                echo("Successfully created a save file!");
            } else {
                echo("OOPS!!! An error occurred when creating a file!");
            }
        } catch (IOException e) {
            echo("OOPS!!! An error occurred when creating a file!");
            e.printStackTrace();
        }
    }

    /**
     * Overwrites task list in txt file with updated list.
     */
    private static void writeFile() {
        try {
            FileWriter myWriter = new FileWriter(FILEPATH); // Overwrite mode.
            StringBuilder fileContent = new StringBuilder();
            for (Task task : taskList) {
                fileContent.append(task.toData()).append("\n");
            }
            myWriter.write(fileContent.toString());
            myWriter.close();
        } catch (IOException e) {
            echo("OOPS!!! An error occurred when writing a file!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFile();
        logo();
        greet();
        Scanner sc = new Scanner(System.in);
        boolean isExited = false;
        while (!isExited) {
            try {
                String input = sc.nextLine();
                switch (input) {
                case "bye":
                    isExited = true;
                    bye();
                    break;
                case "list":
                    printList();
                    break;
                default:
                    String[] split = input.split(" ", 2);
                    switch (split[0]) {
                    case "done":
                        doTask(split);
                        break;
                    case "todo":
                        addToDo(split);
                        break;
                    case "deadline":
                        addDeadline(split);
                        break;
                    case "event":
                        addEvent(split);
                        break;
                    case "delete":
                        deleteTask(split);
                        break;
                    default:
                        echo("OOPS!!! I'm sorry, but I don't know what that means ☹ ");
                    }
                }
            } catch (DukeException e) {
                echo(e.getMessage());
            }
        }
    }
}
