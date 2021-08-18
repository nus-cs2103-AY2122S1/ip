import java.util.Scanner;

/**
 * This class implements a Duke Chatbot variant: Duchess
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class Duke {
    /** The horizontal bars to add style in the output.*/
    private static final String horizontalBars = "\n____________________________________________________________\n";
    /** The DukeList which holds the string stored by the user.*/
    private DukeList dukeList;

    /**
     * Constructor for Duke object
     */
    public Duke()
    {
        dukeList = new DukeList();
    }

    public static void main(String[] args) {
        Duke duchess = new Duke();
        Scanner sc = new Scanner(System.in);
        String name = "Duchess";
        prettyPrint("Good day. I am " + name + "\nWhat can I do for you?");
        duchess.handleInput(sc);
    }

    /**
     * Prints to System.out fancily including horizontal bars ontop and bottom
     * @param input string to be printed fancily
     */
    public static void prettyPrint(String input)
    {
        System.out.println(horizontalBars + input + horizontalBars);
    }

    /**
     * Gets input from user and PrettyPrints the corresponding response
     * @param sc scanner to be reused
     */
    public void handleInput(Scanner sc)
    {
        String input = sc.nextLine();
        if (input.equals("bye")) {
            prettyPrint("I bid thee farewell.");
            return;
        }
        else if (input.equals("list"))
            prettyPrint(dukeList.printList());
        else if (checkPrefix(input, "done"))
            handleDone(input);
        else if (checkPrefix(input, "todo"))
            handleTodo(input);
        else if (checkPrefix(input, "deadline"))
            handleDeadline(input);
        else if (checkPrefix(input, "event"))
            handleEvent(input);
        else
            printError();
        // Continue to read for inputs unless "bye" is called
        handleInput(sc);
    }

    /**
     * Handles the logic for marking a task as done
     * @param input the user given input
     */
    public void handleDone(String input) {
        String index = input.split(" ", 2)[1];
        // Parsing a non-numeric string will throw a NumberFormatException
        try {
            if (dukeList.checkWithinRange(Integer.parseInt(index))) {
                // Valid done task
                Task task = dukeList.getTask(Integer.parseInt(index));
                task.setDone(true);
                prettyPrint("Nice! I've marked this task as done:   \n  " + task);
            } else {
                // "done" followed by an integer outside of range of the list
                prettyPrint("Apologies, that task does not exist and cannot be marked as done.");
            }
        } catch (NumberFormatException e) {
            // "done" followed by an invalid non-integer string input
            prettyPrint("The command \"done\" should be followed by an integer.");
        }
    }

    /**
     * Handles the logic for checking and creating ToDo tasks
     * @param input the user given input
     */
    public void handleTodo(String input) {
        String task = input.split(" ", 2)[1];
        // Valid input
        ToDo todo = new ToDo(task);
        dukeList.add(todo);
        int listSize = dukeList.getSize();
        prettyPrint("Understood. I've added this task:\n    " + todo
                + "\nYou now have " + listSize
                    + (listSize > 1 ? " tasks in the list." : " task in the list."));
    }

    /**
     * Handles the logic for checking and creating Deadline tasks
     * @param input the user given input
     */
    public void handleDeadline(String input) {
        String invalidMessage = "The command \"deadline\" should be followed by " +
                "a task and a deadline, e.g (read book /by Sunday)";
        String taskAndBy = input.split(" ", 2)[1];
        if (!taskAndBy.contains(" /by ")) {
            prettyPrint(invalidMessage);
            return;
        }
        String[] taskParts = taskAndBy.split(" /by ", 2);
        String checkTask = taskParts[0];
        String checkBy = taskParts[1];
        if (checkBy.equals("")) {
            prettyPrint(invalidMessage);
            return;
        }
        // Valid input
        Deadline deadline = new Deadline(checkTask, checkBy);
        dukeList.add(deadline);
        int listSize = dukeList.getSize();
        prettyPrint("Understood. I've added this task:\n    " + deadline
                + "\nYou now have " + listSize
                + (listSize > 1 ? " tasks in the list." : " task in the list."));
    }

    /**
     * Handles the logic for checking and creating Event tasks
     * @param input the user given input
     */
    public void handleEvent(String input) {
        String invalidMessage = "The command \"event\" should be followed by " +
                "a task and a duration, e.g (meeting /at Mon 2-4pm)";
        String taskAndDuration = input.split(" ", 2)[1];
        if (!taskAndDuration.contains(" /at ")) {
            prettyPrint(invalidMessage);
            return;
        }
        String[] taskParts = taskAndDuration.split(" /at ", 2);
        String task = taskParts[0];
        String time = taskParts[1];
        if (!time.contains(" ")) {
            prettyPrint(invalidMessage);
            return;
        }
        String[] timeParts = time.split(" ", 2);
        String day = timeParts[0];
        String duration = timeParts[1];
        if (!duration.contains("-")) {
            prettyPrint(invalidMessage);
            return;
        }
        // Valid input
        Event event = new Event(task, day, duration);
        dukeList.add(event);
        int listSize = dukeList.getSize();
        prettyPrint("Understood. I've added this task:\n    " + event
                + "\nYou now have " + listSize
                + (listSize > 1 ? " tasks in the list." : " task in the list."));
    }

    /**
     * Prints a message given for invalid inputs
     */
    public void printError()
    {
        prettyPrint("Apologies, I didn't catch that.");
    }

    /**
     * Checks if a given string is present at the front of another string
     * @param input the string to be checked against
     * @param prefix the string to check
     * @return whether the prefix string at the front of the string
     */
    public boolean checkPrefix(String input, String prefix) {
        String[] parts = input.split(" ", 2);
        String front = parts[0];
        return front.equals(prefix);
    }
}
