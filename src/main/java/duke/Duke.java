package duke;

import duke.task.TaskList;

import duke.command.Command;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        taskList = new TaskList();
        storage = new Storage(filePath);
        ui = new Ui(taskList, storage);
    }

    public void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.getCommand();
            Parser parser = new Parser(command);
            if (parser.isExit()) {
                break;
            }
            Command c = parser.parse();
            c.execute(taskList, storage);
            isExit = parser.isExit();
        }
    }

    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}
