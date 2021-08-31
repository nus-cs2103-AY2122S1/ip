package kayu;

import java.util.List;

import kayu.commands.Command;
import kayu.exception.DukeException;
import kayu.exception.StorageException;
import kayu.parser.Parser;
import kayu.service.Logger;
import kayu.service.TaskList;
import kayu.storage.Storage;
import kayu.task.Task;

/**
 * Holds the main logic for Kayu project (CS2103T's iP).
 * Kayu is a task manager for users that love the command line interface.
 * Code structure inspiration from <code>se-edu</code>'s addressbook-level2 code on GitHub
 * <a href="https://github.com/se-edu/addressbook-level2">addressbook-level2</a>.
 * @author muhammad-khair, damithc, j-lum
 */
public class Kayu {

    private static final String GREETING = "Hello!\n"
            + "I'm Kayu, your alternative personal task management to Duke!\n"
            + "What can I do for you?";
    
    private final Parser parser = new Parser();
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();
    private final Logger logger = new Logger();
    
    private boolean isRecentCommandBye = false;

    /**
     * Returns whether the recent command parsed and executed is a {@link kayu.commands.ByeCommand}.
     *
     * @return Boolean true if recent command is a {@link kayu.commands.ByeCommand}, else false.
     */
    public boolean isRecentCommandBye() {
        return isRecentCommandBye;
    }

    /**
     * Returns the greeting message for user.
     *
     * @return A String greeting message.
     */
    public String getGreeting() {
        return GREETING;
    }

    /**
     * Initializes the data stored in file to taskList.
     */
    public void initialize() {
        try {
            logger.printLogo();
            logger.printMessage(GREETING);
            List<Task> tasks = storage.load();
            taskList.initializeTasks(tasks);
            
        } catch (StorageException exception) {
            logger.printError(exception.getMessage());
            exception.printStackTrace();
            exit(); // force terminate
        }
    }

    /**
     * Executes the command fed by user and returns the response string.
     *
     * @param userInput User input string to parse and execute.
     * @return A String response from the parsing and execution of the command.
     */
    public String getResponse(String userInput) {
        String feedback;
        Command command = parser.parseToCommand(userInput);
        isRecentCommandBye = (command.isBye()); // updates internally as a field
        
        try {
            feedback = command.execute(taskList, storage);
            logger.printMessage(feedback);

        } catch (DukeException exception) {
            feedback = exception.getMessage();
            logger.printError(feedback);

        } catch (StorageException exception) {
            feedback = exception.getMessage();
            logger.printError(feedback);
            exception.printStackTrace();
            exit(); // force terminate
        }
        return feedback;
    }

    /**
     * Exits the whole program.
     */
    public void exit() {
        try {
            Thread.sleep(300); // sleep for 0.3s
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.exit(0);
    }
}
