package duke.Client;

public class Phone {
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String phone;

    Phone(String phone) throws IllegalArgumentException {
        if (!isValidPhone(phone)) {
            throw new IllegalArgumentException();
        }
        this.phone = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return phone;
    }

}
