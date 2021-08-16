import java.util.List;
import java.util.Scanner;

public class Duke {

    final static String EXIT = "bye";

    /**
     * Display formatted message.
     * @param message Message to be printed in console.
     */
    public static void display(String message) {
        System.out.println("    * * * * * * * * * * * * * * * * * * * *");
        System.out.println("    " + message);
        System.out.println("    * * * * * * * * * * * * * * * * * * * *\n");
    }

    /**
     * Display greeting message.
     */
    public static void greet() {
        display("Hi, I'm Sync-Me Sebby.\n    " +
                "I'm here to assist you with tracking and synchronizing of your personal tasks.\n    " +
                "Let me know how I can help?");
    }

    /**
     * Display exit message.
     */
    public static void exit() {
        display("Goodbye. See you again soon!");
    }

    /**
     * Display success message for adding task.
     * @param tasks The list of tasks.
     * @param task The individual task which can be Todo, Deadline or Event.
     */
    public static void displaySuccessMessage(TaskList tasks, Task task) {
        display("Got it. I've added this task: \n" + "      " + task + "\n    Now you have "
                + tasks.getLength() + (tasks.getLength() <= 1 ? " task" : " tasks") + " in the list.");
    }

    /**
     *
     * @param userInput user input command.
     * @param splitText the command to split by.
     * @param splitTime the time to split by.
     * @return an array consisting of the description and the time of the task.
     */
    public static String[] processUserInput(String userInput, String splitText, String splitTime) {
        // split the user input string
        String[] splitInput = userInput.split(splitText)[1].split(splitTime);
        String desc = splitInput[0].trim();
        String time = splitInput[1];
        String[] arr = {desc, time};
        return arr;
    }

    /**
     * Checks if the command given by user is valid.
     * @param userInput the user input provided by scanner.
     * @return true if the command is valid; false otherwise.
     */
    public static boolean isCommandValid(String userInput) {
        String[] expectedCommands = {"todo", "deadline", "event", "done", "list", "bye", "delete"};
        String userCommand = userInput.split(" ")[0];
        for (int i = 0; i < expectedCommands.length; i++) {
            if (userCommand.equals(expectedCommands[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if description is given for Todo, Deadline and Event tasks.
     * @param userInput the user input provided by scanner.
     * @return true if description is provided; false otherwise.
     */
    public static boolean isDescExists(String userInput) {
        String[] arr = userInput.split(" ");
        return arr.length >= 2;
    }

    public static void main(String[] args) {

        // initialize Scanner object
        Scanner scan = new Scanner(System.in);

        // initialize TaskList
        TaskList tasks = new TaskList();

        // print greeting message
        greet();

        // read user input
        String userInput = scan.nextLine();

        while (!userInput.equals(EXIT)) {
            if (isCommandValid(userInput)) {
                if (userInput.equals("list")) {
                    tasks.displayAllItems();
                } else if (userInput.startsWith("delete") && isDescExists(userInput)) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    Task task = tasks.getTask(taskNumber - 1);
                    tasks.removeTask(taskNumber - 1);
                    display("Gotchu mate. I've removed this task: \n" + "      " + task + "\n    Now you have "
                            + tasks.getLength() + (tasks.getLength() <= 1 ? " task" : " tasks") + " in the list.");
                } else if (userInput.startsWith("done") && isDescExists(userInput)) {
                    // get task number
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    Task task = tasks.getTask(taskNumber - 1);
                    // mark done as done
                    task.markAsDone();
                    display("Nice! This task is marked as done: \n" + "      " + task);
                } else if (userInput.startsWith("todo") && isDescExists(userInput)) {
                    // get task description
                    String desc = userInput.split("todo ")[1];
                    // add task to list
                    Todo todo = new Todo(desc);
                    tasks.add(todo);
                    displaySuccessMessage(tasks, todo);
                } else if (userInput.startsWith("deadline") && isDescExists(userInput)) {
                    String[] arr = processUserInput(userInput,"deadline ", "/by ");
                    Deadline dl = new Deadline(arr[0], arr[1]);
                    tasks.add(dl);
                    displaySuccessMessage(tasks, dl);
                } else if (userInput.startsWith("event") && isDescExists(userInput)) {
                    String[] arr = processUserInput(userInput,"event ", "/at ");
                    // add task to list
                    Event event = new Event(arr[0], arr[1]);
                    tasks.add(event);
                    displaySuccessMessage(tasks, event);
                } else {
                    display("OOPS! The description cannot be empty. Please try again.");
                }
            } else {
                display("OOPS! I do not understand what does that mean. Maybe you can try either one of " +
                        "[todo, deadline, event, done, list, bye, delete]?");
            }
            userInput = scan.nextLine();
        }

        scan.close();

        // print exit message
        exit();

    }

}
