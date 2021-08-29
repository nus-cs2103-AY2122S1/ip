package duke;

import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.ui.Ui;

import java.util.Scanner;  // Import the Scanner class

/**
 * Entry point of Duke Chatbot.
 * Initializes the application and starts the interaction with the user.
 *
 * @author Wang Hong Yong
 */
public class Duke {

    private Scanner myObj;

    /**
     * Constructor for the Parser class.
     */
    public Duke() {
        this.myObj = new Scanner(System.in);
    }

    /**
     * Initialises duke.
     *
     * @param args arguments supplied by the user at program launch.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs duke until termination.
     */
    public void run(){
        Ui.printWelcomeMsg();
        Parser parser = new Parser();
        String command = myObj.nextLine();  // Read user input
        while (!command.toLowerCase().equals("bye")) {
            try {
                parser.parse(command);
            } catch (DukeException e) {
                Ui.prettify(e.getMessage());
            }
            command = myObj.nextLine();
        }

        if (command.toLowerCase().equals("bye")) {
            Ui.printGoodbyeMsg();
        }
    }
}