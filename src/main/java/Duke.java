import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A chatbot based on Project Duke
 *
 * @author KelvinSoo
 * @version Level-5
 *
 */
public class Duke {

    private String chatbotName;
    private Scanner sc = new Scanner(System.in);
    private List<Task> taskList = new ArrayList<>();

    /**
     * A private constructor to initialize the name of the chatbot.
     *
     * @param chatbotName The name of the chatbot.
     */
    private Duke(String chatbotName) {
        this.chatbotName = chatbotName;
    }

    /**
     * Print a given text in a box.
     * @param text The text to be formatted.
     */
    private void printReply(String text) {
        int maxLength = 0;
        String[] textLine = text.split("\n");
        for (String s : textLine) {
            if (maxLength < s.length())
                maxLength = s.length();
        }
        // unicode does not work with test script
        //String lineStart =  "    \u2554" + "\u2550".repeat(maxLength + 2) + "\u2557";
        //String lineEnd =  "    \u255A" + "\u2550".repeat(maxLength + 2) + "\u255D";
        String lineBoarder =  "     " + "=".repeat(maxLength + 2);
        System.out.println(lineBoarder);
        for (String s : textLine) {
            System.out.println("    | " + s + " ".repeat(maxLength - s.length()) + " |");
        }
        System.out.println(lineBoarder);
    }

    /**
     * Prints the greeting text to user
     */
    private void greetUser() {
        printReply(String.format("Hello! I'm %s \nWhat can I do for you?", this.chatbotName));
    }

    /**
     * Terminate user session
     */
    private void terminateUser() {
        printReply("Bye. Hope to see you again soon!");
    }

    /**
     * Print a list of task.
     * @param list task list.
     */
    private void printListCommand(List<Task> list) throws DukeException{
        if (list.isEmpty()) {
            throw new DukeException("It seems that your task list is empty.\n" +
                    "Try adding some task using \"todo\", \"deadline\" or \"event\"");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here is your task list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            sb.append(String.format("%s. %s %s \n", i+1, task.getStatusIcon(), task.getDescription()));
        }
        printReply(sb.toString());
        processReply(sc.nextLine());
    }

    /**
     * Add a Task to the task list
     * @param task the task.
     */
    private void addTask(Task task) {
        taskList.add(task);
        printReply(String.format("Got it. I've added this task:\n  %s %s\nNow you have %d tasks in the list.",
            task.getStatusIcon(), task.getDescription(), taskList.size()));
        processReply(sc.nextLine());
    }

    /**
     * Evaluate a todo command.
     * And add a todo task to the task list.
     *
     * @param command the user command.
     */
    private void addTodoCommand(String command) throws DukeException{
        if (command.length() <= 5) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        addTask(new ToDo(command.substring(5)));
    }

    /**
     * Evaluate a deadline command.
     * And add a deadline task to the task list.
     *
     * @param command the user command.
     */
    private void addDeadlineCommand(String command) throws DukeException{
        if (command.length() <= 9 || !command.contains("/by")) {
            throw new DukeException("OOPS!!! The format of the deadline is incorrect.\n" +
                    "eg. deadline read book /by Friday");
        }
        String parameter = command.substring(9);
        String[] details = parameter.split("/by");
        addTask(new Deadline(details[0], details[1]));
    }

    /**
     * Evaluate an event command.
     * And add a event task to the task list.
     *
     * @param command the user command.
     */
    private void addEventCommand(String command) throws DukeException{
        if (command.length() <= 6 || !command.contains("/at")) {
            throw new DukeException("OOPS!!! The format of the event is incorrect.\n" +
                    "eg. event CS2103T lecture /at Thursday, 1600hr");
        }
        String parameter = command.substring(6);
        String[] details = parameter.split("/at");
        addTask(new Event(details[0], details[1]));
    }

    /**
     * Evaluate a done command.
     * Mark a given task as done.
     *
     * @param command the user command.
     */
    private void doneCommand(String command) throws DukeException{
        String[] details = command.split(" ");
        if (details.length < 2) {
            //missing parameter
            throw new DukeException("OOPS!!! Did you forget the task number?");
        }
        if (!details[1].matches("\\d+")) {
            //invalid parameter
            throw new DukeException("OOPS!!! Invalid task number.");
        }
        int taskID = Integer.parseInt(details[1]);
        if (taskID > taskList.size()) {
            //task does not exist
            throw new DukeException(String.format("Task %d does not exist.\nUse \"list\" to see all tasks.", taskID));
        }
        Task task = taskList.get(taskID - 1);
        task.markAsDone();
        printReply(String.format("Nice! I've marked this task as done: \n  %s %s",
                task.getStatusIcon(), task.getDescription()));
        processReply(sc.nextLine());
    }

    private void deleteCommand(String command) throws DukeException{
        String[] details = command.split(" ");
        if (details.length < 2) {
            //missing parameter
            throw new DukeException("OOPS!!! Did you forget the task number?");
        }
        if (!details[1].matches("\\d+")) {
            //invalid parameter
            throw new DukeException("OOPS!!! Invalid task number.");
        }
        int taskID = Integer.parseInt(details[1]);
        if (taskID > taskList.size()) {
            //task does not exist
            throw new DukeException(String.format("Task %d does not exist.\nUse \"list\" to see all tasks.", taskID));
        }
        Task task = taskList.get(taskID - 1);
        taskList.remove(task);
        printReply(String.format("Noted. I've removed this task:\n  %s %s\nNow you have %d tasks in the list.",
                task.getStatusIcon(), task.getDescription(), taskList.size()));
        processReply(sc.nextLine());
    }

    /**
     * Process a given input and generate a reply
     * @param text The user input.
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
        greetUser();
        processReply(sc.nextLine());
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
