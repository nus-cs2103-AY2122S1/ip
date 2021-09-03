package type;

public enum DatetimeTypeEnum {
    INPUT("dd-MM-yyyy HHmm"),
    OUTPUT("MMM d yyyy HHmm");

    private final String format;

    DatetimeTypeEnum(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return this.format;
    }
}
