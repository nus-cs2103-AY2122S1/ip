import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that keeps track of various tasks.
 *
 * @author Adam Oh Zhi Hong
 */
public class Duke {
    /** A class to keep track of all tasks of the Duke instance **/
    private TaskList tasklist;

    /**
     * Initializes a new Duke instance with specified filepath to store tasks
     * @param filePath Path to storage file
     */
    private Duke(String filePath) {
        this.tasklist = new TaskList(filePath);
    }

    /**
     * Entry point of Duke
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Starts an instance of Duke
     */
    private void run() {
        // Welcome the user
        UI.welcome();

        boolean running = true;
        Scanner sc = new Scanner(System.in);

        // Start taking input from the user
        while (running) {
            System.out.println("Enter your command:");
            String command = sc.nextLine();

            // Handles basic user input such as list and exit
            // All other commands are handled by Parser
            switch (command) {
            case "bye":
                running = false;
                tasklist.saveTasksToStorage();
                UI.bye();
                break;
            case "list":
                tasklist.list();
                break;
            default:
                try {
                    Parser.handle(command, tasklist);
                } catch (InvalidCommandException e) {
                    System.out.println("I'm afraid I don't recognise that, please try again!");
                } catch (MissingTaskNameException e) {
                    System.out.println("Task name cannot be empty!");
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
                    System.out.println("There seems to be a problem with Duke. " +
                            "Please try again!");
                }
            }
        }
    }
}

