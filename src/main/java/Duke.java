import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that keeps track of various tasks.
 *
 * @author Adam Oh Zhi Hong
 */
public class Duke {
    private TaskList tasklist;
    private String filePath;

    private Duke(String filePath) {
        this.tasklist = new TaskList();
        this.tasklist.start();
        this.filePath = filePath;
        try {
            this.tasklist.getTasksFromStorage(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private void run() {
        // Welcome the user
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Stay on track with Duke!\n" +
                "How can I help you?");

        boolean running = true;
        Scanner sc = new Scanner(System.in);

        // Start taking input from the user
        while (running) {
            System.out.println("Enter your command:");
            String command = sc.nextLine();

            // Handles basic user input such as list and exit
            // All other commands are handled by TaskManager
            switch (command) {
                case "bye":
                    System.out.println("¡Adiós! See you soon!");
                    tasklist.saveTasksToStorage(filePath);
                    running = false;
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

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}

