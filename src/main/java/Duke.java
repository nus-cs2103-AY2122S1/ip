import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Duke {
    // Constant declarations
    private final static String LINE_BREAK = "--------------------------\n";

    // Class static attributes
    private final static List<Task> taskList = new ArrayList<Task>();

    /**
     * Wraps a string between 2 line breaks.
     * 
     * @param s String to be wrapped.
     * @return New string between 2 line breaks.
     */
    private static String wrapBetweenLines(String s) {
        return LINE_BREAK + s + "\n" + LINE_BREAK;
    }

    /**
     * Prints the specified message in a standardized format.
     * 
     * @param s The message to be printed.
     */
    private static void dukePrint(String s) {
        System.out.println(wrapBetweenLines(s));
    }

    private static void todoHandler(String args) throws InvalidDukeCommandException {
        if (args.equals("")) {
            throw new InvalidDukeCommandException("The description of a todo cannot be empty.");
        }
        taskList.add(new Todo(args));
        String returnMessage = String.format("Got it. I've added this task:\n%s\nNow you have %d task%s in the list.",
                args, taskList.size(), taskList.size() > 1 ? "s" : "");
        dukePrint(returnMessage);
    }

    private static void eventHandler(String args) throws InvalidDukeCommandException {
        if (args.equals("")) {
            throw new InvalidDukeCommandException("The description of an event cannot be empty.");
        }
        String taskDescription = args.split(" /at ")[0];
        String eventTime;
        try {
            eventTime = args.split(" /at ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidDukeCommandException(
                    "Incorrect description format. Description should follow this pattern: *description* /at *time*");
        }
        taskList.add(new Event(taskDescription, eventTime));
        String returnMessage = String.format("Got it. I've added this task:\n%s\nNow you have %d task%s in the list.",
                taskDescription, taskList.size(), taskList.size() > 1 ? "s" : "");
        dukePrint(returnMessage);
    }

    private static void deadlineHandler(String args) throws InvalidDukeCommandException {
        if (args.equals("")) {
            throw new InvalidDukeCommandException("The description of a deadline cannot be empty.");
        }
        String taskDescription = args.split(" /by ")[0];
        String finishDate;
        try {
            finishDate = args.split(" /by ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidDukeCommandException(
                    "Incorrect description format. Description should follow this pattern: *description* /by *time*");
        }
        taskList.add(new Deadline(taskDescription, finishDate));
        String returnMessage = String.format("Got it. I've added this task:\n%s\nNow you have %d task%s in the list.",
                taskDescription, taskList.size(), taskList.size() > 1 ? "s" : "");
        dukePrint(returnMessage);
        ;
    }

    private static void listHandler() {
        AtomicInteger idx = new AtomicInteger(1);
        String outputList = taskList.stream().map(task -> Integer.toString(idx.getAndIncrement()) + ". " + task + "\n")
                .collect(Collectors.joining());
        // Remove last newline for prettier formatting
        outputList = outputList.substring(0, outputList.length() - 1);
        dukePrint(outputList);
    }

    private static void doneHandler(String args) throws InvalidDukeCommandException {
        if (args.equals("")) {
            throw new InvalidDukeCommandException(
                    "The done command expects an integer argument indicating the index of a task.");
        }
        Task task;
        try {
            task = taskList.get(Integer.valueOf(args) - 1);
        } catch (NumberFormatException e) {
            throw new InvalidDukeCommandException("Invalid argument for done command. Argument should be an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDukeCommandException("Invalid integer. Integer should match the index of a task."
                    + " Run list to look at the list of tasks and their corresponding indices.");
        }
        String acknowledgementMessage = "Nice! I've marked this task as done:\n";
        task.markAsDone();
        dukePrint(acknowledgementMessage + task);
    }

    private static void deleteHandler(String args) throws InvalidDukeCommandException {
        if (args.equals("")) {
            throw new InvalidDukeCommandException(
                    "The delete command expects an integer argument indicating the index of a task.");
        }
        Task task;
        try {
            task = taskList.remove(Integer.valueOf(args) - 1);
        } catch (NumberFormatException e) {
            throw new InvalidDukeCommandException(
                    "Invalid argument for delete command. Argument should be an integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDukeCommandException("Invalid integer. Integer should match the index of a task."
                    + " Run list to look at the list of tasks and their corresponding indices.");
        }
        String returnMessage = String.format("Noted. I've removed this task:\n%s\nNow you have %d task%s in the list.",
                task, taskList.size(), taskList.size() > 1 ? "s" : "");
        dukePrint(returnMessage);
    }

    private static void byeHandler() {
        dukePrint("Bye. Hope to see you again soon!");
    }

    private static void defaultHandler() throws InvalidDukeCommandException {
        throw new InvalidDukeCommandException("Invalid command detected");
    }

    /**
     * Run tasks based on the corresponding commands given by the user.
     * 
     * @param s Command input of user
     */
    private static void handleInput(String s) {
        String[] inputTokens = s.split(" ", 2);
        String command = inputTokens[0];
        String args = inputTokens.length > 1 ? inputTokens[1] : "";
        try {
            switch (command) {
                case "done":
                    doneHandler(args);
                    break;
                case "bye":
                    byeHandler();
                    break;
                case "list":
                    listHandler();
                    break;
                case "todo":
                    todoHandler(args);
                    break;
                case "event":
                    eventHandler(args);
                    break;
                case "deadline":
                    deadlineHandler(args);
                    break;
                case "delete":
                    deleteHandler(args);
                    break;
                default:
                    defaultHandler();
            }
        } catch (InvalidDukeCommandException e) {
            dukePrint(e.getMessage());
        }
    }

    /**
     * Prompts users to input their commands to Duke
     */
    private static void promptUserCommands() {
        String introduction = "Hello! I'm Duke\nWhat can I do for you?";
        dukePrint(introduction);

        String TERMINATE_COMMAND = "bye";
        String userInput = "";
        Scanner reader = new Scanner(System.in);

        while (true) {
            userInput = reader.nextLine();
            handleInput(userInput);
            if (userInput.equals(TERMINATE_COMMAND)) {
                break;
            }
        }
        reader.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        promptUserCommands();
    }
}
