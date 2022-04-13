package dac.exception;

/**
 * Represents an invalid instruction. Thrown when user input is an invalid instruction.
 */
public class InvalidInstructionException extends DukeException {

    private String instruction;

    /**
     * Constructor.
     *
     * @param str The invalid instruction.
     */
    public InvalidInstructionException(String str) {
        super(str);
        instruction = str;
    }

    /**
     * Returns a String representation of the exception.
     *
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return "Invalid instruction: " + instruction + " is not a valid instruction.";
    }
}
