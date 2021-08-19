public class DukeException extends Exception{
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return ANSI_RED + "     " + super.getMessage() + "!!\n     Try Again \\_(\"v\")_/" + ANSI_RESET;
    }
}
