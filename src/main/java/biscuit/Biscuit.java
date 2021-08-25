package biscuit;

import biscuit.commands.Command;
import biscuit.exceptions.BiscuitException;
import biscuit.parser.Parser;
import biscuit.storage.Storage;
import biscuit.task.TaskList;
import biscuit.ui.Ui;

import java.util.ArrayList;

/**
 * Biscuit is a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Biscuit {
    private final Storage storage;
    private final Ui ui;
    private TaskList taskList;

    /**
     * Constructs Biscuit class.
     *
     * @param filePath file path to save file.
     */
    public Biscuit(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            taskList = new TaskList(storage.load());
        } catch (BiscuitException e) {
            ui.showMessage(e.getMessage());
            taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Executes main method.
     *
     * @param args Arguments passed when run.
     */
    public static void main(String[] args) {
        new Biscuit("data/biscuit.csv").run();
    }

    /**
     * Runs Biscuit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(userInput);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (BiscuitException be) {
                ui.showError(be.getMessage());
            } catch (Exception e) {
                ui.showError("Woof! Looks like something went wrong, please try again.");
            } finally {
                ui.showLine();
            }
        }
    }

}
