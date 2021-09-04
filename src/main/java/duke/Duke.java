
package duke;

import java.util.Scanner;

import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Entry point of Duke Chatbot.
 * Initializes the application and starts the interaction with the user.
 *
 * @author Wang Hong Yong
 */
public class Duke {

    private Parser parser;

    /**
     * Constructor for the Parser class.
     */
    public Duke() {
        parser = new Parser();
    }

    /**
     * Runs duke until termination.
     */
    public String getResponse(String command) {
        try {
            return parser.parse(command);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
