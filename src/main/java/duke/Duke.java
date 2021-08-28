package duke;

import java.io.IOException;

import duke.exception.DukeException;
import duke.misc.Parser;
import duke.misc.TaskList;
import duke.misc.Ui;

/**
 * Driver class to simulate the 'Annie' chat bot program.
 */
public class Duke {
    private Ui ui;
    private TaskList tl;
    private Parser p;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        ui = new Ui();
        tl = new TaskList();
        p = new Parser();
    }

    /**
     * Method to simulate program.
     *
     * @throws IOException In case of invalid directory.
     */
    public void run() throws IOException {
        Ui.printBlock(Ui.WELCOME_MSG);
        while (true) {
            String command = ui.readCommand();
            try {
                Ui.printBlock(p.execute(command, tl));
            } catch (DukeException e) {
                Ui.printBlock(e.toString());
            }
            if (command.equals("bye")) {
                tl.saveData();
                break;
            }
        }
    }

    /**
     * Driver method to start program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (IOException e) {
            Ui.printBlock("No such directory!");
        }
    }
}
