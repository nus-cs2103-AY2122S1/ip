package kayu;

import java.util.List;

import kayu.commands.Command;
import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.note.Note;
import kayu.parser.Parser;
import kayu.service.Logger;
import kayu.service.NoteList;
import kayu.service.TaskList;
import kayu.storage.NoteStorage;
import kayu.storage.TaskStorage;
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
    private final NoteList noteList = new NoteList();
    private final Logger logger = new Logger();
    private final TaskStorage taskStorage = TaskStorage.generate();
    private final NoteStorage noteStorage = NoteStorage.generate();

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
            initializeTasks();

        } catch (StorageException exception) {
            logger.printError(exception.getMessage());
            exception.printStackTrace();
            exit(); // force terminate
        }
    }

    private void initializeTasks() throws StorageException {
        List<Task> tasks = taskStorage.load();
        List<Note> notes = noteStorage.load();
        taskList.initializeTasks(tasks);
        noteList.initializeNotes(notes);
    }

    /**
     * Executes the command fed by user and returns the response string.
     *
     * @param userInput User input string to parse and execute.
     * @return A String response from the parsing and execution of the command.
     */
    public String executeAndRespond(String userInput) {
        String feedback;
        Command command = parser.parseToCommand(userInput);
        isRecentCommandBye = (command.isBye()); // updates internally as a field

        try {
            feedback = command.execute(taskList, taskStorage, noteList, noteStorage);
            logger.printMessage(feedback);

        } catch (KayuException exception) {
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
