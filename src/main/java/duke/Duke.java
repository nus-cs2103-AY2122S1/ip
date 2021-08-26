package duke;
import java.io.IOException;
import duke.command.Command;

/**
 * Duke is a chatbot that can read and respond to user input.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

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
     * @throws IOException Handles exception with input and output to target file.
     */
    public void run() throws IOException {
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
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) {
        try {
            new Duke("./data/tasks.txt").run();
        } catch (IOException e) {
            e.printStackTrace();
        } ;
    }
}
