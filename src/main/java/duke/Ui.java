package duke;

import commands.Command;
import commands.CommandStack;
import commands.UndoableCommand;
import tasks.Task;


/**
 * Class responsible for interacting with users by collecting user's input and displaying
 * messages to users.
 */
public class Ui {

    public static final String DASHES = "-------------------------------------------------\n";
    /** System messages */
    public static final String DEADLINE_FORMAT = "deadline {Deadline name} -by {Date to be completed by}.\n"
            + "Use \"/by\" to specify that the input date is a formatted date.";
    public static final String EVENT_FORMAT = "event {Event name} -at {Date of event}.\n"
            + "Use \"/at\" to specify that the input date is a formatted date.";
    public static final String TODO_FORMAT = "todo {Todo name}";
    public static final String DATE_FORMAT = "dd/MM/yyyy or dd/MM/yyyy HHmm";

    private Parser parser;
    private boolean isExit;
    private CommandStack commandStack;

    /**
     * Starts up the chat bot. It prints a welcome message for the user and then waits
     * for a user input. It will then evaluate and execute inputs given by the user until the
     * user ends the chat. It is the main driver for the chat.
     */
    protected void startDuke() {
        System.out.println(getStartMessage());
        this.commandStack = new CommandStack();
        this.parser = new Parser(this.commandStack);
        Storage.initialise(this.parser.getTaskList());
    }

    /**
     * Takes a user input and feeds it to the parser in order to retrieve the appropriate command.
     * Then execute the command and return the execution text that is to be displayed to the user.
     *
     * @param input The user input that is to be passed to the parser.
     * @return The execution message that is to be displayed by the user.
     */
    public String readUserInput(String input) {
        Command command = this.parser.parseUserInput(input);
        boolean commandExecutedSuccessfully = command.execute();
        if (commandExecutedSuccessfully && command instanceof UndoableCommand) {
            commandStack.addCommand((UndoableCommand) command);
        } else if (command.isExit()) {
            this.isExit = true;
        }
        return command.getExecutionMessage();
    }

    /**
     * Returns the start message that is displayed to the user when the user first
     * opens Duke.
     *
     * @return The start message as a String.
     */
    public static String getStartMessage() {
        return "Hello, I'm Duke\n" + "What can I help you with?";
    }

    /**
     * Prints an error message when the user wishes to add a task but provides an invalid input. The message
     * tells the user that an error has occurred and how to correctly the input in order to add
     * the respective task.
     *
     * @param type The type of task inputted by the user.
     * @return A message indicating the correct format to enter a task that is of type <code>type</code>.
     */
    public static String getErrorMessage(Task.Type type) {
        assert type != null : "Task type is null";
        if (type == Task.Type.TODO) {
            return "Invalid format. Please enter the todo format as below:\n" + Ui.TODO_FORMAT + "\n";
        } else if (type == Task.Type.DEADLINE) {
            return "Invalid format. Please enter the deadline format as below:\n" + Ui.DEADLINE_FORMAT + "\n";
        } else {
            return "Invalid format. Please enter the event format as below:\n" + Ui.EVENT_FORMAT + "\n";
        }
    }

    public boolean isExit() {
        return isExit;
    }
}
