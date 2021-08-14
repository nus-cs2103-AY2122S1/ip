public class DukeException extends Exception {
    private String e;

    public DukeException(String e) {
        this.e = e;
    }

    @Override
    public String getMessage() {
        return " ☹ OOPS!!! " + e;
    }

}
