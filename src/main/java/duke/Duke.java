package duke;

import config.Setting;
import javafx.util.Pair;
import parser.Parser;
import processor.Processor;
import storage.Storage;
import ui.Ui;

/**
 * Main class for Dub chatbot.
 */
public class Duke {

    private static final Parser parser = new Parser(
            new Processor(new Storage(Setting.FILE_DIRECTORY, Setting.FILE_NAME)));

    /**
     * Initializes and runs duke.
     *
     * @param args Array of sequence of characters (Strings) that are passed to the main function.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        Ui.print("Hello from\n" + logo);
        Ui.print("Wait, I'm not Duke. I'm Dub!\nWhat can I do for you?");
        listen();
    }

    /**
     * Listens to the parser and user input.
     */
    public static void listen() {
        while (true) {
            boolean isListening = parser.nextLine().getKey();
            if (!isListening) {
                break;
            }
        }
    }

    /**
     * Return the response of the chatbot according to the user input.
     *
     * @param input Input from the user.
     * @return Response from the chatbot.
     */
    public Pair<Boolean, String> getResponse(String input) {
        return parser.getResponse(input);
    }
}
