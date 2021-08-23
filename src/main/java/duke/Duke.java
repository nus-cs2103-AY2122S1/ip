package duke;


import duke.command.Command;
import duke.task.TaskList;

public class Duke {

    private boolean isExit = false;
    private TaskList tasks = new TaskList();
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            ui.showException(e);
        }
    }

    private void run() {
        ui.greet();
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input, ui, tasks, storage);
                command.execute();
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showException(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/taskList.txt");
        duke.run();
    }

}
