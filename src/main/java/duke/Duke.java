package duke;
import duke.ui.GraphicUi;
import duke.ui.Ui;

/**
 * Duke class used to run the duke.Duke chat-bot.
 * Contains methods that
 * (i)    initialises the Ui class and runs the chat-bot.
 */
public class Duke {
    /**
     * Initialises the chatBot and runs the Duke chatBot.
     * @param args arguments for main method.
     */
    public static void main(String[] args) {
        Ui chatBot = new Ui();
        chatBot.run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        GraphicUi guiParser = new GraphicUi();
        return guiParser.run(input);
    }
}
