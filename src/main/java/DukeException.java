public class DukeException extends Exception {
    private String e;

    public DukeException(String e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return e;
    }

}
