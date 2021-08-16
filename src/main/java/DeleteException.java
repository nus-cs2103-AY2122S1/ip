public class DeleteException extends Exception {
    public DeleteException() {
        super("There is nothing to delete here!");
    }

    public DeleteException(String message) {
        super(message);
    }

}
