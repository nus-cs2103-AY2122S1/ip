package duke;

import duke.command.Command;
import duke.task.TaskList;

public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a duke chat bot with the given file path to store and
     * load data from.
     *
     * @param filePath relative file path to data storage
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.printError(e);
        }
        this.ui = new Ui();
    }

    /**
     * Runs Duke Chat bot.
     */
    public void run() {
        ui.sayGreeting();
        boolean isExit = false;
        while (!isExit) {
           try {
               String userInput = ui.getUserInput();
               Command c = Parser.parseCommand(userInput);
               c.execute(taskList, ui, storage);
               isExit = c.isExit();
           } catch (DukeException e) {
               ui.printError(e);
           }
       }

    }

    public static void main(String[] args) {
            new Duke("data/data.txt").run();
        }

}


