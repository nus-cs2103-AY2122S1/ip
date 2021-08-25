import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {
    private final static String LINE = "\t----------------------------------------------------\n";
    private Storage storage;
    private static TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    public void run() {
        greetUser();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            try {
                if (command.startsWith("list")) {
                    tasks.printTasks();
                } else if (command.startsWith("done")) {
                    tasks.handleDone(command);
                } else if (command.startsWith("delete")) {
                    tasks.handleDelete(command);
                } else if (command.startsWith("todo")) {
                    tasks.addToDo(command);
                } else if (command.startsWith("deadline")) {
                    tasks.addDeadline(command);
                } else if (command.startsWith("event")) {
                    tasks.addEvent(command);
                } else {
                    throw new DukeException("I don't understand that command!\n");
                }
                storage.saveTasks(tasks.getTasks());
            } catch (DukeException e) {
                printFormattedMessage(e.getMessage());
            }
            
            // * Ask user for next command
            System.out.println("What's your next command?\n");
            command = sc.nextLine();         
        }

        printFormattedMessage("Bye. Hope to see you again soon!\n");
        sc.close();
    }

    public static void main(String[] args) {
        new Duke("../../../data/duke.txt").run();
    }

    /**
     * Print a welcome message to the user.
     */
    private static void greetUser() {
        String LOGO = " ____        _        \n"
                                     + "|  _ \\ _   _| | _____ \n"
                                     + "| | | | | | | |/ / _ \\\n"
                                     + "| |_| | |_| |   <  __/\n"
                                     + "|____/ \\__,_|_|\\_\\___|\n";
        String GREETING = "Greetings friend! I am your personal assistant,\n" 
                            + LOGO 
                            + "\nWhat can I do for you?\n";
        System.out.println(GREETING);
    }

    /**
     * Prints a message with a line of dashes before and after it.
     * 
     * @param message The message to print within 2 lines.
     */
    protected static void printFormattedMessage(String message) {
        System.out.println(LINE + "\t" + message + LINE);
    }
}