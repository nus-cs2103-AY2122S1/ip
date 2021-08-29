package duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Represents the chat bot
 */
public class Duke {
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

//    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/bot.jpg"));

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
       boolean isExit = false;

       String commandString;
       while (!isExit) {
           commandString = ui.readCommand();
           if (commandString.length() <= 0) continue;
           String response = getResponse(commandString);
           ui.print(response);
           if (CommandParser.isExit(commandString)) {
               break;
           }
       }
    }

    public String getResponse(String input) {
        try {
            String message = CommandParser.parse(input).run();
            return message;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String greet() {
        return "Yo, I'm Xiri.\nHow can I help you?";
    }

    /**
     * The main method. A Duke instance is instantiated and run.
     *
     * @param args the command line arguments (ignored)
     */
    public static void main(String[] args) {
       new Duke().run();
    }
}
