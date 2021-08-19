public class JadenInputException extends Exception {

    private JadenInputException(String description) {
        super(description);
    }

    public static JadenInputException invalidTodo() {
        return new JadenInputException("   The Description Of A ToDo Cannot Be Empty. Do Better.");
    }

    public static JadenInputException unrecognized() {
        return new JadenInputException("   I Only Listen To Valid Commands. You Are Invalid.");
    }
}
