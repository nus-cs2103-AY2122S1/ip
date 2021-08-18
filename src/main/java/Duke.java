public class Duke {
    private Ui ui;

    public Duke() {
        ui = new Ui();
    }

    private void run() {
        boolean shouldExit = false;

        ui.greet();
        while (!shouldExit) {
            String commandName = ui.readInput();
            // Can possibly add a new class that will assign a Command object based on commandName to save space
            Command command = new Command(commandName);
            command.execute();
            shouldExit = command.shouldExit();
        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}
