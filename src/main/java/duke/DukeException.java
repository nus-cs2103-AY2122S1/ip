package duke;

/**
 * This class is a subclass of exception.
 *
 * @author Deng Huaiyu(G12)
 * @version CS2103T AY21/22 Semester 1
 */
public class DukeException extends Exception {
    private static final String IND = "    ";
    //for sentences
    private static final String IND_2 = "     ";
    private static final String DIV = IND + "";
    private final String message;

    /**
     * The construction method of an exception
     *
     * @param m message of the exception
     */
    DukeException(String m) {
        this.message = m;
    }

    /**
     * Prints the exception
     *
     * @return string form of the exception
     */
    @Override
    public String toString() {
        return DIV + "\n" + IND_2 + message + "\n" + DIV;
    }
}
