/**
 * Main program
 */
public class Duke {
    public static void main(String[] args) {
        Ui.greetings();
        Parser parser = new Parser();
        parser.start();
    }
}
