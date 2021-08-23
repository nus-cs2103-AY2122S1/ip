package duke;

import duke.misc.Parser;
import duke.misc.TaskList;
import duke.misc.Ui;

import java.io.IOException;

/**
 * Driver class to simulate the 'Annie' chat bot program.
 */
public class Duke {
    /**
     * Method to Simulate program.
     *
     * @throws IOException In case of invalid directory.
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
