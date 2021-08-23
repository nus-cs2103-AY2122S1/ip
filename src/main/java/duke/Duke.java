package duke;

import duke.exception.DukeException;
import duke.misc.Parser;
import duke.misc.TaskList;
import duke.misc.Ui;

import java.io.IOException;

/**
 * Driver class to simulate the 'Annie' chat bot program.
 *
 * @author limzk126
 * @version Level-7
 */
public class Duke {
    /**
     * Method to simulate the program.
     */
    public void run() throws IOException {
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        Ui.printBlock(Ui.WELCOME_MSG);
        Parser p = new Parser();
        while (true) {
            String command = ui.readCommand();
            Ui.printBlock(p.execute(command, tl));
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
            System.err.println("File does not exist!");
        }
    }
}
