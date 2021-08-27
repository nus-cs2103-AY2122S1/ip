package Duke;

import Duke.Tasks.Task;
import Duke.Tool.Parser;
import Duke.Tool.Storage;
import Duke.Tool.TaskList;

import java.io.IOException;
import java.lang.String;

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
     * The method of run
     */
    public void run() {
        ui.showLogo();
        ui.showWelcome();
        boolean isProcess = true;
        while (isProcess) {
            try {
                String cmd = ui.getCommand();
                Task comingCmdTask = Parser.parse(cmd, task);
                comingCmdTask.execute(task, ui, storage);
                isProcess = !comingCmdTask.isExit;
            } catch (NullPointerException e) {
                continue;
            }

        }
        ui.exit();
    }

    /**
     * the main method
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}


