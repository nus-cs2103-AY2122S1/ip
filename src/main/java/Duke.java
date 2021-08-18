public class Duke {
    private Ui ui;

    public Duke() {
        ui = new Ui();
    }

    private void run() {
        boolean shouldExit = false;

        ui.greet();
        while (!shouldExit) {
            String input = ui.readInput();
            Command command = new Command(input);
            command.execute();
            shouldExit = command.shouldExit();
        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}
