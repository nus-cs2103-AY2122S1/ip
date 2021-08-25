import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private static final String FORMAT = "\t%s\n";
    private static final String LINE = "______________________________________________________";
    private static final Path SAVEFILE_DIR =
            Paths.get(System.getProperty("user.dir"), "data");
    private static final Path SAVEFILE_PATH =
            Paths.get(System.getProperty("user.dir"), "data", "tasks.txt");


    public static void main(String[] args) {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        System.out.print(logo);
        System.out.printf(FORMAT, LINE);

        List<Task> tasks = new ArrayList<>();
        try {
            tasks = loadTasksFromFile();
            System.out.printf(FORMAT, "Welcome back!");
        } catch (FileNotFoundException e) {
            // If file is not found, greet user with first time welcome message
            System.out.printf(FORMAT, "Hello there, I'm Duke!");
        } catch (Exception e) {
            System.out.printf("\tUh-oh! %s\n", e.getMessage());
        }

        System.out.printf(FORMAT, "What can I do for you today?");
        System.out.printf(FORMAT, LINE);

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String input = sc.nextLine().trim();
                // Convert string command to enum value
                Command command = Command.valueOf(input.split(" ")[0].toUpperCase());
                switch (command) {
                case BYE:
                    // Exit chat bot
                    System.out.printf(FORMAT, LINE);
                    System.out.printf(FORMAT, "Goodbye. Have a nice day!");
                    System.out.printf(FORMAT, LINE);
                    return; // To terminate function
                case LIST:
                    runListCommand(tasks);
                    break;
                case DONE:
                    runDoneCommand(input, tasks);
                    saveTasksToFile(tasks);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    runAddTaskCommand(input, command, tasks);
                    saveTasksToFile(tasks);
                    break;
                case DELETE:
                    runDeleteCommand(input, tasks);
                    saveTasksToFile(tasks);
                    break;
                default:
                    throw new DukeException("You have entered an invalid command.");
                }
            } catch (IOException e) {
                System.out.printf(FORMAT, LINE);
                System.out.printf("\tUh-oh! %s\n", "The data failed to save to the save file.");
                System.out.println(e.getMessage());
                System.out.printf(FORMAT, LINE);
            }catch (IllegalArgumentException e) {
                // When invalid command is given, it is unable to be parsed into the enum
                System.out.printf(FORMAT, LINE);
                System.out.printf("\tUh-oh! %s\n", "You have entered an invalid command.");
                System.out.printf(FORMAT, LINE);
            } catch (Exception e) {
                System.out.printf(FORMAT, LINE);
                System.out.printf("\tUh-oh! %s\n", e.getMessage());
                System.out.printf(FORMAT, LINE);
            }
        }
    }

    // Helper function to separate a string into taskName and date
    private static String[] splitWith(String input, int startIndex, String regex) throws DukeException {
        if (startIndex >= input.length() || !input.contains(regex)) {
            throw new DukeException("Command must be in the format: [taskName]" + regex + "[date]");
        }
        return input.substring(startIndex).split(regex);
    }

    // Abstraction to make main function neater
    private static void runDoneCommand(String input, List<Task> tasks) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("Please type in a task number to mark as done.");
        }
        String taskNumberString = input.substring(5);
        System.out.printf(FORMAT, LINE);
        if (taskNumberString.matches("\\d+")
                && (Integer.parseInt(taskNumberString) - 1 < tasks.size()
                && Integer.parseInt(taskNumberString) - 1 >= 0)) {
            int taskIndex = Integer.parseInt(taskNumberString) - 1;
            Task doneTask = tasks.get(taskIndex);
            doneTask.markAsDone();
            System.out.printf(FORMAT, "Good work! This task is now marked as done:");
            System.out.printf("\t\t%s\n", doneTask.toString());
        } else {
            // Invalid input (not a number or invalid number)
            throw new DukeException("Please type in a valid task number to mark as done.");
        }
        System.out.printf(FORMAT, LINE);
    }

    // Abstraction to make main function neater
    private static void runListCommand(List<Task> tasks) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("You do not have any tasks currently.");
        }
        System.out.printf(FORMAT, LINE);
        System.out.printf(FORMAT, "Here is your task list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d.%s\n", i + 1, tasks.get(i));
        }
        System.out.printf(FORMAT, LINE);
    }

    // Abstraction to make main function neater
    private static void runAddTaskCommand(String input, Command command, List<Task> tasks) throws DukeException {
        if (input.contains("|")) {
            throw new DukeException("Input contains |, which is an invalid character.");
        }
        Task task;
        switch (command) {
        case TODO:
            // Add Todo task
            if (input.length() <= 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            task = new Todo(input.substring(5));
            break;
        case DEADLINE:
            String[] splitInput = splitWith(input, 9, " /by ");
            String taskName = splitInput[0];
            String date = splitInput[1];
            task = new Deadline(taskName, date);
            break;
        default: // default is guaranteed to be event task due to use of enum + outer control flow
            // Add Event task
            splitInput = splitWith(input, 6, " /at ");
            taskName = splitInput[0];
            date = splitInput[1];
            task = new Event(taskName, date);
            break;
        }

        // Common functionality: add task to list, print task and list size, save tasks to file
        tasks.add(task);
        System.out.printf(FORMAT, LINE);
        System.out.printf(FORMAT, "Got it. The following task has been added: ");
        System.out.printf("\t\t%s\n", task.toString());
        System.out.printf("\tNow you have %d task%s in the list.\n",
                tasks.size(), tasks.size() == 1 ? "" : "s");
        System.out.printf(FORMAT, LINE);
    }

    // Abstraction to make main function neater
    private static void runDeleteCommand(String input, List<Task> tasks) throws DukeException {
        if (input.length() <= 7) {
            throw new DukeException("Please type in a task number to delete.");
        }
        String taskNumberString = input.substring(7);
        System.out.printf(FORMAT, LINE);
        if (taskNumberString.matches("\\d+")
                && (Integer.parseInt(taskNumberString) - 1 < tasks.size()
                && Integer.parseInt(taskNumberString) - 1 >= 0)) {
            int taskIndex = Integer.parseInt(taskNumberString) - 1;
            Task removedTask = tasks.remove(taskIndex);
            System.out.printf(FORMAT, "Got it. The following task has been removed:");
            System.out.printf("\t\t%s\n", removedTask.toString());
            System.out.printf("\tNow you have %d task%s in the list.\n",
                    tasks.size(), tasks.size() == 1 ? "" : "s");
        } else {
            // Invalid input (not a number or invalid number)
            throw new DukeException("Please type in a valid task number to delete.");
        }
        System.out.printf(FORMAT, LINE);
    }

    // Saves tasks to the file ./data/tasks.txt. Called when list is modified.
    private static void saveTasksToFile(List<Task> tasks) throws IOException {
        Files.createDirectories(SAVEFILE_DIR); // Create data directory if it does not exist
        FileWriter fw = new FileWriter(SAVEFILE_PATH.toAbsolutePath().toString());
        StringBuilder saveData = new StringBuilder();
        for (Task task : tasks) {
            saveData.append(task.toSaveData()).append(System.lineSeparator());
        }
        fw.write(saveData.toString());
        fw.close();
    }

    // Loads tasks from the save file ./data/tasks.txt. Called when Duke starts.
    private static List<Task> loadTasksFromFile() throws FileNotFoundException, DukeException {
        File saveFile = SAVEFILE_PATH.toFile();
        Scanner scanner = new Scanner(saveFile);
        List<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String taskLine = scanner.nextLine();
            String[] taskData = taskLine.split("\\|");
            switch(taskData[0]) {
            case("T"):
                tasks.add(new Todo(taskData[2], Boolean.parseBoolean(taskData[1])));
                break;
            case("D"):
                tasks.add(new Deadline(taskData[2], Boolean.parseBoolean(taskData[1]), taskData[3]));
                break;
            case("E"):
                tasks.add(new Event(taskData[2], Boolean.parseBoolean(taskData[1]), taskData[3]));
                break;
            default:
                throw new DukeException("Save file contains invalid task data (Invalid task type)");
            }
        }

        return tasks;
    }

}
