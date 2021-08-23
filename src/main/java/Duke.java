import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class encapsulates Duke, an interactive task management chat-bot.
 *
 * @author Kleon Ang
 */
public class Duke {
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DATA_DELIMITER = " \\| ";
    private static final String DATA_FILENAME = "duke.txt";
    private static final String DATA_FILEPATH = System.getProperty("user.dir") + "/data/";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-d H:mm");
    private static List<Task> tasks = new ArrayList<>();

    private static void printReply(String string) {
        System.out.println(new Reply(string));
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printReply("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void add(Task task) {
        tasks.add(task);
        writeData(task);
        String addMessage = "Got it. I've added this task:\n  " + task
                + "\nNow you have " + tasks.size() + " tasks in the list.";
        printReply(addMessage);
    }

    private static void list() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("There are currently no tasks in your list.");
        }
        StringBuilder tasksBuilder = new StringBuilder();
        tasksBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            String counter = String.valueOf(i + 1);
            Task currentTask = tasks.get(i);
            tasksBuilder.append(counter).append(".").append(currentTask);
            if (i < tasks.size() - 1) {
                // Append newline for all tasks before last task
                tasksBuilder.append("\n");
            }
        }
        printReply(tasksBuilder.toString());
    }

    private static void done(int counter) throws DukeException {
        if (counter <= 0 || counter > tasks.size()) {
            throw new DukeException("Sorry, no such task of index " + counter + ".");
        }
        Task doneTask = tasks.get(counter - 1);
        doneTask.markAsDone();
        printReply("Nice! I've marked this task as done:\n  " + doneTask);
        rewriteData();
    }

    private static void delete(int counter) throws DukeException {
        if (counter <= 0 || counter > tasks.size()) {
            throw new DukeException("Sorry, no such task of index " + counter + ".");
        }
        Task taskToRemove = tasks.remove(counter - 1);
        printReply("Noted. I've removed this task:\n  " + taskToRemove
                + "\nNow you have " + tasks.size() + " tasks in the list.");
        rewriteData();
    }

    private static void readData() {
        File directory = new File(DATA_FILEPATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            File dataFile = new File(DATA_FILEPATH + DATA_FILENAME);
            Scanner fileReader = new Scanner(dataFile);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] data = line.split(DATA_DELIMITER);
                String taskType = data[0];
                String doneStatus = data[1];
                Task importedTask;
                // Assign correct Task type to importedTask
                switch (taskType) {
                case "T":
                    importedTask = new Todo(data[2]);
                    break;
                case "D":
                    LocalDateTime deadlineDatetime = LocalDateTime.parse(data[3], FORMATTER);
                    importedTask = new Deadline(data[2], deadlineDatetime);
                    break;
                case "E":
                    LocalDateTime eventDatetime = LocalDateTime.parse(data[3], FORMATTER);
                    importedTask = new Event(data[2], eventDatetime);
                    break;
                default:
                    throw new IllegalStateException("Unexpected Task value: " + taskType);
                }
                // Mark imported task as done if doneStatus is 1
                if (doneStatus.equals("1")) {
                    importedTask.markAsDone();
                }
                tasks.add(importedTask);
            }
            fileReader.close();
            printReply("duke.txt found. Tasks have been imported.");
        } catch (FileNotFoundException e) {
            try {
                File dataFile = new File(DATA_FILEPATH + DATA_FILENAME);
                dataFile.createNewFile();
                String message = DATA_FILENAME + " not found. File has been created.";
                printReply(message);
            } catch (IOException ioException) {
                printReply(ioException.getMessage());
            }
        }
    }

    private static void writeData(Task task) {
        try {
            FileWriter fileWriter = new FileWriter(DATA_FILEPATH + DATA_FILENAME, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(task.getDataString());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            printReply(e.getMessage());
        }
    }

    private static void rewriteData() {
        try {
            FileWriter fileWriter = new FileWriter(DATA_FILEPATH + DATA_FILENAME, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Task task : tasks) {
                bufferedWriter.write(task.getDataString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            printReply(e.getMessage());
        }
    }

    public static void main(String[] args) {
        greet();
        readData();
        String readIn;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                readIn = scanner.nextLine();
                if (readIn.equals(EXIT_COMMAND)) {
                    break;
                } else if (readIn.equals(LIST_COMMAND)) {
                    list();
                } else {
                    String[] commandArguments = readIn.split(" ", 2);
                    String command = commandArguments[0];
                    String arguments = "";
                    if (commandArguments.length == 2) {
                        arguments = commandArguments[1];
                    }

                    switch (command) {
                    case "done": {
                        if (commandArguments.length < 2) {
                            throw new DukeException("☹ OOPS!!! The index of '" + command + "' cannot be empty.");
                        }
                        int counter = Integer.parseInt(arguments);
                        done(counter);
                        break;
                    }
                    case "deadline": {
                        if (commandArguments.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of '" + command + "' cannot be empty.");
                        }
                        String[] splitTask = arguments.split(" /by ");
                        if (splitTask.length < 2) {
                            throw new DukeException("Please indicate a deadline using '/by'.");
                        }
                        String description = splitTask[0];
                        String byString = splitTask[1];
                        try {
                            LocalDateTime by = LocalDateTime.parse(byString, FORMATTER);
                            add(new Deadline(description, by));
                        } catch (DateTimeParseException e) {
                            printReply("Datetime should be in YYYY-MM-DD hr:min (24h clock) format.");
                        }
                        break;
                    }
                    case "event": {
                        if (commandArguments.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of '" + command + "' cannot be empty.");
                        }
                        String[] splitTask = arguments.split(" /at ");
                        if (splitTask.length < 2) {
                            throw new DukeException("Please indicate the event time frame using '/at'.");
                        }
                        String description = splitTask[0];
                        String atString = splitTask[1];
                        try {
                            LocalDateTime at = LocalDateTime.parse(atString, FORMATTER);
                            add(new Event(description, at));
                        } catch (DateTimeParseException e) {
                            printReply("Datetime should be in YYYY-MM-DD hr:min (24h clock) format.");
                        }
                        break;
                    }
                    case "todo": {
                        if (commandArguments.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of '" + command + "' cannot be empty.");
                        }
                        add(new Todo(arguments));
                        break;
                    }
                    case "delete": {
                        if (commandArguments.length < 2) {
                            throw new DukeException("☹ OOPS!!! The index of '" + command + "' cannot be empty.");
                        }
                        int counter = Integer.parseInt(arguments);
                        delete(counter);
                        break;
                    }
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                printReply(e.getMessage());
            }
        }

        printReply("Bye. Hope to see you again soon!");
    }
}
