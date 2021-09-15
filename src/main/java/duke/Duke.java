package duke;

import duke.command.ICommand;
import duke.ui.MainWindow;
import duke.ui.Ui;
import duke.ui.UiMode;

/**
 * Wrapper of Duke's implementation.
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    private boolean isExit;

    /**
     * Initializes duke with specified UI mode
     * @param uiMode UI mode
     */
    public Duke(UiMode uiMode) {
        ui = new Ui(uiMode);
        taskList = new TaskList();
        storage = new Storage();
        try {
            storage.initialize();
        } catch (DukeException e) {
            Ui.printErrorMessage(e);
        }
        this.isExit = false;
    }

    /**
     * Initializes duke with given data save path.
     * @param pathStr string of save path, ending with the name of save file
     * @param uiMode UI mode
     */
    public Duke(String pathStr, UiMode uiMode) {
        ui = new Ui(uiMode);
        taskList = new TaskList();
        storage = new Storage();
        try {
            storage.initialize(pathStr);
        } catch (DukeException e) {
            Ui.printErrorMessage(e);
        }
        this.isExit = false;
    }

    protected void initialize() {
        ui.printWelcomeMessage();

        try {
            if (!storage.isEmpty()) {
                taskList.load(storage.getFileContents());
            }
        } catch (DukeException e) {
            Ui.printErrorMessage(e);
        }
    }

    protected void initialize(MainWindow mainWindow) {
        ui.printWelcomeMessage(mainWindow);

        try {
            if (!storage.isEmpty()) {
                taskList.load(storage.getFileContents());
            }
        } catch (DukeException e) {
            Ui.printErrorMessage(e);
        }
    }

    /**
     * Starts interacting with user. Exit the loop when detecting {@link duke.Parser#WORD_EXIT Parser.WORD_EXIT}.
     */
    public void run() {
        this.initialize();

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
                this.isExit = c.isExit();
                Ui.printDividerLine();
            } catch (DukeException e) {
                Ui.printErrorMessage(e, userInput);
                Ui.printDividerLine();
            }
        }
    }

    /**
     * Starts interacting with user. Exit the loop when detecting {@link duke.Parser#WORD_EXIT Parser.WORD_EXIT}.
     */
    public String getResponse(String userInput) {
        try {
            if (userInput.contains("|")) {
                throw new DukeException(ExceptionType.PIPE_SYMBOL);
            }
            ICommand c = Parser.parse(userInput, taskList.size());
            c.execute(this.taskList, this.ui, this.storage);
        } catch (DukeException e) {
            Ui.printErrorMessage(e, userInput);
        }
        return Ui.getResponse();
    }

    public static void main(String[] args) {
        new Duke(UiMode.CLI).run();
    }
}
