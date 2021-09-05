package duke.contact;

/**
 * The Contact class encapsulates a contact object.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class Contact {
    private String name;
    private String phoneNumber;
    private String email;
    private String notes;

    public Contact(String name, String phoneNumber, String email, String notes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.notes = notes;
    }

    @Override
    public String toString() {
        int len = phoneNumber.length();
        String FirstHalf = phoneNumber.substring(0, len / 2);
        String SecondHalf = phoneNumber.substring(len / 2, len);
        return this.name + "\n" + "Phone Number: " + FirstHalf + " " + SecondHalf + "\n"
                + "Email: " + this.email + "\n" + "Notes: " + this.notes;
    }

    public String getReadableString() {
        return this.name + "\n" + this.phoneNumber + "\n"
                + this.email + "\n" + this.notes;
    }
}
