import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static Scanner sc;
    private static ArrayList<Task> list;
    private enum TaskAction {
        DELETE("delete", 7, "deleted"),
        DONE("done", 5, "marked as done");

        // Name of the task action
        private final String name;

        // At what index to substring the input so as to remove the first word
        private final int substringIndex;

        // The message fragment when the action is successfully completed
        private final String successMessage;

        TaskAction(String name, int substringIndex, String successMessage) {
            this.name = name;
            this.substringIndex = substringIndex;
            this.successMessage = successMessage;
        }
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        list = new ArrayList<>();

        // Show the logo
        showLogo();

        // Gets the user input
        getInput();
    }

    /**
     * Prints the introduction and logo.
     */
    private static void showLogo() {
        String logo = " _______       ___      _______   __     __   _\n"
                    + "|   ____|     / ^ \\     |   _  \\  \\ \\   / /  | |\n"
                    + "|  | ___     / /_\\ \\    |  |_|  |  \\ \\ / /   | |\n"
                    + "|  ||_  |   /  ___  \\   |  __  <    \\   /    |_|\n"
                    + "|  |__| |  /  /   \\  \\  |  | \\  \\    | |      _ \n"
                    + "|_______| /__/\t   \\__\\ |--|  \\--\\   |_|     |_|\n";

        printDoubleDivider();
        System.out.println("Hello! My name is\n" + logo);
        printDoubleDivider();
        System.out.println("How may I help you?");
        printDoubleDivider();
    }

    /**
     * Prompts the user for input.
     */
    private static void getInput() {
        System.out.print("Input: ");
        handleInput(sc.nextLine());
    }

    /**
     * Takes user input and handles it appropriately.
     *
     * @param input the input string from the Scanner
     */
    private static void handleInput(String input) {
        printSingleDivider();

        if (input.equals("bye")) {

            System.out.println("Output: Goodbye! See you again!");
            printDoubleDivider();
            sc.close();
            return;

        } else if (input.equals("list")) {

            System.out.println("Output: This is your current list!\n");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + ". " + list.get(i));
            }

        } else if (input.startsWith("done")) {

            alterTask(input, TaskAction.DONE);

        } else if (input.startsWith("delete")) {

            // If successfully deleted task, then print current number of tasks.
            if (alterTask(input, TaskAction.DELETE))
                printNumberOfTasks();

        } else {

            vetoTask(input);

        }

        printDoubleDivider();
        getInput();
    }

    /**
     * Adds the Task into the task list.
     *
     * @param newTask the Task to be added into the task list.
     */
    private static void addTask(Task newTask) {
        list.add(newTask);
        System.out.println("Output:\n\nYou have successfully added the following task:\n\n" +
                            "    " + newTask);
        printNumberOfTasks();
    }

    /**
     * Checks if the input contains the description for the task to be added.
     * Throws a NoDescriptionException otherwise.
     *
     * @param input the input string from the Scanner.
     * @param task the name of the Task attempting to be added into the task list.
     *
     * @throws NoDescriptionException if the description of the task is empty.
     */
    private static void checkDescription(String input, String task) throws NoDescriptionException {
        if (input.split(" ").length == 1) throw new NoDescriptionException(task);
    }

    /**
     * Alters the task depending on the TaskAction given, which could either be delete or done.
     *
     * @param input the input string from the Scanner.
     * @param action the TaskAction to be done.
     * @return true if the task is successfully altered, false otherwise.
     */
    private static boolean alterTask(String input, TaskAction action) {

        try {
            Task taskToBeAltered;

            int index = Integer.parseInt(input.substring(action.substringIndex)) - 1;

            if (action == TaskAction.DELETE) {          // If delete
                taskToBeAltered = list.remove(index);
            } else {                                    // If done
                taskToBeAltered = list.get(index);
                taskToBeAltered.markAsDone();
            }

            System.out.println("Output:\n\nThis task is successfully " + action.successMessage + ":\n\n" +
                                "    " + taskToBeAltered);
            return true;
        } catch (StringIndexOutOfBoundsException | NumberFormatException e1) {

            System.out.println("Output: Please specify which task you would like to have\n" +
                                action.successMessage + " by adding a single number after '" + action.name + "'!\n" +
                                "i.e. " + action.name + " 1");
            return false;

        } catch (IndexOutOfBoundsException e2) {

            System.out.println("Output: There is no task under that number!");
            return false;

        }

    }

    /**
     * Checks the task that is attempting to be added into the task list.
     * If the input contains the appropriate information, the task is added.
     *
     * @param input the input string from the Scanner.
     */
    private static void vetoTask(String input) {
        Task newTask = null;

        try {
            if (input.startsWith("todo")) {

                // Check if a task description is present
                checkDescription(input, "todo");

                // Set the to-do
                newTask = setTodo(input.substring(5));

            } else if (input.startsWith("deadline")) {

                // Check if a task description is present
                checkDescription(input, "deadline");

                // Set the deadline
                newTask = setDeadline(input.substring(9));

            } else if (input.startsWith("event")) {

                // Check if a task description is present
                checkDescription(input, "event");

                // Set the event
                newTask = setEvent(input.substring(6));

            } else {

                throw new InvalidInputException();

            }
        } catch (NoDescriptionException | InvalidParamException | InvalidInputException e1) {
            System.out.println("Output: " + e1.getMessage());
        }

        // If there was no error, then add task. Else, skip this to get input again.
        if (newTask != null) addTask(newTask);
    }

    /**
     * Returns a to-do task based on the given description.
     *
     * @param input the string containing the to-do task description.
     * @return the to-do task constructed from the given description.
     */
    private static Task setTodo(String input) {
        Task todo = new Todo(input);
        return todo;
    }

    /**
     * Returns a deadline task based on the given description.
     *
     * @param input the string containing the deadline task description.
     * @return the deadline task constructed from the given description.
     * @throws InvalidParamException if the description does not contain the appropriate information.
     */
    private static Task setDeadline(String input) throws InvalidParamException {
        String[] deadlineParams = input.split(" /by ");
        if (deadlineParams.length != 2) {
            throw new InvalidParamException("Please include the deadline of the task after\n" +
                                            "a task description using a '/by' (only once).\n" +
                                            "i.e. deadline return book /by Monday");
        }
        Task deadline = new Deadline(deadlineParams[0], deadlineParams[1]);
        return deadline;
    }

    /**
     * Returns an event task based on the given description.
     *
     * @param input the string containing the event task description.
     * @return the event task constructed from the given description.
     * @throws InvalidParamException if the description does not contain the appropriate information.
     */
    private static Task setEvent(String input) throws InvalidParamException {
        String[] eventParams = input.split(" /at ");
        if (eventParams.length != 2) {
            throw new InvalidParamException("Please include the time of the event after\n" +
                                            "a task description using an '/at' (only once).\n" +
                                            "i.e. event project meeting /at Aug 6th 2-4pm");
        }
        Task event = new Event(eventParams[0], eventParams[1]);
        return event;
    }

    /**
     * Prints the number of tasks currently in the task list.
     */
    private static void printNumberOfTasks() {
        System.out.println("\nYou now have " + list.size() + (list.size() == 1 ? " task " : " tasks ") + "in your list!");
    }

    /**
     * Prints a divider using "=".
     */
    private static void printDoubleDivider() {
        System.out.println("\n=================================================\n");
    }

    /**
     * Prints a divider using "-".
     */
    private static void printSingleDivider() {
        System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - -\n");
    }

}

