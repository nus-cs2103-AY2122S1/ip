import java.util.Scanner;

/**
 * This class implements a Duke Chatbot variant: Duchess
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class Duchess {
    /** The horizontal bars to add style in the output.*/
    private static final String HORIZONTAL_BARS = "\n____________________________________________________________\n";
    /** The duchessList which holds the string stored by the user.*/
    private DuchessList duchessList;

    /**
     * Constructor for Duchess object
     */
    public Duchess()
    {
        duchessList = new DuchessList();
    }

    public static void main(String[] args) {
        Duchess duchess = new Duchess();
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
        System.out.println(HORIZONTAL_BARS + input + HORIZONTAL_BARS);
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
        try {
            if (input.equals("list"))
                prettyPrint(duchessList.printList());
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
        } catch (DuchessException d) {
            prettyPrint(d.getMessage());
        }
        // Continue to read for inputs unless "bye" is called
        handleInput(sc);
    }

    /**
     * Handles the logic for marking a task as done
     * @param input the user given input
     * @throws DuchessException the exception thrown when input does not match the done format
     */
    public void handleDone(String input) throws DuchessException {
        String index = input.split(" ", 2)[1];
        // Parsing a non-numeric string will throw a NumberFormatException
        try {
            if (duchessList.checkWithinRange(Integer.parseInt(index))) {
                // Valid done task
                Task task = duchessList.getTask(Integer.parseInt(index));
                if (task.isDone)
                    throw new DuchessException("Oops... This task is already done.");
                task.setDone(true);
                prettyPrint("Brilliant! I've marked this task as done:   \n  " + task);
            } else {
                // "done" followed by an integer outside of range of the list
                throw new DuchessException("Apologies, that task does not exist and cannot be marked as done.");
            }
        } catch (NumberFormatException e) {
            // "done" followed by an invalid non-integer string input
            throw new DuchessException("The command \"done\" should be followed by an integer.");
        }
    }

    /**
     * Handles the logic for checking and creating ToDo tasks
     * @param input the user given input
     * @throws DuchessException the exception thrown when input does not match the todo format
     */
    public void handleTodo(String input) {
        String task = input.split(" ", 2)[1];
        // Valid input
        ToDo todo = new ToDo(task);
        duchessList.add(todo);
        int listSize = duchessList.getSize();
        prettyPrint("Understood. I've added this task:\n    " + todo
                + "\nYou now have " + listSize
                    + (listSize > 1 ? " tasks in the list." : " task in the list."));
    }

    /**
     * Handles the logic for checking and creating Deadline tasks
     * @param input the user given input
     * @throws DuchessException the exception thrown when input does not match the deadline format
     */
    public void handleDeadline(String input) throws DuchessException {
        String invalidMessage = "The command \"deadline\" should be followed by " +
                "a task and a deadline, e.g (read book /by Sunday).";
        String taskAndBy = input.split(" ", 2)[1];
        if (!taskAndBy.contains(" /by "))
            throw new DuchessException(invalidMessage);
        String[] taskParts = taskAndBy.split(" /by ", 2);
        String checkTask = taskParts[0];
        String checkBy = taskParts[1];
        if (checkBy.equals(""))
            throw new DuchessException(invalidMessage);
        // Valid input
        Deadline deadline = new Deadline(checkTask, checkBy);
        duchessList.add(deadline);
        int listSize = duchessList.getSize();
        prettyPrint("Understood. I've added this task:\n    " + deadline
                + "\nYou now have " + listSize
                + (listSize > 1 ? " tasks in the list." : " task in the list."));
    }

    /**
     * Handles the logic for checking and creating Event tasks
     * @param input the user given input
     * @throws DuchessException the exception thrown when input does not match the event format
     */
    public void handleEvent(String input) throws DuchessException {
        String invalidMessage = "The command \"event\" should be followed by " +
                "a task and a duration, e.g (meeting /at Mon 2-4pm).";
        String taskAndDuration = input.split(" ", 2)[1];
        if (!taskAndDuration.contains(" /at "))
            throw new DuchessException(invalidMessage);
        String[] taskParts = taskAndDuration.split(" /at ", 2);
        String task = taskParts[0];
        String time = taskParts[1];
        if (!time.contains(" "))
            throw new DuchessException(invalidMessage);
        String[] timeParts = time.split(" ", 2);
        String day = timeParts[0];
        String duration = timeParts[1];
        if (!duration.contains("-"))
            throw new DuchessException(invalidMessage);
        // Valid input
        Event event = new Event(task, day, duration);
        duchessList.add(event);
        int listSize = duchessList.getSize();
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
     * @throws DuchessException the exception thrown when the prefix is preceded by an empty string
     * @return whether the prefix string at the front of the string
     */
    public boolean checkPrefix(String input, String prefix) throws DuchessException {
        String[] parts = input.split(" ", 2);
        String front = parts[0];
        if (front.equals(prefix))
            try {
                String back = parts[1];
                if (back.isBlank())
                    throw new DuchessException("The description of " + prefix + " cannot be empty.");
                return true;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DuchessException("The description of " + prefix + " cannot be empty.");
            }
        return false;
    }
}
