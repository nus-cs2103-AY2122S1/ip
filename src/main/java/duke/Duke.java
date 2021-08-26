package duke;

import duke.commands.Command;
import duke.storage.Storage;


/**
 * Main class of the program
 */
public class Duke {

    /**
     * Main function of the duke program
     * Loops taking user input and converting it into commands to
     * execute until look is broken (using bye command)
     */

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
