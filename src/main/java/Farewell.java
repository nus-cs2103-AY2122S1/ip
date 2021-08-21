/**
 *  This class handles the farewell when the user exits the program
 * @author Ryan Tian Jun
 */
public class Farewell {
    private String farewell = "Bye. Hope to see you again soon!";

    public Farewell() {

    }

    /**
     * Returns farewell message when user exits program
     *
     * @return farewell message
     */
    public String printFarewell() {
        System.out.println(farewell);
        return farewell;
    }
}
