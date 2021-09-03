package type;

/**
 * Encapsulates valid formats for datetime.
 */
public enum DatetimeTypeEnum {
    INPUT("dd-MM-yyyy HHmm"),
    OUTPUT("MMM d yyyy HHmm");

    private final String format;

    DatetimeTypeEnum(String format) {
        this.format = format;
    }

    /**
     * Returns string representation of a datetime type enum.
     *
     * @return String representation of a datetime type enum
     */
    @Override
    public String toString() {
        return this.format;
    }
}
