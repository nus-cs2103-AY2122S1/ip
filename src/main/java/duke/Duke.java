package duke;

import duke.command.Command;
import duke.graphics.ResponseMessage;

import java.util.ArrayList;
import java.util.List;


public class Duke {//} extends Application {
    // project components
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String FILE_URL = "data/storedList.txt";

    /**
     * Empty constructor for Launcher to call
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_URL);

        List<Task>[] storedLists = storage.loadStorage();
        tasks = new TaskList(storedLists[0], storedLists[1]);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public ResponseMessage getResponse(String input) {
        List<String> linesOfOutput = new ArrayList<>();
        boolean isExit = false;

        try {
            Command c = Parser.parse(input);
            linesOfOutput.addAll(c.execute(tasks, ui, storage));
            isExit = c.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        return new ResponseMessage(String.join("\n", linesOfOutput), isExit);
    }

    public String greetToGui() {
        return ui.displayGreetings();
    }
}