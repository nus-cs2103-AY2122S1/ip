import duke.util.List;
import duke.util.Parser;
import duke.util.Ui;

public class Duke {

    private Ui ui;
    private List list;
    private Parser parser;

    /**
     * Constructor of Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.list = new List();
        this.parser = new Parser(this.list);
    }

    /**
     * Run the Duke program, prints out welcome statement and starts accepting input.
     */
    public void run() {
        ui.welcome();
        parser.execute();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
