public class Duke {
    private static final Presentation presentation_layer = new Presentation();


    // Function starts the process of the dukebot, closes when the "Bye command is issued"
    private static void start() {
        while (true) {
            String command = presentation_layer.scan();
            // Check if player has entered a command, returns the command if command is not null or bye
            if (command.equals("")) {
                System.out.println("Please enter a command");
            } else if (command.equals("bye")) {
                System.out.println("See ya");
                break;
            } else {
                System.out.println(command);
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
