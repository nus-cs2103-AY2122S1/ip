package duke;

import duke.Command.ICommand;

/**
 * Wrapper of Duke's implementation.
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Initializes duke with default data save path.
     */
    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage();
        try {
            storage.initialize();
        } catch (DukeException e) {
            Ui.printErrorMessage(e);
        }
    }

    /**
     * Initializes duke with given data save path.
     * @param pathStr string of save path, ending with the name of save file
     */
    public Duke(String pathStr) {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage();
        try {
            storage.initialize(pathStr);
        } catch (DukeException e) {
            Ui.printErrorMessage(e);
        }
    }

    /**
     * Starts interacting with user. Exit the loop when detecting {@link duke.Parser#WORD_EXIT Parser.WORD_EXIT}.
     */
    public void run() {
        Ui.printWelcomeMessage();
        boolean isExit = false;

        try {
            if (!storage.isEmpty()) {
                taskList.load(storage.getFileContents());
            }
        } catch (DukeException e) {
            Ui.printErrorMessage(e);
        }

        // Echo loop till exit word is entered
        while (!isExit) {
            String userInput = ui.readCommand();

            try {
                Ui.printDividerLine();

                if (userInput.contains("|")) {
                    throw new DukeException(ExceptionType.PIPE_SYMBOL);
                }

                ICommand c = Parser.parse(userInput, taskList.size());
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
                Ui.printDividerLine();
            } catch (DukeException e) {
                Ui.printErrorMessage(e, userInput);
                Ui.printDividerLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
