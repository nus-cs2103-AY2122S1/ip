package duke.place;

/**
 * Class to encapsulate a place.
 */
public class Place {

    private String name;

    /**
     * Constructor for the Place class.
     *
     * @param name the name of the place;
     */
    public Place(String name) {
        this.name = name;
    }

    /**
     * Returns the string representation of the place.
     *
     * @return the name of the place.
     */
    @Override
    public String toString() {
        return name;
    }
}
