import duke.command.Command;

import duke.exception.DukeException;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Main Duke class
 */
public class Duke {

    private final Parser parser;
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    /**
     * Basic Constructor
     *
     * @param filePath the path where file savedOutput.txt. which contains past state is located
     * @throws DukeException
     */
    public Duke(String filePath) throws DukeException{
        storage = new Storage(filePath);
        ui = new Ui(48);
        taskList = new TaskList();
        parser = new Parser(storage, taskList, ui);
        parser.loadTask();
    }

    /**
     * Continuous scan loops until user input "bye"
     */
    public void  run() {
        ui.welcome();
        boolean notBye = true;
        while (notBye) {
            String input = ui.userInput();
            try {
                Command c = parser.parse(input);
                notBye = c.exec();
            } catch (DukeException e) {
                ui.error(e.toString());
            }
        }
    }

    /**
     * Main program
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            new Duke("src/main/storedMsg").run();
        } catch (DukeException e) {
            System.out.println(e);
        }

    }
}
