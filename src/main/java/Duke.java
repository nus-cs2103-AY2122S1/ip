import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.time.format.DateTimeFormatter;


/**
 * @author  Zhang Zhiyao
 */

/**
 * This is the Main class that will contain the main method
 * to be executed at run-time.
 */
public class Duke {



//-------------new implemenation-------------------
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


    public void run() {
        ui.showLogo();
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String cmd = ui.getCommand();
            Task comingCmdTask = Parser.parse(cmd, task);
            isExit = !comingCmdTask.isExit;
        }
        ui.exit();
    }

    public static void main(String[] args) throws IOException{
        new Duke("data/tasks.txt").run();
    }

    //-------------new implemenation-------------------

}


