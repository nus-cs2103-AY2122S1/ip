package petal;

import petal.command.Command;
import petal.components.Parser;
import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;
import java.util.Scanner;

/**
 * The class for the Petal bot. It is able to respond to
 * a certain number of pre-determined commands in order to add certain
 * activities and track them.
 */
public class Petal {

    //Boolean representing if the user has said bye
    private boolean bye = false;
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Constructor for the Duke class
     */
    public Petal() {
        ui = new Ui(this);
        taskList = new TaskList(ui);
        storage = new Storage(taskList, ui);
        parser = new Parser();
    }

    public void stop() {
        this.bye = true;
    }

    /**
     * Method to give the start message and to run the bot.
     */
    public void run() {
        storage.createDirectory();
        while (!bye) {
            String message = ui.readCommand();
            Command command = parser.handleInput(message);
            command.execute(taskList, ui, storage);
        }
    }

    public static void main(String[] args) {
        Petal petal = new Petal();
        petal.run();
    }
}

