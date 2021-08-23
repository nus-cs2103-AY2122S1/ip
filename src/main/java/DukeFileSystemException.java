public class DukeFileSystemException extends RuntimeException {
    private static final String preMessage = "ERROR: ";

    public DukeFileSystemException(String message) {
        super(preMessage + message);
    }
}
