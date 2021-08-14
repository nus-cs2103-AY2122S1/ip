public class DukeListMessage extends DukeOutputMessage{
    /**
     * Constructor to instantiate a `DukeListMessage`.
     * Instantiates a parent `DukeOutputMessage`.
     *
     * @param message the string to be used in the list message
     */
    public DukeListMessage(String message) {
        super(message);
    }

    /**
     * Gets the message with a shocked face added to it
     *
     * @return the string message with a shocked face
     */
    @Override
    public String getMessageWithFace() {
        return this.getMessage() + "\n\tヽ(°〇°)ﾉ";
    }
}
