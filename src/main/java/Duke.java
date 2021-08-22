import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * A chatbot based on Project Duke
 *
 * @author KelvinSoo
 * @version Level-7
 */
public class Duke {

    // File path
    private final String fileDirectory = "./data/";
    private final String fileName = "duke.txt";

    // Name of the chat bot
    private final String chatbotName;

    // Scanner for getting user input
    private final Scanner sc = new Scanner(System.in);

    // Array list of all the tasks
    private final List<Task> taskList = new ArrayList<>();

    /**
     * A private constructor to initialize the name of the chatbot.
     *
     * @param chatbotName The name of the chatbot
     */
    private Duke(String chatbotName) {
        this.chatbotName = chatbotName;
    }

    /**
     * Print a given text in a box.
     *
     * @param text The text to be formatted
     */
    private void printReply(String text) {
        String[] textLine = text.split("\n");
        int maxLength = Arrays.stream(textLine).map(String::length).max(Integer::compareTo).orElse(-1);
        String lineStart = "." + "-".repeat(maxLength + 2) + ".";
        String lineEnd = "`" + "-".repeat(maxLength + 2) + "`";
        System.out.println(lineStart);
        for (String s : textLine) {
            System.out.printf("| %s%s |\n", s, " ".repeat(maxLength - s.length()));
        }
        System.out.println(lineEnd);
    }

    /**
     * Prints the greeting text to user.
     */
    private void greetUser() {
        printReply(String.format("Hello! I'm %s \nWhat can I do for you?", this.chatbotName));
    }

    /**
     * Terminate user session.
     */
    private void terminateUser() {
        printReply("Bye. Hope to see you again soon!");
    }

    /**
     * Print a list of task.
     *
     * @param list the task list
     */
    private void printListCommand(List<Task> list) throws DukeException {
        if (list.isEmpty()) {
            throw new DukeException("It seems that your task list is empty.\n"
                    + "Try adding some task using \"todo\", \"deadline\" or \"event\"");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here is your task list:\n");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            sb.append(String.format("%s. %s %s \n", i + 1, task.getStatusIcon(), task.getDescription()));
        }
        printReply(sb.toString());
        processReply(sc.nextLine());
    }

    /**
     * Add a Task to the task list.
     *
     * @param task The task to be added to taskList
     */
    private void addTask(Task task) throws DukeException {
        taskList.add(task);
        saveTasks(taskList);
        printReply(String.format("Got it. I've added this task:\n  %s %s\nNow you have %d tasks in the list.",
                task.getStatusIcon(), task.getDescription(), taskList.size()));
        processReply(sc.nextLine());
    }

    /**
     * Evaluate a todo command.
     * And add a todo task to the task list.
     *
     * @param command The user command
     */
    private void addTodoCommand(String command) throws DukeException {
        if (command.length() <= 5) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        addTask(new ToDo(command.substring(5)));
    }

    /**
     * Evaluate a deadline command
     * and add a deadline task to the task list.
     *
     * @param command The user command
     */

    private void addDeadlineCommand(String command) throws DukeException{
        if (command.length() <= 9 || !command.contains(" /by ")) {
            throw new DukeException("OOPS!!! The format of the deadline is incorrect.\n" +
                    "eg. deadline read book /by Friday");
        }
        String parameter = command.substring(9);
        String[] details = parameter.split(" /by ");
        LocalDateTime ldt = stringToDate(details[1]);
        if (ldt == null) {
            addTask(new Deadline(details[0], details[1]));
        } else {
            addTask(new Deadline(details[0],  ldt));
        }
    }

    /**
     * Evaluate an event command
     * and add a event task to the task list.
     *
     * @param command The user command
     */

    private void addEventCommand(String command) throws DukeException{
        if (command.length() <= 6 || !command.contains(" /at ")) {
            throw new DukeException("OOPS!!! The format of the event is incorrect.\n" +
                    "eg. event CS2103T lecture /at Thursday, 1600hr");
        }
        String parameter = command.substring(6);
        String[] details = parameter.split(" /at ");
        LocalDateTime ldt = stringToDate(details[1]);
        if (ldt == null) {
            addTask(new Event(details[0], details[1]));
        } else {
            addTask(new Event(details[0],  ldt));
        }
    }

    /**
     * Evaluate a done command.
     * Mark a given task as done.
     *
     * @param command The user command
     */
    private void doneCommand(String command) throws DukeException {
        String[] details = command.split(" ");
        if (details.length < 2) {
            // Missing parameter
            throw new DukeException("OOPS!!! Did you forget the task number?");
        }
        if (!details[1].matches("\\d+")) {
            // Invalid parameter
            throw new DukeException("OOPS!!! Invalid task number.");
        }
        int taskID = Integer.parseInt(details[1]);
        if (taskID > taskList.size()) {
            // Task does not exist
            throw new DukeException(String.format("Task %d does not exist.\nUse \"list\" to see all tasks.", taskID));
        }
        Task task = taskList.get(taskID - 1);
        task.markAsDone();
        saveTasks(taskList);
        printReply(String.format("Nice! I've marked this task as done: \n  %s %s",
                task.getStatusIcon(), task.getDescription()));
        processReply(sc.nextLine());
    }

