package bobbybot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class BobbyBot {
    private final String DBPATH = "data/database.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor to initialise classes
     */
    public BobbyBot()  {
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
        Parser parser = new Parser();
        while (true) {
            String userInput = sc.nextLine();
            ui.showLine();
            try {
                parser.parseCommand(userInput, tasks , ui);
                storage.save(tasks);
            } catch (IllegalArgumentException e) {
                System.out.println("OOPS!!! I'm sorry but I don't know what that mean :-(");
            } catch (InvalidArgumentException e) {
                System.out.println("You did not specify the correct details for this command");
            } catch (TooManyArgumentsException e) {
                System.out.println("You gave me too many details for this command!");
            } catch (IOException e) {
                System.out.println("Could not save tasks to database!\n");
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
