public class InvalidDeadlineException extends Exception {
    @Override
    public String toString() {
        return "Please enter deadline in the format yyyy-mm-dd";
    }
}
