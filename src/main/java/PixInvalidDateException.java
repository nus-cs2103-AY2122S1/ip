public class PixInvalidDateException extends PixException {
    @Override
    public String getMessage() {
        return "Invalid date! Give me a date in yyyy-mm-dd!";
    }
}
