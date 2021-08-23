import java.io.IOException;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class runs a personal assistant chatbot that helps a person keep track of various tasks.
 * Commands for the bot are: list, mark, delete, bye, event, deadline, and todo.
 * Unrecognised command will be met with a prompt to enter a recognised one instead.
 */
public class Duke {

    // To represent line separators in the chat.
    private static final String LINE_HORIZONTAL =
            "___________________________________________________________";
    // To store the tasks to be done.
    private static ArrayList<Task> taskList = new ArrayList<>();
    // The possible commands for the bot.
    private enum Command {
        TODO, DEADLINE, EVENT, LIST, MARK, DELETE, BYE
    }

    /**
     * Initializes the chatbot.
     */
    public static void main(String[] args) {
        // Prints initial message as prompt.
        System.out.printf("Greetings! This is Elsa.\n");

        // Loads list data if saved in hard drive before.
        try {
            loadListData();
        } catch (IOException e) {
            System.out.printf("%s\nIt seems there was an error reading the saved list.\n"
                    + "Please ensure a duke.txt file is present in /data.\n"
                    + "%s\n",
                    LINE_HORIZONTAL, LINE_HORIZONTAL);
        }

        // Prints the loaded list.
        if (taskList.size() > 0) {
            printList();
        } else {
            System.out.printf("%s\nIt seems like there are no items in the saved list.\n"
                    + "Start adding tasks to save your list!\n"
                    + "%s\n",
                    LINE_HORIZONTAL, LINE_HORIZONTAL);
        }

        // Initializes scanner to take input from user.
        Scanner scanner = new Scanner(System.in);
        String input;

        // Tracks if the user is done with the assistant.
        boolean exit = false;

        // Takes in input and performs actions accordingly.
        while(!(exit)) {
            input = scanner.nextLine().trim();
            try {
                Command commandToExecute = interpretCommand(input);
                switch (commandToExecute) {
                case LIST:
                    printList();
                    break;
                case MARK:
                    int toMark = Integer.parseInt(input.substring(5));
                    markTaskAsDone(toMark);
                    break;
                case DELETE:
                    int toDelete = Integer.parseInt(input.substring(7));
                    deleteTask(toDelete);
                    break;
                case TODO:
                    addToDo(input);
                    addTaskToFile();
                    break;
                case DEADLINE:
                    addDeadline(input);
                    addTaskToFile();
                    break;
                case EVENT:
                    addEvent(input);
                    addTaskToFile();
                    break;
                case BYE:
                    printBye();
                    exit = true;
                    break;
                }
            } catch (InvalidCommandException e) {
                System.out.printf("%s\n"
                        + "I don't quite understand what that means.\n"
                        + "Could you please rephrase that?\n"
                        + "%s\n", LINE_HORIZONTAL, LINE_HORIZONTAL);
            } catch (MissingTaskException e) {
                System.out.printf("%s\n"
                        + "You might have missed out on the task.\n"
                        + "Could you please enter it again?\n"
                        + "%s\n", LINE_HORIZONTAL, LINE_HORIZONTAL);
            } catch (MissingTimeException e) {
                System.out.printf("%s\n"
                        + "You might have missed out on the time.\n"
                        + "Could you please enter it again?\n"
                        + "%s\n", LINE_HORIZONTAL, LINE_HORIZONTAL);
            } catch (InvalidTaskException e) {
                System.out.printf("%s\n"
                        + "You might have mistyped the task number.\n"
                        + "Please ensure task number is between 1 and %d.\n"
                        + "%s\n", LINE_HORIZONTAL, taskList.size(), LINE_HORIZONTAL);
            } catch (IOException e) {
                System.out.printf("%s\n"
                        + "There is a problem with saving the list to the file.\n"
                        + "Please ensure a duke.txt file is present in /data.\n"
                        + "%s\n", LINE_HORIZONTAL, LINE_HORIZONTAL);
            } catch (DateTimeParseException e) {
                System.out.printf("%s\n" +
                        "Your date might not be in the correct format.\n" +
                        "Please ensure it is in the YYYY-MM-DD format.\n" +
                        "%s\n", LINE_HORIZONTAL, LINE_HORIZONTAL);
            }
        }
        scanner.close();
    }

