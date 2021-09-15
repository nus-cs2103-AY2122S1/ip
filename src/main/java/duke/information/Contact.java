package duke.information;

/**
 * Class that stores a Contact.
 */
public class Contact {

    /** Name of the contact. */
    protected String name;
    /** Details of the contact. */
    protected String detail;

    /**
     * Constructs Contact class.
     *
     * @param name Name of the contact.
     * @param detail Details of the contact.
     */
    public Contact(String name, String detail) {
        this.name = name;
        this.detail = detail;
    }

    /**
     * Converts the contact's information into a string.
     * To be stored in the user's dedicated txt file.
     *
     * @return String of the contact information.
     */
    public String toData() {
        return "C|" + this.name + "|" + this.detail + "\n";
    }

    /**
     * Converts the contact's information into a string.
     * To be used to display the contact information to the user.
     *
     * @return String of the contact information.
     */
    @Override
    public String toString() {
        if (this.detail.isBlank()) {
            return "[C] " + this.name;
        } else {
            return "[C] " + this.name + " (detail: " + this.detail + " )";
        }
    }
}