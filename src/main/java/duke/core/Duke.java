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
            String output = command.execute(taskList, storage);
            Ui.formatAndPrint(output);
            shouldExit = command.shouldExit();
        }
    }

    /**
     * Returns a String array with two elements. The first element is Duke's response to a given user input, and the
     * second element indicates whether Duke should exit, with "1" indicating yes and "0" indicating no.
     *
     * @param input The user's input
     * @return A String array with two elements. The first element is Duke's response to a given user input, and the
     * second element indicates whether Duke should exit, with "1" indicating yes and "0" indicating no.
     */
    public String[] getResponseAndExitStatus(String input) {
        Command command = Parser.parse(input, taskList);
        String response = command.execute(taskList, storage);
        String exitStatus = command.shouldExit() ? "1" : "0";
        return new String[] {response, exitStatus};
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
