package duke;

public class Place {
    private String name;
    private String details = "0";

    /**
     * Constructor for a place.
     *
     * @param name Name of Place.
     */
    public Place(String name) {
        this.name = name;
    }

    /**
     * Constructor for a place with details.
     *
     * @param name Name of Place.
     * @param details Details of Place, eg (Address...).
     */
    public Place(String name, String details) {
        this.name = name;
        this.details = details;
    }

    /**
     * Converts the Place into a form that writes into storage.
     *
     * @return String representation of Place to write into Storage.
     */
    public String toWrite() {
        if (name == "") {
            return "0--0\n";
        } else {
            return name + "--" + details + "\n";
        }
    }

    public void addDetails(String details) {
        this.details = details;
    }

    /**
     * Returns true if given object is equal to this object.
     *
     * @param other Object to compare with.
     * @return True if the 2 objects are equal, else false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other.getClass() != this.getClass()) {
            return false;
        } else {
            Place p = (Place) other;
            return this.name.equals(p.name);
        }
    }

    /**
     * Returns a String representation of a Place.
     *
     * @return String representation of a Place.
     */
    @Override
    public String toString() {
        return name;
    }

}
