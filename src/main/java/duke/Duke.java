package duke;

import duke.command.Command;
import duke.ui.Ui;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke(String filePath) throws Exception {
        super();
        this.storage = new Storage(filePath);
        // TODO: throw error if unable to create file?
        this.taskList = new TaskList(storage.loadTasks());
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public boolean executeCommand(String fullCommand) {
        try {
            Command command = Parser.parse(fullCommand);
            command.execute(taskList, ui, storage);
            return command.shouldExit();
        } catch (Exception e) {
            // TODO: custom Duke exceptions?
            ui.printMessage("Error: " + e.getMessage());
            return false;
        }
    }
}
