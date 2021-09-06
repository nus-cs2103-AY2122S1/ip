import duke.Command;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.getResponse(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }


//    public void run() {
//        boolean isExit = false;
//        while (!isExit && ui.hasUserInput()) {
//            try {
//                String fullCommand = ui.readCommand();
//                Command c = Parser.parse(fullCommand);
//                c.getResponse(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            }
//        }
//        ui.closeUserInput();
//    }

    public static void main(String[] args) {
//        new Duke().run();
    }
}

