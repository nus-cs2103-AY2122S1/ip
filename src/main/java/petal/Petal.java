package petal;

import petal.command.Command;
import petal.components.Parser;
import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The class for the Petal bot. It is able to respond to
 * a certain number of pre-determined commands in order to add certain
 * activities and track them.
 */
public class Petal {

    //Boolean representing if the user has said bye
    public static boolean bye = false;
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Constructor for the Duke class
     */
    public Petal() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(taskList, ui);
        parser = new Parser(storage, taskList);
    }

    /**
     * Method to give the start message and to run the bot.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        storage.createDirectory();
        while (!bye) {
            String message = scanner.nextLine();
            Command command = parser.handleInput(message);
            command.execute(taskList, ui, storage);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Petal petal = new Petal();
        petal.run();
    }
}

