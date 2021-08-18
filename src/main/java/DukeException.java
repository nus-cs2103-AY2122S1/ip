public class DukeException {
    private static final String LINEBREAK = "____________________________________________________________";

    protected DukeException(String message) {
        System.out.printf("%s\n%s\n%s\n", LINEBREAK, message, LINEBREAK);
    }
}
