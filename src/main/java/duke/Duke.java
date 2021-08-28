package duke;

import duke.command.Command;

/**
 * This program is Duke bot who keeps track of tasks that user inputs
 * and let user manage the tasks through various commands.
 */
public class Duke {
    //Added Gradle support in master branch
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * This is a constructor for Duke bot which is necessary to run Duke
     * after putting in file path to store tasks into Hard Disk.
     * @param filePath Duke stores a text file of tasks input and loads them at the given file path.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage);
    }

    /**
     * This method lets Duke run and start asking for user input.
     */
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

    /**
     * This is the main method to run Duke.
     * @param args Main method arguments.
     */
    public static void main(String[] args) {
        new Duke("./Duke.txt").run();
    }
}
