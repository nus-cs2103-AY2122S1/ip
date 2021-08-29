package duke;

/**
 *  This class handles the farewell when the user exits the program.
 *  May handle more features in the future such as saving to hard drive
 *
 * @author Ryan Tian Jun.
 */
public class Farewell {
    private String farewell = "Bye. Thank You for using Duke!";

    public Farewell() {

    }

    /**
     * Returns farewell message when user exits program.
     *
     * @return farewell message.
     */
    public String printFarewell() {
        return farewell;
    }
}
