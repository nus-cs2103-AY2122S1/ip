package neo;

/**
 *  This class handles the farewell when the user exits the program.
 *
 * @author Ryan Tian Jun.
 */
public class Farewell {
    private final String farewellMessage = "Bye. Thank You for using Neo! :)";

    public Farewell() {

    }

    /**
     * Returns farewell message when user exits program.
     *
     * @return farewell message.
     */
    public String printFarewell() {
        return farewellMessage;
    }
}
