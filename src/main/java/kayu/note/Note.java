package kayu.note;

/**
 * Represents a note instance for {@link kayu.Kayu}.
 */
public class Note {

    private final String description;

    /**
     * Initializes a new Note instance.
     *
     * @param description String description of Note.
     */
    public Note(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the Note instance.
     *
     * @return Description of the Note as a String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return description;
    }
}
