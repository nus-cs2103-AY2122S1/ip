package duke;

import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;
import javafx.application.Application;
import javafx.fxml.FXML;

public class Duke {

    private Ui ui;
    private TaskList list;
    private Parser parser;

    /**
     * Constructor of duke.Duke.
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
     * Runs the duke.Duke program, prints out welcome statement and starts accepting input.
     * @return
     */
    public String run() {
        return ui.welcome();

    }
}
