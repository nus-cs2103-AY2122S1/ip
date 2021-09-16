package sun;

import sun.data.exception.SunException;
import sun.parser.Parser;

/**
 * Entry point of Sun Chatbot.
 * Initializes the application and starts the interaction with the user.
 *
 * @author Won Ye Ji
 */
public class Sun {
    private Parser parser;

    /**
     * Constructor for Sun.
     */
    public Sun() {
        parser = new Parser();
        parser.initialiseSun();
    }

    public String getResponse(String input) {
        try {
            return parser.runSun(input);
        } catch (SunException e) {
            return e.getMessage();
        }
    }
}
