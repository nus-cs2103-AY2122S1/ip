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

    private List<String> preCommandErrors;

    private static final String FILE_URL = "data/storedList.txt";

    /**
     * Empty constructor for Launcher to call
     */
    public Duke() {
        preCommandErrors = null;
        ui = new Ui();

        List<Task>[] storedLists = new List[2];
        storedLists[0] = new ArrayList<>();
        storedLists[1] = new ArrayList<>();

        try {
            storage = new Storage(FILE_URL);
            storedLists = storage.loadStorage();

        } catch (DukeException e) {
            preCommandErrors = new ArrayList<>();
            preCommandErrors.add(Ui.UI_ERROR_HEADING);
            preCommandErrors.add("\t" + e.getMessage());
        } finally {
            tasks = new TaskList(storedLists[0], storedLists[1]);
        }
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
        } catch (DukeException | IllegalArgumentException | IndexOutOfBoundsException e) {
            linesOfOutput = new ArrayList<String>();
            linesOfOutput.add(Ui.UI_ERROR_HEADING);
            linesOfOutput.add("\t" + e.getMessage());
        }

        return new ResponseMessage(String.join("\n", linesOfOutput), isExit);
    }

    public String greetToGui() {
        if (preCommandErrors == null) {
            return ui.displayGreetings();
        } else {
            return ui.displayGreetings() + "\n" + String.join("\n", preCommandErrors);
        }
    }
}