package petal;

import javafx.application.Application;
import petal.command.Command;
import petal.components.Parser;
import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;
import petal.gui.Main;

/**
 * The class for the Petal bot. It is able to respond to
 * a certain number of pre-determined commands in order to add certain
 * activities and track them.
 *
 * @author Siddanth
 */
public class Petal {

    //Components of the Petal instance
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Constructs a Petal instance
     */
    public Petal() {
        ui = new Ui(this);
        taskList = new TaskList();
        storage = new Storage(taskList);
        parser = new Parser();
    }

    /**
     * Returns either start message or welcome back message
     */
    public void greetUser() {
        ui.sendToGui(storage.createDirectory());
    }

    public Ui getUi() {
        return this.ui;
    }

    /**
     * Takes the user input and sends output to ui instance
     */
    public void run(String message) {
        Command command = parser.handleInput(message);
        String reply = command.execute(taskList, storage);
        ui.sendToGui(message, reply);
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

