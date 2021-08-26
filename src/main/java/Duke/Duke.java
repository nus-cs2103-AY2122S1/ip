package Duke;

import Duke.Commands.Command;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Task.TaskList;
import Duke.Ui.Ui;

import java.io.FileNotFoundException;


public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file path.");
            e.printStackTrace();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand, tasks);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                break;
            }
        }
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
