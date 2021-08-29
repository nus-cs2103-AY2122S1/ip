package duke;

import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.ui.Ui;

import java.util.Scanner;  // Import the Scanner class

public class Duke {

    private Scanner myObj;

    public Duke() {

        this.myObj = new Scanner(System.in);  // Create a Scanner object
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

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