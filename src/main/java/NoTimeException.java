public class NoTimeException  extends Exception{
    public NoTimeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return String.format("No Time Provided: %s", super.getMessage());
    }
}
