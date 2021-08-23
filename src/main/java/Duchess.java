import java.time.LocalDate;
import java.util.Scanner;
import java.time.LocalDateTime;

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
     * The commands recognised by Duchess
     */
    private enum Command {
        BYE ("bye"),
        LIST ("list"),
        DONE ("done"),
        TODO ("todo"),
        DEADLINE ("deadline"),
        EVENT ("event"),
        DELETE ("delete"),
        TASKS ("tasks"),
        INVALID (null);
        private String commandName;
        Command(String commandName) {
            this.commandName = commandName;
        }
    }

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
        try {
            Command c = checkPrefix(input);
            switch (c) {
            case BYE:
               prettyPrint("I bid thee farewell.");
                return; // stop prompting user input
            case LIST:
                prettyPrint(duchessList.printList());
                break;
            case DONE:
                handleDone(input);
                break;
            case TODO:
                handleTodo(input);
                break;
            case DEADLINE:
                handleDeadline(input);
                break;
            case EVENT:
                    handleEvent(input);
                break;
            case DELETE:
                handleDelete(input);
                break;
            case TASKS:
                handleTasks(input);
                break;
            case INVALID:
                printError();
                break;
            }
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
                "a task and a date and time, e.g (read book /by 11/10/2019 4pm).";
        String taskAndBy = input.split(" ", 2)[1];
        if (!taskAndBy.contains(" /by "))
            throw new DuchessException(invalidMessage);
        String[] taskParts = taskAndBy.split(" /by ", 2);
        String checkTask = taskParts[0];
        String checkBy = taskParts[1];
        if (checkBy.equals(""))
            throw new DuchessException(invalidMessage);
        // Valid input
        Deadline deadline = new Deadline(checkTask, Deadline.convertStringToDate(checkBy));
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
                "a task and a date and time, e.g (meeting /at 2/10/2019 2pm-4pm).";
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
        LocalDateTime[] events = Event.convertStringToDate(day, duration);
        Event event = new Event(task, events[0], events[1]);
        duchessList.add(event);
        int listSize = duchessList.getSize();
        prettyPrint("Understood. I've added this task:\n    " + event
                + "\nYou now have " + listSize
                + (listSize > 1 ? " tasks in the list." : " task in the list."));
    }

    /**
     * Handles the logic fpr checking and deleting tasks
     * @param input the user given input
     * @throws DuchessException the exception thrown when input does not match the deletion format
     */
    public void handleDelete(String input) throws DuchessException {
        String index = input.split(" ", 2)[1];
        // Parsing a non-numeric string will throw a NumberFormatException
        try {
            if (duchessList.checkWithinRange(Integer.parseInt(index))) {
                // Valid delete task
                Task deletedTask = duchessList.delete(Integer.parseInt(index));
                prettyPrint("Alright. I've removed this task:   \n  " + deletedTask
                    + "\nNow you have " + duchessList.getSize() + " tasks in the list.");
            } else {
                // "delete" followed by an integer outside of range of the list
                throw new DuchessException("Apologies, that task does not exist and cannot be deleted.");
            }
        } catch (NumberFormatException e) {
            // "delete" followed by an invalid non-integer string input
            throw new DuchessException("The command \"delete\" should be followed by an integer.");
        }
    }

    /**
     * Handles the logic for printing tasks that match a specified time.
     * @param input The user given input.
     * @throws DuchessException The exception thrown when input does not match the tasks format.
     */
    public void handleTasks(String input) throws DuchessException {
        String invalidMessage = "The command \"tasks\" should be followed by " +
                "a keyword \"/after\" or \"/before\", a date and/or a time (e.g before 2/10/2019 2pm" +
                "or after today)";
        try {
            String[] timeSplit = input.substring(6).split(" ", 2);
            String keyword = timeSplit[0];
            String date = timeSplit[1];
            boolean isBefore = false;
            if (keyword.equals("/before"))
                isBefore = true;
            else if (keyword.equals("/after"))
                isBefore = false;
            else {
                System.out.println(keyword + "\n" + date);
                throw new DuchessException(invalidMessage);
            }
            LocalDateTime dateTime = date.equals("today") ? LocalDateTime.now()
                    : Deadline.convertStringToDate(date);
            String tasksToPrint = "";
            for (int i = 1; i < duchessList.getSize() + 1; i++) {
                Task t = duchessList.getTask(i);
                if (isBefore && t.getDateTime().isBefore(dateTime))
                    tasksToPrint += t.toString() + "\n";
                else if (!isBefore && t.getDateTime().isAfter(dateTime))
                    tasksToPrint += t.toString() + "\n";
            }
            prettyPrint(tasksToPrint.isBlank() ? "You have no tasks " + keyword.substring(1) + " " + date
                    : tasksToPrint);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new DuchessException(invalidMessage);
        }
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
     * @throws DuchessException the exception thrown when the prefix is preceded by an empty string
     * @return the prefix enum present at the front of the string
     */
    public Command checkPrefix(String input) throws DuchessException {
        String[] parts = input.split(" ", 2);
        String front = parts[0];
        // Check if the prefix matches any command recognised by Duchess
        for (Command c : Command.values()) {
            if (front.equals(c.commandName))
                try {
                    if (front.equals("bye") || front.equals("list"))
                        return c; // No need second argument
                    String back = parts[1]; // May throw ArrayIndexOutOfBoundsException
                    if (back.isBlank()) // Second argument is only whitespaces
                        throw new DuchessException("The description of " + front + " cannot be empty.");
                    return c;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DuchessException("The description of " + front + " cannot be empty.");
                }
        }
        // No command recognised
        return Command.INVALID;
    }
}
