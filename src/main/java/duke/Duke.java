package duke;

import duke.exception.InvalidInputException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.FileNotFoundException;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

//    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
//    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));
//
//    private Duke duke = new Duke();

    /**
     * Constructor for a Duke instance.
     *
     * @param filePath The specified path of the file we will write and load information from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/tasks.json");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Runs the Duke chatbot.
     *
     * @throws InvalidInputException If the input is deemed invalid.
     */
//    public void run() throws InvalidInputException {
//
//        boolean isExit = false;
//
//        while (!isExit) {
//            String input = ui.readCommand();
//            Command c = Parser.parseCommand(input);
//            c.execute(tasks, ui, storage);
//            isExit = c.isExit();
//        }
//
//    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws InvalidInputException {
        return Parser.parseCommand(input).execute(this.tasks, this.ui, this.storage);
    }


//    public static void main(String[] args) throws InvalidInputException {
//        new Duke("./data/tasks.json").run();
//    }

}
