import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * Gnosis class is the main programme to execute chatbot assistant.
 * Commands Gnosis can provide a task tracker to user:
 * "list" - displays all tasks
 * "done (task number)" - marks specified task as done
 * "bye" - exits program
 * default - adds user input as task
 *
 * @author Pawandeep Singh
 * @version Level-3
 *
 * */
public class Gnosis {

    private static final String GREET_MESSAGE = "Welcome, I am Gnosis.\n" +
            "The spark to meet your needs.\nHow can I assist you ?";

    private static final String BYE_MESSAGE = "Good bye.\nI hope your needs have been sparked.\n" +
            "I welcome you back soon.";

    private static ArrayList<Task> tasks;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        String input;
        tasks = new ArrayList<>();

        // Display greeting message
        displayTopDivider();
        System.out.println(GREET_MESSAGE);
        displayBottomDivider();

        // terminates user input only when "bye" is inputted by user
        do {
            command = sc.next();
            input = sc.nextLine();

            // display and execute commands
            displayTopDivider();
            executeCommand(command, input);
            displayBottomDivider();

        } while (!command.equalsIgnoreCase("BYE"));

        sc.close(); // close scanner
    }


    /**
     * Executes user commands
     * */
    public static void executeCommand(String command, String input) {



        // convert command to lower case to avoid case issues
        switch (command.toLowerCase()) {
            case "todo":
                addTodo(input);
                displayNumOfTasks();
                break;
            case "deadline":
                addDeadline(input);
                displayNumOfTasks();
                break;
            case "event":
                addEvent(input);
                displayNumOfTasks();
                break;
            case "list":
                listTasks();
                break;
            case "done":
                // only if "done" command is call, we retrieve task index from user
                int taskIndex = Integer.parseInt(input.trim()) - 1;
                MarkTaskAsDone(taskIndex);
                break;
            case "bye":
                displayByeMessage();
                break;
            default:
                displayHelpMessage();
        }
    }

    //Corresponding user command methods

    public static void addTodo(String todo) {
        Todo td = new Todo(todo);
        tasks.add(td);
        System.out.println("Todo added:");
        System.out.println(td);
    }

    public static void addDeadline(String deadlineInput) {
        String[] splitTaskInput = deadlineInput.split("/by");
        String taskName = splitTaskInput[0];
        String taskDeadline = splitTaskInput[1];

        Deadline dl = new Deadline(taskName, taskDeadline);
        tasks.add(dl);
        System.out.println("Deadline added:");
        System.out.println(dl);

    }

    public static void addEvent(String eventInput) {
        String[] splitTaskInput = eventInput.split("/at");
        String taskName = splitTaskInput[0];
        String taskSchedule = splitTaskInput[1];

        Event et = new Event(taskName,taskSchedule);
        tasks.add(et);
        System.out.println("Event added:");
        System.out.println(et);
    }

    public static void listTasks() {
        int len = tasks.size();
        System.out.println("Listing all tasks in your list:");
        for (int i = 0; i < len; i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
    }

    public static void MarkTaskAsDone(int taskIndex) {
        tasks.get(taskIndex).setDone(true);
        System.out.println("Task " + (taskIndex+1) +" marked as done:" );
        System.out.println("\t" + tasks.get(taskIndex));
    }


    //Utility methods for output

    public static void displayTopDivider() {
        System.out.println("-- \t============\t --");
    }
    public static void displayBottomDivider() {
        System.out.println("-- \t============\t --" + '\n');
    }

    public static void displayByeMessage() {
        System.out.println(BYE_MESSAGE);
    }
    public static void displayHelpMessage() {
        System.out.println("COMMAND NOT FOUND.\nPlease try another command.");
    }

    public static void displayNumOfTasks() {
        System.out.println("Total tasks in the list: " + tasks.size());
    }
}
