import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Duke {
    // Constant declarations
    private final static String LINE_BREAK = "--------------------------\n";

    // Class static attributes
    private final static List<Task> taskList = new ArrayList<Task>();

    /**
     * Wrap a string between 2 line breaks.
     * 
     * @param s String to be wrapped.
     * @return New string between 2 line breaks.
     */
    private static String wrapBetweenLines(String s) {
        return LINE_BREAK + s + "\n" + LINE_BREAK;
    }

    private static void todoHandler(String args) {
        taskList.add(new Todo(args));
        String returnMessage = String.format("Got it. I've added this task:\n%s\nNow you have %d task%s in the list.",
                args, taskList.size(), taskList.size() > 1 ? "s" : "");
        System.out.println(wrapBetweenLines(returnMessage));
    }

    private static void eventHandler(String args) {
        String taskDescription = args.split(" /at ")[0];
        String eventTime = args.split(" /at ")[1];
        taskList.add(new Event(taskDescription, eventTime));
        String returnMessage = String.format("Got it. I've added this task:\n%s\nNow you have %d task%s in the list.",
                taskDescription, taskList.size(), taskList.size() > 1 ? "s" : "");
        System.out.println(wrapBetweenLines(returnMessage));
    }

    private static void deadlineHandler(String args) {
        String taskDescription = args.split(" /by ")[0];
        String finishDate = args.split(" /by ")[1];
        taskList.add(new Event(taskDescription, finishDate));
        String returnMessage = String.format("Got it. I've added this task:\n%s\nNow you have %d task%s in the list.",
                taskDescription, taskList.size(), taskList.size() > 1 ? "s" : "");
        System.out.println(wrapBetweenLines(returnMessage));
    }

    private static void listHandler() {
        AtomicInteger idx = new AtomicInteger(1);
        String outputList = taskList.stream().map(task -> Integer.toString(idx.getAndIncrement()) + ". " + task + "\n")
                .collect(Collectors.joining());
        // Remove last newline for prettier formatting
        outputList = outputList.substring(0, outputList.length() - 1);
        System.out.println(wrapBetweenLines(outputList));
    }

    private static void doneHandler(String args) {
        String acknowledgementMessage = "Nice! I've marked this task as done:\n";
        Task task = taskList.get(Integer.valueOf(args) - 1);
        task.markAsDone();
        System.out.println(wrapBetweenLines(acknowledgementMessage + task));
    }

    private static void byeHandler() {
        System.out.println(wrapBetweenLines("Bye. Hope to see you again soon!"));
    }

    private static void defaultHandler() {
        System.out.println("Invalid command detected");
    }

    /**
     * Run tasks based on the corresponding commands given by the user
     * 
     * @param s Command input of user
     */
    private static void handleInput(String s) {
        String[] inputTokens = s.split(" ", 2);
        String command = inputTokens[0];
        String args = inputTokens.length > 1 ? inputTokens[1] : "";
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
            default:
                defaultHandler();

        }
    }

    /**
     * Prompts users to input their commands to Duke
     */
    private static void promptUserCommands() {
        String introduction = wrapBetweenLines("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(introduction);

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
