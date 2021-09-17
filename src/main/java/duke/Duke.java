package duke;

import duke.command.Command;
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
     *
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

    protected void initialize() {
        this.initializeHelper();
        ui.printWelcomeMessage();
    }

    protected void initialize(MainWindow mainWindow) {
        this.initializeHelper();
        ui.printWelcomeMessage(mainWindow);
    }

    /**
     * Starts interacting with user in CLI-mode. Exit the loop when detecting {@link duke.Parser#WORD_EXIT WORD_EXIT}.
     */
    public void run() {
        // Echo loop till exit word is entered
        while (!isExit) {
            String userInput = ui.readCommand();

            try {
                Ui.printDividerLine();

                if (userInput.contains("|")) {
                    throw new DukeException(ExceptionType.PIPE_SYMBOL);
                }

                Command c = Parser.parse(userInput, taskList.size());
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
     * Starts interacting with user in GUI-mode. Exit the loop when detecting {@link duke.Parser#WORD_EXIT WORD_EXIT}.
     */
    public String getResponse(String userInput) {
        try {
            if (userInput.contains("|")) {
                throw new DukeException(ExceptionType.PIPE_SYMBOL);
            }

            Command c = Parser.parse(userInput, taskList.size());
            c.execute(this.taskList, this.ui, this.storage);
            this.isExit = c.isExit();
        } catch (DukeException e) {
            Ui.printErrorMessage(e, userInput);
        }
        return Ui.getResponse();
    }

    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Entry of CLI-mode duke.
     * @param args arguments for the main method.
     */
    public static void main(String[] args) {
        Duke duke = new Duke(UiMode.CLI);
        duke.initialize();
        duke.run();
    }

    private void initializeHelper() {
        try {
            storage.initialize();
            taskList.load(storage.getFileContents());
        } catch (DukeException e) {
            Ui.printErrorMessage(e);
        }
    }
}
