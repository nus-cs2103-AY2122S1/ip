package duke;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Duke is a chatbot that can read and respond to user input.
 */
public class Duke {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructor of Duke.
     * @param filePath File that stores all the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    /**
     * Runs the programme.
     *
     */
    public void run() {
        ui.welcome();
        boolean isExist = true;

        while(isExist) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExist = c.isExist();
            } catch (DukeException e) {
                Ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

}
