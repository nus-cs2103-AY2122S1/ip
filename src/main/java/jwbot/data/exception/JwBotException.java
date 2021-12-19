package jwbot.data.exception;

/**
 * Class for chatbot exceptions
 *
 * @author Yim Jaewon
 */
public class JwBotException extends Exception {

    /**
     * The constructor of the exception.
     *
     * @param e the description of the exception
     */
    public JwBotException(String e) {
        super(e);
        assert e != null && !e.equals("");
    }
}
