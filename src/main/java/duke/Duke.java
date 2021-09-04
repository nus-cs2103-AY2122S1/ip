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
     *
     * @param args Arguments for main method.
     */
    public static void main(String[] args) {
        Ui chatBot = new Ui();
        chatBot.run();
    }

    /**
     * Returns response from Duke chat-bot for a given input.
     *
     * @param input User input into Duke chat-bot.
     * @return String object representing response from Duke chat-bot.
     */
    public String getResponse(String input) {
        GraphicUi guiParser = new GraphicUi();
        return guiParser.run(input);
    }
}
