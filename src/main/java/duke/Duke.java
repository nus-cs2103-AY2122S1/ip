package duke;

import duke.Tasks.Task;
import duke.Tool.Parser;
import duke.Tool.Storage;
import duke.Tool.TaskList;
import duke.Ui.Ui;

import java.io.IOException;
import java.lang.String;

import javafx.scene.image.Image;



/**
 * @author  Zhang Zhiyao
 * @version 1.0
 */

/**
 * This is the Main class that will contain the main method
 * to be executed at run-time.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpeg"));

    /**
     * Constructs Duke class server with input filepath
     *
     * @param filePath
     */
    public Duke(String filePath)  {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readData());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Constructs Duke class server
     */
    public Duke() {
        this("data/tasks.txt");
    }

    /**
     * Gets response from input message
     *
     * @param input
     * @return String the value of getResponse for run launcher
     */
    public String getResponse(String input) {
        Task comingCmdTask = Parser.parse(input, tasks);
        return comingCmdTask.execute(tasks, ui, storage);
    }

    /**
     * Gets Ui
     *
     * @return Ui
     */
    public Ui getUi() {
        return ui;
    }


}


