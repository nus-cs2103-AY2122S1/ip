package duke;

/**
 * Main class of Duke
 *
 * @author Jay Aljelo Saez Ting
 */
public class Duke {
    /**
     * Initialises the Duke chatbot.
     * Command line arguments are ignored.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        DukeChatbot dukeChatbot = new DukeChatbot();
        dukeChatbot.printGreeting();
        dukeChatbot.listenForInput();
    }
}
