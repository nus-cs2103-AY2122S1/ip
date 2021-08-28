package duke.shared;

// This needs to be unchecked, otherwise commands hashmap will be quite troublesome
/**
 * Main exception class for the chatbot. Responsible for some of the more
 * generic errors. Subclasses cover errors with narrower scope.
 */
public class DukeException extends RuntimeException {
    /**
     * Encapsulates a generic error message for relatively generic error types.
     */
    public enum ExceptionCode {
        UNPROCESSABLE_ENTITY("Unprocessable entity"), INCORRECT_ARGS("Incompatible arguments"),
        FEWER_THAN_EXPECTED_ARGS("Too few arguments provided"), MORE_THAN_EXPECTED_ARGS("Too many arguments provided"),
        OUT_OF_BOUNDS("Out of bounds");

        private String message;

        private ExceptionCode(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    protected ExceptionCode exceptionCode;

    /**
     * Creates a DukeException from the given code.
     * @param exceptionCode Code.
     */
    public DukeException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    /**
     * Creates a DukeException from the given code and a message in addition to the code's message.
     * @param exceptionCode Code.
     * @param additionalMessage Message to supplement the exceptionCode's message.
     */
    public DukeException(ExceptionCode exceptionCode, String additionalMessage) {
        super(exceptionCode.getMessage() + "; " + additionalMessage);
        this.exceptionCode = exceptionCode;
    }

    /**
     * Creates a DukeException from the given code and a throwable instance.
     * @param exceptionCode Code.
     * @param error Root cause of the DukeException.
     */
    public DukeException(ExceptionCode exceptionCode, Throwable error) {
        super(exceptionCode.getMessage(), error);
        this.exceptionCode = exceptionCode;
    }

    /**
     * Creates a DukeException from the given code, additional message and a throwable instance.
     * @param exceptionCode Code.
     * @param additionalMessage Message to supplement the exceptionCode's message.
     * @param error Root cause of the DukeException.
     */
    public DukeException(ExceptionCode exceptionCode, String additionalMessage, Throwable error) {
        super(exceptionCode.getMessage() + "; " + additionalMessage, error);
        this.exceptionCode = exceptionCode;
    }

    /**
     * Creates a DukeException caused by wrong number of arguments provided.
     * @param expected Expected number of arguments.
     * @param given Number of arguments actually given by user.
     * @return DukeException.
     */
    public static DukeException createArgumentCountException(int expected, int given) {
        ExceptionCode code = given < expected ? ExceptionCode.FEWER_THAN_EXPECTED_ARGS
                : ExceptionCode.MORE_THAN_EXPECTED_ARGS;
        return new DukeException(code, String.format("Expected %d arguments, given %d", expected, given));
    }

    /**
     * Creates a DukeException caused by accessing out of bounds indices.
     * @param size Size of array or list-like data structure.
     * @param given Index user is trying to access.
     * @return DukeException.
     */
    public static DukeException createIndexOutOfBoundsException(int size, int given) {
        ExceptionCode code = ExceptionCode.OUT_OF_BOUNDS;
        return new DukeException(code, String.format("You have %d items, but you wanted item number %d", size, given));
    }
}
