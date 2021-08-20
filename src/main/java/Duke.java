import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This Duke class implements the functionalities of a chatbot,
 * helping a person to keep track of various things.
 *
 * @author Yeo Jun Wei
 */
public class Duke {

    /** Horizontal line for formatting */
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    /** Greeting message to be displayed when bot starts running */
    private static final String GREETING_MESSAGE = "Hello! I'm JWBot\nWhat can I do for you?";

    /** Goodbye message to be displayed when bot stops running */
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    /** An ArrayList to store tasks entered by the user */
    private static ArrayList<Task> tasksList = new ArrayList<>();

    /** A Scanner instance to obtain user input */
    private static Scanner scanner = new Scanner(System.in);

    /** Path of the file that stores the tasks in the hard disk */
    private static final String FILE_PATH = String.valueOf(Paths.get(
            System.getProperty("user.home"), "data", "dukeFile.txt"));

    public static void main(String[] args) {
        readFile(FILE_PATH);
        greetUser();
        listenToCommands();
    }

    /**
     * Displays the formatted greeting message to the user.
     */
    private static void greetUser() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(GREETING_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the formatted goodbye message to the user.
     */
    private static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the formatted user-entered task to the user,
     * and adds the task to tasksList.
     *
     * @param taskCommand Command entered by the user to add a task.
     * @throws EmptyTaskDescriptionException If task description is empty.
     * @throws TimeNotSpecifiedException If the date/time is not specified.
     * @throws DateTimeParseException If the specified date is in the wrong format.
     */
    private static void addTask(String taskCommand) throws EmptyTaskDescriptionException, TimeNotSpecifiedException,
            DateTimeParseException {
        String[] wordsArr = taskCommand.split(" ");
        String taskType = wordsArr[0];

        int indexOfSpaceChar = taskCommand.indexOf(" ");
        if (indexOfSpaceChar == -1) {
            throw new EmptyTaskDescriptionException(taskType);
        }
        String taskDescription = taskCommand.substring(indexOfSpaceChar + 1);
        if (taskDescription.trim().isEmpty()) {
            throw new EmptyTaskDescriptionException(taskType);
        }

        Task taskToBeAdded;
        if (taskType.equals("todo")) {
            taskToBeAdded = new Todo(taskDescription);
        } else if (taskType.equals("deadline")) {
            if (!taskDescription.contains(" /by ")) {
                throw new TimeNotSpecifiedException(" /by ");
            }
            String[] descriptionArr = taskDescription.split(" /by ");
            String task = descriptionArr[0];
            String deadline = descriptionArr[1];
            if (deadline.trim().isEmpty()) {
                throw new TimeNotSpecifiedException(" /by ");
            }
            LocalDate date = LocalDate.parse(deadline);
            taskToBeAdded = new Deadline(task, date);
        } else {
            if (!taskDescription.contains(" /at ")) {
                throw new TimeNotSpecifiedException(" /at ");
            }
            String[] descriptionArr = taskDescription.split(" /at ");
            String task = descriptionArr[0];
            String timeFrame = descriptionArr[1];
            if (timeFrame.trim().isEmpty()) {
                throw new TimeNotSpecifiedException(" /at ");
            }
            LocalDate date = LocalDate.parse(timeFrame);
            taskToBeAdded = new Event(task, date);
        }
        tasksList.add(taskToBeAdded);

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskToBeAdded);
        if (tasksList.size() == 1) {
            System.out.println("Now you have " + tasksList.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays all previously entered tasks to the user.
     */
    private static void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println((i + 1) + "." + tasksList.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskId The id of the task to be marked as done.
     * @throws TaskIndexOutOfBoundsException If the task index is out of bounds.
     * @throws TaskAlreadyDoneException If the task has previously been marked as done.
     */
    private static void markAsDone(String taskId) throws TaskIndexOutOfBoundsException, TaskAlreadyDoneException {
        int taskPositionInList = Integer.valueOf(taskId) - 1;
        if (taskPositionInList < 0 || (taskPositionInList + 1) > tasksList.size()) {
            throw new TaskIndexOutOfBoundsException();
        }
        Task specifiedTask = tasksList.get(taskPositionInList);
        if (specifiedTask.getStatusIcon().equals("X")) {
            throw new TaskAlreadyDoneException();
        }
        specifiedTask.markAsDone();

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:\n  " + specifiedTask);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Deletes the specified task from the list.
     *
     * @param taskId The id of the task to be deleted.
     * @throws TaskIndexOutOfBoundsException If the task index is out of bounds.
     */
    private static void deleteTask(String taskId) throws TaskIndexOutOfBoundsException {
        int taskPositionInList = Integer.valueOf(taskId) - 1;
        if (taskPositionInList < 0 || (taskPositionInList + 1) > tasksList.size()) {
            throw new TaskIndexOutOfBoundsException();
        }
        Task removedTask = tasksList.remove(taskPositionInList);

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it! I've removed this task:");
        System.out.println("  " + removedTask);
        if (tasksList.size() == 1) {
            System.out.println("Now you have " + tasksList.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Listens to the user-entered commands, and acts accordingly.
     */
    private static void listenToCommands() {
        String command = scanner.nextLine();
        if (command.equals("bye")) {
            scanner.close();
            exit();
        } else {
            if (command.equals("list")) {
                listTasks();
            } else {
                // Splits the string on spaces, and acts accordingly based on the first word
                String[] wordsArr = command.split(" ");
                try {
                    if (wordsArr.length == 0) {
                        throw new UnrecognisedCommandException();
                    } else if (wordsArr[0].equals("done")) {
                        if (wordsArr.length != 2) {
                            throw new MissingTaskIndexException();
                        }
                        markAsDone(wordsArr[1]);
                    } else if (wordsArr[0].equals("delete")) {
                        if (wordsArr.length != 2) {
                            throw new MissingTaskIndexException();
                        }
                        deleteTask(wordsArr[1]);
                    } else if (wordsArr[0].equals("todo") || wordsArr[0].equals("event")
                            || wordsArr[0].equals("deadline")) {
                        addTask(command);
                    } else {
                        throw new UnrecognisedCommandException();
                    }
                    writeToFile(FILE_PATH);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("Please specify the date in this format: yyyy-mm-dd");
                    System.out.println(HORIZONTAL_LINE);
                }
            }
            listenToCommands();
        }
    }

    /**
     * Loads the tasks from the specified file.
     *
     * @param path The path to the file that stores the tasks.
     */
    private static void readFile(String path) {
        File dukeFile = new File(path);
        try {
            Scanner fileScanner = new Scanner(dukeFile);
            while (fileScanner.hasNext()) {
                String currentLine = fileScanner.nextLine();

                String[] taskStatus = currentLine.split(Pattern.quote(" | "));
                String taskType = taskStatus[0];
                String taskProgress = taskStatus[1];
                String taskDescription = taskStatus[2];
                Task taskToAdd;

                if (taskType.equals("T")) {
                    taskToAdd = new Todo(taskDescription);
                } else if (taskType.equals("D")) {
                    taskToAdd = new Deadline(taskDescription, LocalDate.parse(taskStatus[3]));
                } else {
                    taskToAdd = new Event(taskDescription, LocalDate.parse(taskStatus[3]));
                }

                if (taskProgress.equals("1")) {
                    taskToAdd.markAsDone();
                }

                tasksList.add(taskToAdd);
            }
        } catch (FileNotFoundException e) {
            // Checking if directory exists
            java.nio.file.Path directoryPath = Paths.get(System.getProperty("user.home"), "data");
            if (!Files.exists(directoryPath)) {
                File directory = new File(String.valueOf(String.valueOf(directoryPath)));
                directory.mkdir();
            }
        }
    }

    /**
     * Saves the tasks in the specified file.
     *
     * @param path The path to the file that stores the tasks.
     */
    private static void writeToFile(String path) {
        String textToAdd = "";

        for (Task task : tasksList) {
            String currentLine = "";

            if (task instanceof Todo) {
                currentLine += "T | ";
            } else if (task instanceof Deadline) {
                currentLine += "D | ";
            } else {
                currentLine += "E | ";
            }

            if (task.isDone) {
                currentLine += "1 | ";
            } else {
                currentLine += "0 | ";
            }

            currentLine += task.description + " | ";

            if (task instanceof Deadline) {
                currentLine += ((Deadline) task).by.toString();
            } else if (task instanceof Event) {
                currentLine += ((Event) task).at.toString();
            }

            textToAdd += currentLine + System.lineSeparator();
        }

        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(textToAdd);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to write to ./data/dukeFile.txt");
        }
    }
 }
