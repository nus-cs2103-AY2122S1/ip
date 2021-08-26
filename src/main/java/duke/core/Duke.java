package duke.core;

import duke.command.Command;
import duke.gui.Ui;

/**
 * Duke is a chat bot that helps you keep track of tasks. It recognizes specific commands as stated in the user guide.
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a Duke object.
     *
     * @param filePath The relative filepath of the storage file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    private void run() {
        boolean shouldExit = false;
        Ui.greet();
        while (!shouldExit) {
            String input = Ui.readInput();
            Command command = Parser.parse(input, taskList);
            command.execute(taskList, storage);
            shouldExit = command.shouldExit();
        }
    }

    public static void main(String[] args) {
        new Duke("data/Duke.txt").run();
    }
}
