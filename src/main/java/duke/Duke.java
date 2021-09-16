package duke;

import duke.command.Command;
import duke.exception.DukeException;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a Duke bot that can interact with users
 * and keep track of different tasks.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Duke
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.loadingError();
            tasks = new TaskList();
        }
        parser = new Parser(ui, tasks);
    }

    /**
     * A public constructor for Duke.
     */
    public Duke() {
        this("data/tasks.txt");
    }

    public String getResponse(String input) {
        try {
            String fullCommand = input;
            Command c = parser.parse(fullCommand);
            if(c.isBye()) {
                exitDuke();
            }
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    //@@author endriu_l-reused
    //Reused from https://stackoverflow.com/questions/21974415/how-to-close-this-javafx-application-after-showing-a-message-in-a-text-area-elem/21996863
    // with minor modifications
    public void exitDuke() {
        Timer timer = new Timer();
                new Timer().schedule(new TimerTask() {
                    public void run () { Platform.exit(); }
                }, 1000);
    }

    public Ui getUi() {
        return ui;
    }

}