    /**
     * Interprets the command entered by user and returns its enum.
     *
     * @param input The input entered by user.
     * @return The command enum in the input.
     * @throws InvalidCommandException If command cannot be found.
     */
    public static Command interpretCommand(String input) throws InvalidCommandException {
        if (input.equalsIgnoreCase("list")) {
            return Command.valueOf("LIST");
        } else if (input.toLowerCase().indexOf("done") != -1) {
            return Command.valueOf("MARK");
        } else if (input.toLowerCase().indexOf("delete") != -1) {
            return Command.valueOf("DELETE");
        } else if (input.toLowerCase().indexOf("todo") != -1) {
            return Command.valueOf("TODO");
        } else if (input.toLowerCase().indexOf("deadline") != -1) {
            return Command.valueOf("DEADLINE");
        } else if (input.toLowerCase().indexOf("event") != -1) {
            return Command.valueOf("EVENT");
        } else if (input.toLowerCase().equals("bye")) {
            return Command.valueOf("BYE");
        } else {
            throw new InvalidCommandException("Invalid command!");
        }
    }

    /**
     * Prints out every task in the list.
     */
    public static void printList() {
        System.out.println(LINE_HORIZONTAL);

        if (taskList.size() == 0) {
            System.out.printf("There are no tasks to be done! Hooray!\n");
        } else {
            System.out.println("Here is your list of tasks:");

            for (int i = 0; i < taskList.size(); i++) {
                String taskName = taskList.get(i).toString();
                System.out.printf("%d.%s\n", i + 1, taskName);
            }
        }

        System.out.println(LINE_HORIZONTAL);
    }

    /**
     * Marks the corresponding task as done and prints confirmation.
     *
     * @param toMark The index of the task to be marked.
     * @throws InvalidTaskException If task cannot be found in user's list.
     * @throws IOException If there are problems with writing into the file.
     *
     */
    public static void markTaskAsDone(int toMark) throws InvalidTaskException, IOException {
        if (toMark <= 0 || toMark > taskList.size()) {
            throw new InvalidTaskException("Task is not found");
        }

        taskList.get(toMark - 1).markAsDone();
        updateTaskToFile(toMark);

        System.out.printf("%s\n"
                + "Great job!\n"
                + "The following task is marked as done:\n"
                +  "\t%s\n"
                +  "%s\n",
                LINE_HORIZONTAL, taskList.get(toMark - 1).toString(), LINE_HORIZONTAL);
    }

    /**
     * Deletes the corresponding task as done and prints confirmation.
     *
     * @param toDelete The index of the task to be deleted.
     * @throws InvalidTaskException If task cannot be found in list.
     */
    public static void deleteTask(int toDelete) throws InvalidTaskException, IOException {
        if (toDelete <= 0 || toDelete > taskList.size()) {
            throw new InvalidTaskException("Task is not found");
        }

        System.out.printf("%s\n"
                + "Done!\n"
                + "The following task has been removed:\n"
                + "\t%s\n"
                + "You now have %d "
                + (taskList.size() - 1 == 1 ? "task" : "tasks")
                + " left in your list!\n"
                + "%s\n",
                LINE_HORIZONTAL, taskList.get(toDelete - 1).toString(), taskList.size() - 1, LINE_HORIZONTAL);

        taskList.remove(toDelete - 1);
        deleteTaskFromFile(toDelete);
    }

    /**
     * Adds the to do entered by the user to the list and prints it.
     *
     * @param input The to do inputted by the user.
     * @throws MissingTaskException If task is unspecified after command.
     */
    public static void addToDo(String input) throws MissingTaskException {
        if (input.length() < 6) {
            throw new MissingTaskException("Task not found.");
        }

        String taskName = input.substring(5);
        taskList.add(new ToDo(taskName));
        printTaskAdded(taskName);
    }

