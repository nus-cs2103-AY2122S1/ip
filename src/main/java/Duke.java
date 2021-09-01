import duke.Parser;
import duke.Ui;

/**
 * Main program
 */
public class Duke {
    public static void main(String[] args) {
        Ui.sendGreetings();
        Parser parser = new Parser();
        parser.start();
    }
}
