import command.Command;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import dukeException.DukeException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTaskList());
        } catch (DukeException e) {
            ui.showDukeException(e);
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.printLogo();
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch  (DukeException e) {
                ui.showDukeException(e);
            }
        }
    }

    public static void main(String[] Args){
        new Duke("data/taskList.txt").run();
    }
}