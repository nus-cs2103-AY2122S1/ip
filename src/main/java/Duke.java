import java.io.IOException;
import java.lang.String;

/**
 * @author  Zhang Zhiyao
 */

/**
 * This is the Main class that will contain the main method
 * to be executed at run-time.
 */
public class Duke {

    private Storage storage;
    private TaskList task;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            task = new TaskList(storage.readData());
        } catch (IOException e) {
            ui.showLoadingError();
            task = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.showLogo();
        ui.showWelcome();
        boolean isProcess = true;
        while (isProcess) {
            try {
                String cmd = ui.getCommand();
                Task comingCmdTask = Parser.parse(cmd, task);
                comingCmdTask.excute(task, ui, storage);
                isProcess = !comingCmdTask.isExit;
            } catch (NullPointerException e) {
                continue;
            }

        }
        ui.exit();
    }

    public static void main(String[] args) throws IOException{
        new Duke("data/tasks.txt").run();
    }

}


