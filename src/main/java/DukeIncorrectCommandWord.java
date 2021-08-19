/**
 * Represents the error object that is thrown when
 * the user keys in an invalid or incorrect command
 */
public class DukeIncorrectCommandWord extends DukeIncorrectInputs {

    /**
     * Constructor to create the DukeIncorrectCommandWord Exception object
     * @param err error that is to be thrown
     */
    public DukeIncorrectCommandWord(Throwable err) {
        super("\t⚠️ OOPS! I'm sorry, but I don't know what that means! ⚠️", err);
    }
}
