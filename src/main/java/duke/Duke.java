package duke;

import java.time.format.DateTimeParseException;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.MissingTaskNameException;
import duke.exceptions.MissingTaskNumberException;
import duke.exceptions.InvalidTaskNumberException;
import duke.exceptions.MissingDeadlineException;
import duke.exceptions.MissingEventTimeException;
import duke.exceptions.DukeException;

/**
 * Duke is a Personal Assistant Chatbot that keeps track of various tasks.
 *
 * @author Adam Oh Zhi Hong
 */
public class Duke {
    /** A class to keep track of all tasks of the Duke instance **/
    private TaskList taskList;

    /**
     * Initializes a new Duke instance with specified filepath to store tasks
     *
     * @param filePath Path to storage file
     */
    public Duke(String filePath) {
        this.taskList = new TaskList(filePath);
    }

    /**
     * Entry point of Duke
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Starts an instance of Duke
     */
    public void run() {
        // Welcome the user
        UI.welcome();
        boolean running = true;

        while (running) {
            // Get input from the user
            String command = UI.readCommand();
            try {
                Parser.handle(command, taskList);
                running = Parser.isRunning;
            } catch (InvalidCommandException e) {
                System.out.println("I'm afraid I don't recognise that, please try again!");
            } catch (MissingTaskNameException e) {
                System.out.println("Duke.Task name cannot be empty!");
            } catch (MissingTaskNumberException e) {
                System.out.println("Did you forget to enter your task number?");
            } catch (InvalidTaskNumberException e) {
                System.out.println("Sorry, that task does not exist!");
            } catch (MissingDeadlineException e) {
                System.out.println("When is that due? Let me know after '/by'!");
            } catch (MissingEventTimeException e) {
                System.out.println("When is the event happening? Let me know after '/at'!");
            } catch (DateTimeParseException e) {
                System.out.println("Oops, did you enter your date in yyyy-mm-dd format?");
            } catch (DukeException e) {
                UI.dukeException();
            }
        }
    }
}