    /**
     * Evaluate a delete command.
     * Delete a task from the task list.
     *
     * @param command The user command
     */
    private void deleteCommand(String command) throws DukeException {
        String[] details = command.split(" ", 2);
        if (details.length < 2) {
            // Missing parameter
            throw new DukeException("OOPS!!! Did you forget the task number?");
        }
        if (!details[1].matches("\\d+")) {
            // Invalid parameter
            throw new DukeException("OOPS!!! Invalid task number.");
        }
        int taskID = Integer.parseInt(details[1]);
        if (taskID > taskList.size()) {
            // Task does not exist
            throw new DukeException(String.format("Task %d does not exist.\nUse \"list\" to see all tasks.", taskID));
        }
        Task task = taskList.get(taskID - 1);
        taskList.remove(task);
        saveTasks(taskList);
        printReply(String.format("Noted. I've removed this task:\n  %s %s\nNow you have %d tasks in the list.",
                task.getStatusIcon(), task.getDescription(), taskList.size()));
        processReply(sc.nextLine());
    }

    /**
     * Convert a string in the supported format into a LocalDateTime.
     * @param string The string to convert
     */
    private LocalDateTime stringToDate(String string) {
        String[] details = string.split(" ", 2);
        LocalDate localDate;
        LocalDateTime localDateTime = null;
        String date = details[0];
        String time = details.length == 2 ? details[1] : null;
        if (date.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            String[] dateDetails = date.split("/");
            localDate = LocalDate.of(
                    Integer.parseInt(dateDetails[2]),
                    Integer.parseInt(dateDetails[1]),
                    Integer.parseInt(dateDetails[0]));
            if (time != null) {
                localDateTime = localDate.atTime(
                        Integer.parseInt(time.substring(0, 2)),Integer.parseInt(time.substring(2, 4)));
            } else {
                localDateTime = localDate.atStartOfDay();
            }
        }
        return localDateTime;
    }

    /**
     * Save task method.
     * Save the task list at the given file path.
     *
     * @param list The list to save
     */
    private void saveTasks(List<Task> list) throws DukeException {
        File folder = new File(fileDirectory);
        if (!folder.isDirectory()) {
            throw new DukeException(String.format("OOPS!!! Folder  \"%s\" not found.", fileDirectory));
        }
        try {
            FileWriter fw = new FileWriter(fileDirectory + fileName);
            for (Task t:list) {
                fw.write(t.getMetaData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(String.format("OOPS!!! File path \"%s\" is invalid", fileDirectory + fileName));
        }
    }

    /**
     * Load task method.
     * Load the task list at the given file path.
     *
     * @param fileDirectory The folder to save the file in
     * @param fileName The file to save in the folder
     */
    private void loadTasks(String fileDirectory, String fileName) throws DukeException {
        File folder = new File(fileDirectory);
        if (!folder.isDirectory()) {
            throw new DukeException(String.format("OOPS!!! Folder  \"%s\" not found.", fileDirectory));
        }

        File taskFile = new File(fileDirectory + fileName);
        try {
            Scanner s = new Scanner(taskFile);
            while (s.hasNext()) {
                String data = s.nextLine();
                String[] parameter = data.split("\\|");

                switch (parameter[0]) {
                case "T":
                    taskList.add(new ToDo(parameter[2], parameter[1].equals("X")));
                    break;
                case "D":
                    taskList.add(new Deadline(parameter[2], parameter[3], parameter[1].equals("X")));
                    break;
                case "E":
                    taskList.add(new Event(parameter[2], parameter[3], parameter[1].equals("X")));
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException(String.format("OOPS!!! File \"%s\" does not exist in \"%s\"", fileName, fileDirectory));
        }
    }

    /**
     * Process a given input and generate a reply
     *
     * @param text The user input
     */
    private void processReply(String text) {
        String[] details = text.split(" ");
        try {
            switch (details[0]) {
            case "bye":
                terminateUser();
                break;
            case "list":
                printListCommand(taskList);
                break;
            case "todo":
                addTodoCommand(text);
                break;
            case "deadline":
                addDeadlineCommand(text);
                break;
            case "event":
                addEventCommand(text);
                break;
            case "done":
                doneCommand(text);
                break;
            case "delete":
                deleteCommand(text);
                break;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            printReply(e.getMessage());
            processReply(sc.nextLine());
        }
    }

    /**
     * Start a new chatbot session
     */
    private void run() {
        try {
            loadTasks(fileDirectory, fileName);
            greetUser();
            processReply(sc.nextLine());
        } catch (DukeException e) {
            printReply(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String logo = " ____          _____  _______     __\n"
                + "|  _ \\   /\\   |  __ \\|  __ \\ \\   / /\n"
                + "| |_) | /  \\  | |__) | |__) \\ \\_/ /\n"
                + "|  _ < / /\\ \\ |  _  /|  _  / \\   /\n"
                + "| |_) / ____ \\| | \\ \\| | \\ \\  | |\n"
                + "|____/_/    \\_\\_|  \\_\\_|  \\_\\ |_|";
        System.out.println(logo);
        Duke barry = new Duke("Barry");
        barry.run();
    }
}
