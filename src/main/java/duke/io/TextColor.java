package duke.io;

public enum TextColor {
    RED("\u001B[31m"),
    YELLOW("\u001B[33m"),
    DEFAULT("\u001B[0m");

    private final String ansiCode;

    TextColor (String ansiCode) {
        this.ansiCode = ansiCode;
    }

    @Override
    public String toString() {
        return ansiCode;
    }
}
