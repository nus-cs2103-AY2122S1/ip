public class Duke {

    // Function starts the process of the dukebot, closes when the "Bye command is issued"
    private static void start() {
        while (true) {
            String command = Presentation.scan();
            // Check if player has entered a command, returns the command if command is not null or bye
            if (command.equals("bye")) {
                System.out.println("See ya");
                break;
            } else {
                System.out.println("Pass on to logic stage");
                try {
                    Logic.process(command);
                } catch (InvalidCommandException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }
    }

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
