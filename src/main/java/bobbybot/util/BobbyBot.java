package bobbybot.util;

import java.util.ArrayList;
import java.util.Scanner;

import bobbybot.commands.Command;
import bobbybot.person.Person;

/**
 * Represents the chatbot
 */
public class BobbyBot {
    private static final String DBPATH = "data/database.txt";
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final PersonList contacts;
    /**
     * Constructor to initialise classes
     */
    public BobbyBot() {
        ui = new Ui();
        ui.showWelcome();
        storage = new Storage(DBPATH);
        tasks = new TaskList(storage.load());
        contacts = new PersonList(new ArrayList<Person>());
    }

    /**
     * Start running the chatbot
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        boolean isExit = false;
        while (!isExit) {
            String userInput = sc.nextLine();
            ui.showLine();
            Command c = parser.parseCommand(userInput);
            assert c != null : "Command cannot be null";
            c.execute(tasks, ui, storage, contacts);
            String response = c.getResponse();
            System.out.println(response);
            isExit = c.isExit();
            ui.showLine();
        }
    }

    /**
     * Our main method. Starts up the chatbot and waits for user inputs
     * @param args Command Line Arguments
     */
    public static void main(String[] args) {
        new BobbyBot().run();
    }
}
