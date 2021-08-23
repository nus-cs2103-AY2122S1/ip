import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * This class represents the chat bot, Duke.
 */
public class Duke {
    private final static String DATABASE_PATH = "data/duke.txt";
    private static TaskList tasks = TaskList.createTaskList();
    /**
     * The static method that runs in Main to reply to the user.
     */
    public static void reply() throws FileNotFoundException {
        File dukeData = new File(DATABASE_PATH);
        if (dukeData.exists()) {
            Duke.readData(DATABASE_PATH);
        }
        Scanner myObj = new Scanner(System.in);
        String command;
        boolean running = true;
        while (running) {
            command = myObj.nextLine();
            String[] commandSplit = command.split(" ");
            String firstCommand = commandSplit[0];
            try {
                switch(firstCommand) {
                    case "bye":
                        System.out.println("Goodbye!");
                        running = false;
                        break;
                    case "done":
                        markDone(commandSplit);
                        Duke.writeData(DATABASE_PATH);
                        break;
                    case "deadline":
                    case "todo":
                    case "event":
                        String[] commands = addTask(commandSplit);
                        Duke.writeData(DATABASE_PATH);
                        break;
                    case "delete":
                        deleteTask(commandSplit);
                        Duke.writeData(DATABASE_PATH);
                        break;
                    case "list":
                        Duke.tasks.listTasks();
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException | IOException e ) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void createFileIfNotFound(String filePath) throws IOException {
        String[] fileSplit = filePath.split("/");
        String directory = "";
        for (int i = 0; i < fileSplit.length - 1; i++) {
            directory += fileSplit[i];
        }
        File dir = new File(directory);
        dir.mkdir();
        File yourFile = new File(filePath);
        yourFile.createNewFile();
    }

    /**
     * Writes data to data.txt in data directory.
     * @param filePath Path to database.
     * @throws IOException
     */
    public static void writeData(String filePath) throws IOException {
        createFileIfNotFound(filePath);
        FileWriter fw = new FileWriter(filePath);
        for (int i = 1; i < tasks.getTasksLength() + 1; i++) {
            Task task = tasks.getTask(i);
            switch (task.type) {
                case DEADLINE:
                    Deadline dl = (Deadline) task;
                    fw.write("D," + task.getStatusIcon() + "," + task.getTaskDescription() + "," + dl.getBy());
                    break;
                case TODO:
                    fw.write("T," + task.getStatusIcon() + "," + task.getTaskDescription());
                    break;
                case EVENT:
                    Event e = (Event) task;
                    fw.write("E," + task.getStatusIcon() + "," + task.getTaskDescription() + e.getAt());
                    break;
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Reads data from data.txt.
     * @param filePath Path to database.
     * @throws FileNotFoundException
     */
    public static void readData(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int currentTask = 1;
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String[] commands = currentLine.split(",");
            switch(commands[0]) {
                case "T":
                    tasks.addTask(commands[2], Task.TaskType.TODO, "");
                    break;
                case "E":
                    tasks.addTask(commands[2], Task.TaskType.EVENT, commands[3]);
                    break;
                case "D":
                    tasks.addTask(commands[2], Task.TaskType.DEADLINE, commands[3]);
                    break;
            }
            if (commands[1].equals("[X]")) {
                tasks.getTask(currentTask).markAsDone();
            }
            currentTask++;
        }
    }

    /**
     * Method to delete task.
     * @param commandSplit The array of space-separated words/numbers in the command.
     * @throws DukeException
     */
    public static void deleteTask(String[] commandSplit) throws DukeException {
        try {
            Duke.tasks.deleteTask(Integer.parseInt(commandSplit[1]));
            System.out.println("Noted. I've removed this task: ");
            System.out.println("Now you have " + Duke.tasks.getTasksLength() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Index out of range!");
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Put a number after 'delete'!");
        }
    }

    /**
     * Method to add task to Duke.
     * @param commandSplit The array of space-separated words/numbers in the command.
     * @return String array of the command keywords.
     * @throws DukeException
     */
    public static String[] addTask(String[] commandSplit) throws DukeException {
        String firstCommand = commandSplit[0];
        String date = Duke.findDateInCommand(commandSplit, firstCommand);
        String taskDesc = Duke.findTaskDescription(commandSplit);
        String aOrAn = firstCommand.equals("event") ? "an" : "a";
        if (taskDesc.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of " + aOrAn + " " + firstCommand + " cannot be empty.");
        } else if (date.equals("") && !firstCommand.equals("todo")) {
            throw new DukeException("☹ OOPS!!! The date of " + aOrAn + " " + firstCommand + " cannot be empty.");
        } else {
            Duke.tasks.addTask(taskDesc, convertToTaskType(firstCommand), date);
            System.out.println("Got it. I've added this task: ");
            System.out.println(Duke.tasks.getTask(Duke.tasks.getTasksLength()));
            System.out.println("Now you have " + Duke.tasks.getTasksLength() + " tasks in the list.");
        }
        String[] commands = {commandSplit[0], taskDesc, date};
        return commands;
    }

    /**
     * Method for Duke to mark a task done.
     * @param commandSplit The array of space-separated words/numbers in the command.
     * @throws DukeException
     */
    private static void markDone(String[] commandSplit) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(commandSplit[1]);
            Duke.tasks.markTaskDone(taskIndex);
            Task task = Duke.tasks.getTask(taskIndex);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The number you gave is out of range!");
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Put a number after 'done'!");
        }
    }

    /**
     * Checks if the string is an integer.
     * @param input String to check.
     * @return Whether string is an integer.
     */

    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * Finds the date in the command (if any).
     * @param commandList The array of the split up command.
     * @return The date in the command (if any).
     */
    private static String findDateInCommand(String[] commandList, String taskType) {
        int startingIndexOfDate = -1;
        for (int i = 0; i < commandList.length; i++) {
            if (commandList[i].equals("/by") || commandList[i].equals("/at")) {
                if (taskType.equals("deadline") && commandList[i].equals("/at")) {
                    throw new DukeException("☹ OOPS!!! Use /by for deadlines!");
                } else if (taskType.equals("event") && commandList[i].equals("/by")) {
                    throw new DukeException("☹ OOPS!!! Use /at for events!");
                }
                startingIndexOfDate = i + 1;
                break;
            }
        }
        if (startingIndexOfDate == -1) {
            return "";
        } else {
            StringBuilder fullDate = new StringBuilder();
            for (int i = startingIndexOfDate; i < commandList.length; i++) {
                fullDate.append(commandList[i]);
                if (i != commandList.length - 1) {
                    fullDate.append(" ");
                }
            }
            return fullDate.toString();
        }
    }

    /**
     * Finds the task description in the command list.
     * @param commandList The array of the split up command.
     * @return The description of the task.
     */
    public static String findTaskDescription(String[] commandList) {
        StringBuilder taskDesc = new StringBuilder();
        for (int i = 1; i < commandList.length; i++) {
            if (commandList[i].equals("/by") || commandList[i].equals("/at")) {
                break;
            } else {
                taskDesc.append(commandList[i]).append(" ");
            }
        }
        return taskDesc.toString().trim();
    }

    public static Task.TaskType convertToTaskType(String command) {
        if (command.equals("todo")) {
            return Task.TaskType.TODO;
        } else if (command.equals("event")) {
            return Task.TaskType.EVENT;
        } else {
            return Task.TaskType.DEADLINE;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from \n" + logo);
        System.out.println("What can I do for you?");
        try {
            Duke.reply();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }
}
