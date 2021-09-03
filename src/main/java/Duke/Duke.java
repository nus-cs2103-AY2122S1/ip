package Duke;

import Duke.Tasks.Task;
import Duke.Tool.Parser;
import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui.Ui;

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
    private TaskList task;
    private Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpeg"));

    /**
     * The constructor of Duke
     * @param filePath
     */
    public Duke(String filePath)  {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            task = new TaskList(storage.readData());
        } catch (IOException e) {
            ui.showLoadingError();
            task = new TaskList();
        }
    }


    /**
     * The Contructor for Duke Class
     */
    public Duke() {
        this("data/tasks.txt");
    }

    /**
     * The method of getResponse
     * @param input
     * @return String the value of getResponse for run layncher
     */
    public String getResponse(String input) {
        Task comingCmdTask = Parser.parse(input, task);
        return comingCmdTask.execute(task, ui, storage);
    }

    /**
     * The method of getUi
     * @return Ui
     */
    public Ui getUi() {
        return ui;
    }


}


