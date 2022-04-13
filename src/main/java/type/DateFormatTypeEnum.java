package type;

/**
 * Encapsulates valid formats for date.
 */
public enum DateFormatTypeEnum {
    INPUT("dd-MM-yyyy"),
    OUTPUT("d MMM yyyy");

    private final String format;

    DateFormatTypeEnum(String format) {
        this.format = format;
    }

    /**
     * Returns string representation of a date format type enum.
     *
     * @return String representation of a date format type enum
     */
    @Override
    public String toString() {
        return this.format;
    }
}
