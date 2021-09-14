package type;

/**
 * Encapsulates valid formats for time.
 */
public enum TimeFormatTypeEnum {
    INPUT("HHmm"),
    OUTPUT("h:mm a");

    private final String format;

    TimeFormatTypeEnum(String format) {
        this.format = format;
    }

    /**
     * Returns string representation of a time format type enum.
     *
     * @return String representation of a time format type enum
     */
    @Override
    public String toString() {
        return this.format;
    }
}
