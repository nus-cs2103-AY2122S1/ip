import java.util.Optional;

public class Duke {

    public static void main(String[] args) {
        Storage storage = new Storage();
        Ui ui = new Ui();
        Parser parser = new Parser();
        storage.open();
        boolean isLoopBroken = false;
        ui.greet(true);

        while (!isLoopBroken) {
            try {
                Command c = parser.parse(ui.getInput());
                isLoopBroken = c.execute(ui, storage);
            } catch (DukeExceptions e) {
                ui.printException(e);
                isLoopBroken = false;
            }
        }
    }
}
