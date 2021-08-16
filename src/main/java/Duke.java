public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void formatDukeResponse(String response) {
        System.out.println(HORIZONTAL_LINE + "\n" + response + "\n" + HORIZONTAL_LINE);
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String welcomeMessage = logo + "Hello! I'm Duke.\n" + "What can I do for you";
        formatDukeResponse(welcomeMessage);
    }

    public static void main(String[] args) {
        printWelcomeMessage();
    }
}