    /**
     * Adds the deadline entered by the user to the list and prints it.
     *
     * @param input The deadline inputted by the user.
     * @throws MissingTaskException If task is unspecified after command.
     * @throws MissingTimeException If time is unspecified after command.
     */
    public static void addDeadline(String input)
            throws MissingTaskException, MissingTimeException, DateTimeParseException {
        int separation = input.indexOf(" /by ");

        if (separation == -1) {
            throw new MissingTimeException("Time not found");
        }

        if (separation < 11) {
            throw new MissingTaskException("Task not found");
        }

        String taskName = input.substring(9, separation);

        if (input.substring(separation + 6).length() < 1) {
            throw new MissingTimeException("Time not found");
        }

        String timeFull = input.substring(separation + 5);
        int timeFullSeparation;

        if ((timeFullSeparation = timeFull.indexOf(" ")) != -1) {
            String time = timeFull.substring(timeFullSeparation + 1);
            LocalDate date = LocalDate.parse(timeFull.substring(0, timeFullSeparation));
            taskList.add(new Deadline(taskName, date, time));
        } else {
            LocalDate date = LocalDate.parse(timeFull);
            taskList.add(new Deadline(taskName, date));
        }

        printTaskAdded(taskName);
    }

    /**
     * Adds the event entered by the user to the list and prints it.
     *
     * @param input The event inputted by the user.
     * @throws MissingTaskException If task is unspecified after command.
     * @throws MissingTimeException If time is unspecified after command.
     */
    public static void addEvent(String input)
            throws MissingTaskException, MissingTimeException, DateTimeParseException {
        int separation = input.indexOf(" /at ");

        if (separation == -1) {
            throw new MissingTimeException("Time not found");
        }

        if (separation < 8) {
            throw new MissingTaskException("Task not found");
        }
        String taskName = input.substring(6, separation);

        if (input.substring(separation + 6).length() < 1) {
            throw new MissingTimeException("Time not found");
        }

        String timeFull = input.substring(separation + 5);
        int timeFullSeparation;

        if ((timeFullSeparation = timeFull.indexOf(" ")) != -1) {
            String time = timeFull.substring(timeFullSeparation + 1);
            LocalDate date = LocalDate.parse(timeFull.substring(0, timeFullSeparation));
            taskList.add(new Event(taskName, date, time));
        } else {
            LocalDate date = LocalDate.parse(timeFull);
            taskList.add(new Event(taskName, date));
        }

        printTaskAdded(taskName);
    }

    /**
     * Prints the confirmation of the addition of the last task.
     *
     * @param taskName The name of the task just added.
     */
    public static void printTaskAdded(String taskName) {
        System.out.printf("%s\n"
                + "Gotcha! The following task has been added:\n"
                + "\t%s\n"
                + "You now have %d "
                + (taskList.size() == 1 ? "task" : "tasks")
                + " in your list!\n"
                + "%s\n",
                LINE_HORIZONTAL, taskName,
                taskList.size(), LINE_HORIZONTAL);
    }

    /**
     * Prints the farewell message for the user.
     */
    public static void printBye() {
        System.out.printf("%s\n"
                + "Goodbye. Hope to see you again soon!\n",
                LINE_HORIZONTAL);
    }

