package bobbybot.person;

public class Person {
    private final Name name;
    private final Email email;
    private final Phone phone;
    private final Address address;

    /**
     * Constructs a person object, all fields must be present
     * @param name
     * @param email
     * @param phone
     * @param address
     */
    public Person(Name name, Email email, Phone phone, Address address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(name)
                .append("; Phone: ")
                .append(phone)
                .append("; Email: ")
                .append(email)
                .append("; Address: ")
                .append(address);
        return builder.toString();
    }

    /**
     * Get save format string for text file
     * @return save format string
     */
    public String getSaveFormatString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(name)
                .append(";")
                .append(phone)
                .append(";")
                .append(email)
                .append(";")
                .append(address);
        return builder.toString();
    }
}
