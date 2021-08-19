/**
 * This class is a subclass of exception.
 *
 * @author Deng Huaiyu(G12)
 * @version CS2103T AY21/22 Semester 1
 */
public class DukeException extends Exception{
    private static String ind = "    ";
    //for sentences
    private static String ind2 = "     ";
    private static String div = ind + "____________________________________________________________";
    private String message;

    /**
     * The construction method of an exception
     *
     * @param m message of the exception
     */
    DukeException(String m) {
        this.message = m;
    }

    /**
     * The method to print the exception
     *
     * @return string form of the exception
     */
    @Override
    public String toString() {
        return div + "\n" + ind2 + message + "\n" + div;
    }
}
