package duke;

import duke.command.Command;
import duke.command.ExitCommand;

public class Duke {

    private final Storage storage;
    private final TaskList taskList;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
    }

    public void run() {
        Ui ui = new Ui();
        ui.greet();

        boolean isExit = false;

        // duke.Duke continuously asks for the user's input until they type "bye"
        while (!isExit) {

            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        new ExitCommand().execute(taskList, ui, storage);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("myTasks.txt");
        duke.run();
    }
}