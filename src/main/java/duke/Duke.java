package duke;

/**
 * Main file for the duke bot. The duke bot keeps track of all your task and allows you to mark as complete.
 * Call list to see the list of task. Each task can be completed. When a task is completed, it will still appear
 * when you call list this time, but will be deleted from history for storage purposes the next time it is ran.
 * Use dukebot to mark down tasks today!
 */
public class Duke {

    private static boolean isRunning = false;

    /**
     * Function starts the process of the dukebot, closes when the "Bye command is issued"
     */
    public static void start() {
        isRunning = true;
        Logic.preload();
        while (true) {
            String command = UI.scan();
            Logic.checkIfSpecialCommand(command);
            //Check if the logic has made any changes to quit the programme or continue running
            if (!isRunning) {
                System.out.println("See ya");
                break;
            }
        }
    }

    /**
     * Stop the running of the bot
     */
    protected static void stop() {
        assert isRunning;
        isRunning = false;
    }

    /**
     * Starting point of the dukeBot
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you today?");
        start();
    }

}
