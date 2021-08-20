package shared;

// This needs to be unchecked, otherwise commands hashmap will be quite troublesome
/**
 * Main exception class for the chatbot.
 */
public class DukeException extends RuntimeException {
    public static enum ExceptionCode {
        UNPROCESSABLE_ENTITY("Unprocessable entity"), INCORRECT_ARGS("Incompatible arguments"),
        FEWER_THAN_EXPECTED_ARGS("Too few arguments provided"), MORE_THAN_EXPECTED_ARGS("Too many arguments provided"),
        OUT_OF_BOUNDS("Out of bounds");

        public String message;

        private ExceptionCode(String message) {
            this.message = message;
        }
    }

    private ExceptionCode exceptionCode;

    public DukeException(ExceptionCode exceptionCode) {
        super(exceptionCode.message);
        this.exceptionCode = exceptionCode;
    }

    public DukeException(ExceptionCode exceptionCode, String additionalMessage) {
        super(exceptionCode.message + "; " + additionalMessage);
        this.exceptionCode = exceptionCode;
    }

    public DukeException(ExceptionCode exceptionCode, Throwable error) {
        super(exceptionCode.message, error);
        this.exceptionCode = exceptionCode;
    }

    public DukeException(ExceptionCode exceptionCode, String additionalMessage, Throwable error) {
        super(exceptionCode.message + "; " + additionalMessage, error);
        this.exceptionCode = exceptionCode;
    }

    public static DukeException createArgumentCountException(int expected, int given) {
        ExceptionCode code = given < expected ? ExceptionCode.FEWER_THAN_EXPECTED_ARGS
                : ExceptionCode.MORE_THAN_EXPECTED_ARGS;
        return new DukeException(code, String.format("Expected %d arguments, given %d", expected, given));
    }

    public static DukeException createIndexOutOfBoundsException(int size, int given) {
        ExceptionCode code = ExceptionCode.OUT_OF_BOUNDS;
        return new DukeException(code, String.format("You have %d items, but you wanted item number %d", size, given));
    }
}
