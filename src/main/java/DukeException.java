public class DukeException extends Exception {
    private String error;
    private static String indent = "    ";
    private static String div_line = "    ____________________________________________________________";


    public DukeException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return this.error;
    }
}
