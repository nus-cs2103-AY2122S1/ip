package duke;

/**
 * Represents the chat bot
 */
public class Duke {
    private Ui ui;

    /**
     * Constructor for <code>Duke</code>
     */
    public Duke() {
        this.ui = new Ui();
    }

    /**
     * Runs the chat bot
     */
    public void run() {
       ui.greet();

       String commandString;
       while (true) {
           commandString = ui.readCommand();
           try {
               if (CommandParser.isExit(commandString)) {
                   break;
               }
               String message = CommandParser.parse(commandString).run();
               ui.print(message);
           } catch (DukeException e) {
               ui.printError(e.getMessage());
           }
       }
       ui.bye();
    }

    /**
     * The main method. A Duke instance is instantiated and run.
     *
     * @param args the command line arguments (ignored)
     */
    public static void main(String[] args) {
       Duke xiri = new Duke();
       xiri.run();
    }
}
