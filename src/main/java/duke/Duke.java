package duke;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;
import duke.data.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

//todo command to print deadlines/events occuring on specific date
//todo bug of task name/date/time being deleted by one when the program reopens and list keyed in

/**
 * The Duke programme implements a bot that help users to record the tasks they have.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private String storagePath;
    private File dataFile;

    private static final String STORAGE_PATH = "data/duke.txt";
    private static final File DATA_FILE = new File(STORAGE_PATH);

    public Duke(String storagePath) {
        this.storagePath = storagePath;
        this.dataFile = new File(storagePath);
        ui = new Ui();
        storage = new Storage(storagePath);
        try {
            boolean isFileCreated = dataFile.createNewFile();
            if (!isFileCreated) {
                taskList = new TaskList(storage.load());
            } else {
                taskList = new TaskList();
            }
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);

        while (!isExit) {
            try {
                String command = ui.readCommand(sc);
                ui.showLine();
                Parser p = new Parser();
                Command c = p.parse(command);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        sc.close();

    }

    public static void main(String[] args) {
        new Duke(STORAGE_PATH).run();
    }
}
