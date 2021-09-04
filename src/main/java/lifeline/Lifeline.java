package lifeline;

import java.util.ArrayList;

import lifeline.command.Command;
import lifeline.exception.LifelineException;
import lifeline.parser.Parser;
import lifeline.storage.Storage;
import lifeline.task.TaskList;
import lifeline.ui.Ui;

/**
 * The class Lifeline is the entry point to the Lifeline program.
 * Calling start on this class will start the program.
 */
public class Lifeline {

    /** List of tasks created by the user */
    private TaskList taskList;

    /** Used to save new tasks or load saved tasks */
    private Storage storage;

    /** Used to display information to the user */
    private Ui ui;

    /**
     * Default constructor for a Lifeline.
     *
     * @param filepath Path to load or save tasks.
     */
    public Lifeline(String filepath) {
        this.storage = new Storage(filepath);
        this.ui = new Ui();
        try {
            this.taskList = storage.load();
        } catch (LifelineException e) {
            this.taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Gets the appropriate response from Lifeline if user inputs a valid command.
     * Method is used only for GUI.
     *
     * @param command User input.
     * @return Response from Lifeline if user inputs a valid command. If user inputs invalid command, error
     * message is returned instead.
     */
    public String getResponse(String command) {
        try {
            Command c = Parser.parse(command);
            return c.getExecute().apply(command.trim(), storage, taskList, ui);
        } catch (LifelineException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Starts the console program.
     */
    public void start() {
        ui.printToConsole(ui.consoleGreet());
        this.getInput();
    }

    /**
     * Greets user when user starts GUI.
     *
     * @return Greeting message when user starts GUI.
     */
    public String getGreetingMessage() {
        return ui.guiGreet();
    }

    private void getInput() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                if (fullCommand.equals("")) {
                    continue;
                }
                System.out.println();
                Command c = Parser.parse(fullCommand);
                String response = c.getExecute().apply(fullCommand.trim(), storage, taskList, ui);
                ui.printToConsole(response);
                if (c == Command.BYE) {
                    isExit = true;
                }
            } catch (LifelineException e) {
                ui.printToConsole(ui.showError(e.getMessage()));
            }
        }
    }
}
