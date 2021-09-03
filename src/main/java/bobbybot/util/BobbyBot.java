package bobbybot.util;

import bobbybot.commands.Command;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents the chatbot
 */
public class BobbyBot {
    private static final String DBPATH = "data/database.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor to initialise classes
     */
    public BobbyBot() {
        ui = new Ui();
        ui.showWelcome();
        storage = new Storage(DBPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Start running the chatbot
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(tasks, ui, storage);

        while (true) {
            String userInput = sc.nextLine();
            ui.showLine();
            try {
                Command c = parser.parseCommand(userInput);
                String response = c.getResponse(tasks, ui, storage);
                System.out.println(response);
                //storage.save(tasks);
            } catch (IllegalArgumentException e) {
                System.out.println("OOPS!!! I'm sorry but I don't know what that mean :-(");
            } finally {
                ui.showLine();
            }
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
