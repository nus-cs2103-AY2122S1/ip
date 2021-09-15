package parser;

import javafx.util.Pair;

/**
 * Interface for the Parser object which parses command from the user.
 */
public interface IParser {
    /**
     * Parses the command entered by the user.
     *
     * @param line Command that is entered by the user.
     * @return True if the command entered is not bye.
     */
    Pair<Boolean, String> parseLine(String line);

    /**
     * Allows Parser to take the next command from the user.
     *
     * @return True if the command entered is not bye and the response from the chatbot.
     */
    Pair<Boolean, String> nextLine();
}
