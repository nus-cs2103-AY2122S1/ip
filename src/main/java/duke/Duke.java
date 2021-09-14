package duke;

import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;
import javafx.application.Application;
import javafx.fxml.FXML;

/**
 * Represents the task list manager Duke.
 */
public class Duke {

    private Ui ui;
    private TaskList list;
    private Parser parser;

    /**
     * Constructs Duke.
     */
    public Duke(String fileName) {
        this.ui = new Ui();
        this.list = new TaskList(fileName);
        this.parser = new Parser(this.list);
    }

    public static void main(String[] args) {
        new Duke("list.ser");
        Application.launch(Main.class, args);
    }

    /**
     * Gets response from duke.Duke with the input being the prompt.
     *
     * @param input Prompt for duke.Duke, Users input.
     * @return duke.Duke's reponse as a String.
     */
    @FXML
    public String getResponse(String input) {
        return parser.parseAndExecute(input);
    }

    /**
     * Prints out welcome statement.
     *
     * @return Welcome message for new user.
     */
    public String run() {
        return ui.welcome();
    }
}
