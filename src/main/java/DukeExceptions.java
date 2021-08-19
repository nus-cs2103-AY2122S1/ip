public class DukeExceptions extends Exception {
    public DukeExceptions(String message) {
        super("Error Occured: " + message);
    }
}
