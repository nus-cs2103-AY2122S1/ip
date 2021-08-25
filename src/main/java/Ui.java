import java.util.Scanner;
/**
 * Encapsulates the user interface. Scans for input and responds to user.
 */
public class Ui {

    public static final String[] WELCOME_MSG = new String[] {
        "What's up, I'm duke!"
    };
    public static final String[] BYE_MSG = new String[] {"Bye mate!"};
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Constructor for a Ui
     * 
     * @param tasks TaskList to record tasks.
     * @param storage Storage to store tasks.
     */
    public Ui(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Run the Ui and read input from the user.
     */
    public void run() {
        // Print a welcome message
        printMsg(WELCOME_MSG);
        
        // Let user input commands and take actions accordingly.
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(tasks);
        boolean exit = false;
        while(!exit) {
            try {
                if (sc.hasNextLine()) {
                    String command = sc.nextLine();
                    storage.cache(command);
                    String[] resultMsg = parser.parseCommand(command);
                    printMsg(resultMsg);
                    if (isByeMsg(resultMsg)) {
                        exit = true;
                    }
                } else {
                    exit = true;
                }
            } catch(DukeException e) {
                printMsg(new String[] {e.getMessage()});
            }

        }
        sc.close();
    }

    /**
     * Prints out an array of messages.
     *
     * @param msgs Messages to be printed, as a string array.
     */
    public static void printMsg(String[] msgs) {
        System.out.println("    ---");
        for (String msg : msgs) {
            System.out.println("    " + msg);
        }
        System.out.println("    ---");
    }

    /**
     * Checks if a message is a terminating message.
     * 
     * @param msg Message string to be checked.
     * @return Boolean describing if the message is terminal.
     */
    public static boolean isByeMsg(String[] msg) {
        return msg.equals(BYE_MSG);
    }
}