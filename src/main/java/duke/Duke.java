package duke;

import duke.command.Command;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage);
    }

    public void run() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, taskList);
                c.execute(taskList, ui);
                isRunning = c.isRunning();

            } catch (DukeException e) {
                ui.stringWithDivider(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./Duke.txt").run();
    }
}
