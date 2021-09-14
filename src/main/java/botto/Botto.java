package botto;

import botto.command.Command;
import botto.ui.Main;
import botto.util.Dialog;
import botto.util.Parser;
import botto.util.Storage;
import botto.util.TaskList;
import javafx.application.Application;
import javafx.scene.layout.VBox;

/**
 * A task tracking bot.
 */
public class Botto {
    private Storage storage;
    private TaskList taskList;
    private Dialog dialog;

    /**
     * Constructor for a Botto bot.
     */
    public Botto() {
        dialog = new Dialog();
        storage = new Storage("data/botto.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (BottoException e) {
            dialog.showError(e.getMessage());
        }
    }

    /**
     * Evaluate user input and execute necessary action.
     *
     * @param input user input.
     */
    public void handleUserInput(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(taskList, dialog, storage);
        } catch (BottoException e) {
            dialog.showError(e.getMessage());
        }
    }

    /**
     * Set up dialog container in the ui.
     *
     * @param container dialog container.
     */
    public void setUpDialogContainer(VBox container) {
        dialog.setUp(container);
        dialog.showWelcome();
    }

    /**
     * This method will instantiate the Botto bot.
     *
     * @param args sequence of characters (Strings) that are passed to the function.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}
