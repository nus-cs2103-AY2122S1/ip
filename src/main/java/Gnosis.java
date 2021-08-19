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
        String userInput;
        tasks = new ArrayList<>();

        // Display greeting message
        displayTopDivider();
        System.out.println(GREET_MESSAGE);
        displayBottomDivider();

        // terminates user input only when "bye" is inputted by user
        do {
            userInput = sc.nextLine();

            // display and execute commands
            displayTopDivider();
            executeCommand(userInput);
            displayBottomDivider();

        } while (!userInput.equalsIgnoreCase("BYE"));

        sc.close(); // close scanner
    }


    /**
     * Executes user commands
     * */
    public static void executeCommand(String input) {

        // split inputs by " " to retrieve list index
        String[] commands = input.split(" ");
        int len = commands.length;
        String command = commands[0];

        // only if "done" command is call, we retrieve task index from user
        int taskIndex = 0;
        if (command.equalsIgnoreCase("done") && len > 1) {
            taskIndex = Integer.parseInt(commands[1]) - 1;
        }

        // convert command to lower case to avoid case issues
        switch (command.toLowerCase()) {
            case "list":
                listCommand();
                break;
            case "done":
                doneCommand(taskIndex);
                break;
            case "bye":
                byeCommand();
                break;
            default:
                addCommand(input);
        }
    }

    //Corresponding user command methods

    public static void addCommand(String text) {
        tasks.add(new Task(text));
        System.out.println("item saved: " + text);
    }

    public static void listCommand() {
        int len = tasks.size();
        System.out.println("Listing all tasks in your list:");
        for (int i = 0; i < len; i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
    }

    public static void doneCommand(int taskIndex) {
        tasks.get(taskIndex).setDone(true);
        System.out.println("Task " + (taskIndex+1) +" marked as done:" );
        System.out.println("\t" + tasks.get(taskIndex));
    }

    public static void byeCommand() {
        System.out.println(BYE_MESSAGE);
    }

    //Utility methods to display divider for display format

    public static void displayTopDivider() {
        System.out.println("-- \t============\t --");
    }
    public static void displayBottomDivider() {
        System.out.println("-- \t============\t --" + '\n');
    }
}
