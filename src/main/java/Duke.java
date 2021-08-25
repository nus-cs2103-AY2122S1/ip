public class Duke {

    private static boolean isRunning = false;
    /**
     * Function starts the process of the dukebot, closes when the "Bye command is issued"
     */
    private static void start() {
        while (true) {
            String command = Presentation.scan();
            if (!isRunning) {
                System.out.println("See ya");
                break;
            } else {
                try {
                    Logic.process(command);
                } catch (InvalidCommandException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }
    }

    protected static void stop() {
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
