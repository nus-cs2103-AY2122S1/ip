package duchess.main;

import java.util.Scanner;
import duchess.command.Command;

/**
 * This class implements a Duke Chatbot variant: Duchess
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */

public class Duchess {
    /** The DuchessList which holds the string stored by the user.*/
    private DuchessList duchessList;
    /** The Scanner used to read in user input.*/
    private Scanner sc;
    /** The Ui object which handles printing to screen.*/
    private Ui ui;
    /** The parser to determine the respective commands from the user input.*/
    private Parser parser;


    /**
     * Constructs a Duchess object.
     */
    public Duchess()
    {
        this.ui = new Ui();
        this.sc = new Scanner(System.in);
        this.duchessList = DuchessFileHandler.load();
        this.parser = new Parser();
        ui.prettyPrint("Good day. I am Duchess.\nWhat can I do for you?");
    }

    public static void main(String[] args) {
        new Duchess().run();
    }


    /**
     * Handles the input from the user and the corresponding response.
     */
    public void run()
    {
        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = sc.nextLine();
                Command c = parser.checkPrefix(input);
                isRunning = c.handleLogic(this);
            } catch (DuchessException d){
                ui.prettyPrint(d.getMessage());
            }
        }
        ui.prettyPrint("I bid thee farewell.");
    }

    public Ui getUi() {
        return this.ui;
    }

    public DuchessList getDuchessList() {
        return this.duchessList;
    }
}
