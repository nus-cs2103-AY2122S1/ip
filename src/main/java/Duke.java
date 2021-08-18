import java.util.Scanner;

public class Duke {
    private static final String GREETING_MESSAGE = "____________________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________________";
    public static final String EXITING_MESSAGE = "____________________________________________________________\n" +
            "Bye. Hope to see you again soon!\n" +
            "____________________________________________________________";
    private Event event;
    private boolean isRunning;

    /**
     * Constructor of the class 'Duke'.
     */
    public Duke() {
        // Start the program and greet the user.
        this.isRunning = true;
        System.out.println(Duke.GREETING_MESSAGE);
    }

    /**
     * Based on the command received, either quit the program or process an event.
     */
    private void readCommand() {
        // Read the command.
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        if (command.equals("bye")) {
            // Print exiting message and end the program.
            System.out.println(Duke.EXITING_MESSAGE);
            this.isRunning = false;
        } else {
            // Create a new event and convert it to string.
            this.event = new Event(command);
            this.event.process();
            System.out.println(this.event);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        // Process the commands received.
        while (duke.isRunning) {
            duke.readCommand();
        }
    }
}
