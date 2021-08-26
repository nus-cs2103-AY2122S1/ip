package bubbles;

/**
 * A class that represents the exception when the Bubbles bot receives
 * an invalid command/command the bot does not understand.
 */
class InvalidCommandException extends Exception {
    /**
     * A public constrcutor for the InvalidCommandException.
     * @param message The message containing details of the InvalidCommandException.
     */
    public InvalidCommandException(String message) {
        super(message);
    }

    /**
     * Return the String representation of the Exception.
     *
     * @return The String representation of the Exception.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! I'm sorry, but I do not know what this means. ☹\n";
    }
}