    /**
     * Saves the last element in the user's list to the .txt file.
     *
     * @throws IOException If there are problems with writing into the file.
     */
    public static void addTaskToFile() throws IOException {
        String fileName = "./data/duke_list_data.txt";

        // Writes the data into the file.
        FileWriter fw = new FileWriter(fileName, true);
        String textToAdd = "";
        String taskName = taskList.get(taskList.size() - 1).toString();
        textToAdd = textToAdd + taskName + "\n";
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Updates a task in the user's list to be marked as done in the .txt file.
     *
     * @param toMark The index of the task to be marked.
     * @throws IOException If there are problems with writing into the file.
     */
    public static void updateTaskToFile(int toMark) throws IOException {
        String fileName = "./data/duke_list_data.txt";

        // Updates the data into the file.
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        String toUpdate = "";
        int lineNumber = 1;

        while (sc.hasNext()) {
            if (lineNumber == toMark) {
                String currentLine = sc.nextLine();
                currentLine = currentLine.substring(0, 4) + 'X' + currentLine.substring(5);
                toUpdate = toUpdate + currentLine + "\n";
            } else {
                toUpdate = toUpdate + sc.nextLine() + "\n";
            }
            lineNumber++;
        }

        FileWriter fw = new FileWriter(fileName);
        fw.write(toUpdate);
        fw.close();
    }

    /**
     * Deletes a task from the .txt file.
     *
     * @param toDelete The index of the task to be marked.
     * @throws IOException If there are problems with writing into the file.
     */
    public static void deleteTaskFromFile(int toDelete) throws IOException {
        String fileName = "./data/duke_list_data.txt";

        // Updates the data into the file.
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        String toUpdate = "";
        int lineNumber = 1;

        while (sc.hasNext()) {
            if (lineNumber == toDelete) {
                sc.nextLine();
            } else {
                toUpdate = toUpdate + sc.nextLine() + "\n";
            }
            lineNumber++;
        }

        FileWriter fw = new FileWriter(fileName);
        fw.write(toUpdate);
        fw.close();
    }

    /**
     * Imports the data from the hard disk to the user's list.
     *
     * @throws IOException If there are problems with writing into the file.
     */
    public static void loadListData() throws IOException {

        String fileName = "./data/duke_list_data.txt";

        // Creates directory if it does not already exist.
        String directoryName = "./data";
        Path path = Paths.get(directoryName);
        Files.createDirectories(path);

        // Creates file if it does not already exist.
        File file = new File(fileName);
        file.createNewFile();

        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            addTaskToList(sc.nextLine());
        }
    }

    /**
     * Adds the data from a line in the .txt file to the task list.
     *
     * @param lineToAdd The line of the file to add.
     */
    public static void addTaskToList(String lineToAdd) {
        String taskDetails = lineToAdd.substring(7);

        if (lineToAdd.charAt(1) == 'T') {
            Task currentTask = new ToDo(taskDetails);
            taskList.add(currentTask);
            if (lineToAdd.charAt(4) == 'X') {
                currentTask.markAsDone();
            }
        } else if (lineToAdd.charAt(1) == 'D') {
            int separator = taskDetails.indexOf(" (by: ");
            String taskName = taskDetails.substring(0, separator);
            String timeFull = taskDetails.substring(separator + 6);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            Task currentTask;

            if (timeFull.length() > 12) {
                String time = timeFull.substring(12, timeFull.length() - 1);
                LocalDate date = LocalDate.parse(timeFull.substring(0, 11), formatter);
                currentTask = new Deadline(taskName, date, time);
            } else {
                LocalDate date = LocalDate.parse(timeFull.substring(0, 11), formatter);
                currentTask = new Deadline(taskName, date);
            }
            taskList.add(currentTask);

            if (lineToAdd.charAt(4) == 'X') {
                currentTask.markAsDone();
            }
        } else {
            int separator = taskDetails.indexOf(" (at: ");
            String taskName = taskDetails.substring(0, separator);
            String timeFull = taskDetails.substring(separator + 6);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            Task currentTask;

            if (timeFull.length() > 12) {
                String time = timeFull.substring(12, timeFull.length() - 1);
                LocalDate date = LocalDate.parse(timeFull.substring(0, 11), formatter);
                currentTask = new Event(taskName, date, time);
            } else {
                LocalDate date = LocalDate.parse(timeFull.substring(0, 11), formatter);
                currentTask = new Event(taskName, date);
            }
            taskList.add(currentTask);

            if (lineToAdd.charAt(4) == 'X') {
                currentTask.markAsDone();
            }
        }
    }
}
