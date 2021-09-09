package bloom.constant;

/**
 * Contains drawings used for decoration.
 */
public enum Drawing {

    HORIZONTAL_LINE("* * * * * * * * * * * * * * * * * * * *"),
    LOGO("\t   ____  _                       \n"
        + "\t  |  _ \\| |                      \n"
        + "\t  | |_) | | ___   ___  _ __ ___  \n"
        + "\t  |  _ <| |/ _ \\ / _ \\| '_ ` _ \\ \n"
        + "\t  | |_) | | (_) | (_) | | | | | |\n"
        + "\t  |____/|_|\\___/ \\___/|_| |_| |_|\n");

    /** The drawing. */
    private final String drawing;

    /**
     * Constructor for a Drawing.
     *
     * @param drawing the drawing
     */
    Drawing(String drawing) {
        this.drawing = drawing;
    }

    /**
     * Gets the drawing as a string.
     *
     * @return a string representing the drawing
     */
    public String getDrawing() {
        return this.drawing;
    }
}
