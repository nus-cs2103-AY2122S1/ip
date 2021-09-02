import duke.util.TaskList;
import duke.util.Parser;
import duke.util.Ui;

import javafx.fxml.FXML;



public class Duke{

    private Ui ui;
    private TaskList list;
    private Parser parser;

    /**
     * Constructor of Duke.
     */
    public Duke(String fileName) {
        this.ui = new Ui();
        this.list = new TaskList(fileName);
        this.parser = new Parser(this.list);
    }

    /**
     * Gets response from Duke with the input being the prompt.
     *
     * @param input Prompt for Duke, Users input.
     * @return Duke's reponse as a String.
     */
    @FXML
    public String getResponse(String input) {
        return parser.parseAndExecute(input);
    }

    /**
     * Runs the Duke program, prints out welcome statement and starts accepting input.
     */
    public void run() {
        ui.welcome();
    }

    public static void main(String[] args) {
        new Duke("list.ser").run();
    }
}
