import java.io.FileNotFoundException;

import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Command Line Task Manager called Duke
 *
 * @author Rama Venkatesh
 */

public class Duke extends Application {

    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    /**
     * Constructor that instantiates Duke
     */
    public Duke() {
        parser = new Parser();
        this.storage = new Storage();
        try {
            taskList = storage.loadTaskList();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {
        ui = new Ui(stage, taskList, storage, parser);
        ui.init();
    }

}

