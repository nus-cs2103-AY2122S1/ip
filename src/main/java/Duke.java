public class Duke {
    private static final String ENDING_COMMAND = "bye";

    public static void main(String[] args) {
        Ui.say("Hello! I'm Iris. What can I do for you?");
        Storage.readFromFile();
        String command = Ui.prompt();
        while (!command.equals(ENDING_COMMAND)) {
            try {
                Parser.handleCommand(command);
            } catch (IrisException exception) {
                Ui.sayError(exception);
            }
            command = Ui.prompt();
        }

        Ui.say("Bye. Hope to see you again soon!");
    }
}
