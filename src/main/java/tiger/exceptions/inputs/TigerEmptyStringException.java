package tiger.exceptions.inputs;

public class TigerEmptyStringException extends TigerInvalidInputException {

    /**
     * Throws an exception if the input string is empty.
     *
     * @param property The field of the command which is detected to be empty.
     */

    public TigerEmptyStringException(String property) {
        super(String.format("%s property cannot be empty.\nPlease ensure you key in the command in "
                + "the format specified.", property));
    }
}
