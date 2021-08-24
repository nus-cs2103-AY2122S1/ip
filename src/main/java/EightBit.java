import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the chat bot.
 */
public class EightBit {
    private String filepath;

    private ArrayList<Task> tasks = new ArrayList<>();
    private final String LINE = "-------------------------------------------------------";

    public EightBit(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Wraps the message with lines and print it.
     *
     * @param msg Message to be printed.
     */
    public void printWithLines(String msg) {
        System.out.println(LINE + "\n" + msg + "\n" + LINE);
    }

    public void run() {
        printWithLines("Hello! I'm 8-Bit!\nWhat can I do for you?");
        loadFileContents(this.filepath);
        getUserInput();
    }

    /**
     * Gets and processes user input.
     */
    public void getUserInput() {
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();
        Command commandType = getCommandType(command);
        while (commandType != Command.BYE) {
            processInput(command);
            command = sc.nextLine();
            commandType = getCommandType(command);
        }

        printWithLines("Bye. Hope to see you again soon!");
        sc.close();
    }

    private Command getCommandType(String command) {
        String uppercase = command.trim().toUpperCase();
        try {
            return Command.valueOf(uppercase);
        } catch (IllegalArgumentException e) {
            return Command.DEFAULT;
        }
    }

    private void processInput(String msg) {
        String[] commands = msg.split(" ");
        Command commandType = getCommandType(commands[0]);

        try {
            switch (commandType) {
            case LIST:
                processList(msg);
                break;
            case DONE:
                processDone(msg);
                break;
            case TODO:
                processToDo(msg);
                break;
            case DEADLINE:
                processDeadline(msg);
                break;
            case EVENT:
                processEvent(msg);
                break;
            case DELETE:
                processDelete(msg);
                break;
            default:
                throw new EightBitException("OOPS!!! I'm sorry, but I don't know what that means :(");
            }
        } catch (EightBitException e) {
            printWithLines(e.toString());
        }
    }

    private void addTask(Task task) {
        tasks.add(task);
        appendToFile(this.filepath, parseTaskToFileFormat(task));

        printWithLines("Got it. I've added this task:\n  " + task
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private void processList(String msg) throws EightBitException {
        if (msg.split(" ").length > 1) { // extra words after "list"
            throw new EightBitException("OOPS!!! Please remove words after \"list\"");
        }

        StringBuilder listOfTasks = new StringBuilder();
        if (tasks.size() == 0) {
            listOfTasks = new StringBuilder("There are no tasks in your list currently.");
            printWithLines(listOfTasks.toString());
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                String newTask = (i + 1) + ". " + tasks.get(i) + "\n";
                listOfTasks.append(newTask);
            }
            printWithLines("Here are the tasks in your list:\n" + listOfTasks);
        }
    }

    private void processDone(String msg) throws EightBitException {
        if (msg.split(" ").length != 2) { // not in the format "done <integer>"
            throw new EightBitException("OOPS!!! Please enter in this format:\ndone <integer>");
        }
        try {
            Integer.parseInt(msg.split(" ")[1]);
        } catch (NumberFormatException e) { // not integer
            throw new EightBitException("OOPS!!! Please enter in this format:\ndone <integer>");
        }

        int index = Integer.parseInt(msg.split(" ")[1]) - 1;
        if (index >= tasks.size() || index < 0) { // number exceeding no. of tasks in list or negative
            throw new EightBitException("OOPS!!! Task " + (index + 1) + " does not exist.");
        }

        tasks.get(index).markAsDone();
        rewriteFileWithTasks(this.filepath);

        printWithLines("Great job on completing this task!\n" + tasks.get(index).toString());
    }

    private void processToDo(String msg) throws EightBitException {
        if (msg.split(" ").length < 2) { // missing description
            throw new EightBitException("OOPS!!! The description of a todo cannot be empty.");
        }

        String todoDescription = msg.substring(5);
        ToDo todo = new ToDo(todoDescription);
        addTask(todo);
    }

    private void processDeadline(String msg) throws EightBitException {
        if (msg.split(" ").length == 1 // missing description and deadline
                || msg.substring(9).trim().split(" /by ").length < 2) { // missing either description or deadline
            throw new EightBitException("OOPS!!! Please enter your deadline in this format:\n"
                    + "deadline <description> /by <date/time>");
        }

        String[] descriptionAndBy = msg.substring(9).split(" /by ");
        String deadlineDescription = descriptionAndBy[0];
        String deadlineBy = descriptionAndBy[1];
        Deadline deadline = new Deadline(deadlineDescription, deadlineBy);
        addTask(deadline);
    }

    private void processEvent(String msg) throws EightBitException {
        if (msg.split(" ").length == 1 // missing description and date
                || msg.substring(6).trim().split(" /at ").length < 2) { // missing either description or date
            throw new EightBitException("OOPS!!! Please enter your event in this format:\n"
                    + "event <description> /at <date/time>");
        }

        String[] descriptionAndAt = msg.substring(6).split(" /at ");
        String eventDescription = descriptionAndAt[0];
        String eventAt = descriptionAndAt[1];
        Event event = new Event(eventDescription, eventAt);
        addTask(event);
    }

    private void processDelete(String msg) throws EightBitException {
        if (msg.split(" ").length != 2) { // not in the format "delete <integer>"
            throw new EightBitException("OOPS!!! Please enter in this format:\ndelete <integer>");
        }
        try {
            Integer.parseInt(msg.split(" ")[1]);
        } catch (NumberFormatException e) { // not integer
            throw new EightBitException("OOPS!!! Please enter in this format:\ndelete <integer>");
        }

        int index = Integer.parseInt(msg.split(" ")[1]) - 1;
        if (index >= tasks.size() || index < 0) { // number exceeding no. of tasks in list or negative
            throw new EightBitException("OOPS!!! Task " + (index + 1) + " does not exist.");
        }
        Task deletedTask = tasks.remove(index);
        rewriteFileWithTasks(this.filepath);
        printWithLines("Noted. I've removed this task:\n  " + deletedTask
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private void createFile(String filepath) {
        try {
            File f = new File(filepath);
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendToFile(String filepath, String text) {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            fw.write(text + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rewriteFileWithTasks(String filepath) {
        try {
            FileWriter fw = new FileWriter(filepath);
            StringBuilder newFileContent = new StringBuilder();
            for (Task task : this.tasks) {
                newFileContent.append(parseTaskToFileFormat(task)).append(System.lineSeparator());
            }
            fw.write(newFileContent.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFileContents(String filepath) {
        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);

            ArrayList<Task> tasksInFile = new ArrayList<>();
            while (s.hasNext()) {
                String str = s.nextLine();
                String[] words = str.split(" \\| ");
                String taskType = words[0];
                boolean isDone = words[1].equals("1");
                String taskDescription = words[2];

                switch (taskType) {
                case "T":
                    ToDo todo = new ToDo(taskDescription, isDone);
                    tasksInFile.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(taskDescription, words[3], isDone);
                    tasksInFile.add(deadline);
                    break;
                case "E":
                    Event event = new Event(taskDescription, words[3], isDone);
                    tasksInFile.add(event);
                    break;
                default:
                    break;
                }
            }
            s.close();

            this.tasks = tasksInFile;
        } catch (FileNotFoundException e) {
            createFile(filepath);
        }
    }

    private String parseTaskToFileFormat(Task task) {
        String isDone = task.isDone ? "1" : "0";
        if (task instanceof ToDo) {
            ToDo t = (ToDo) task;
            return "T | " + isDone + " | " + t.description;
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + isDone + " | " + d.description + " | " + d.by;
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + isDone + " | " + e.description + " | " + e.at;
        } else {
            return "";
        }
    }
}
