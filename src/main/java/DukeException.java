public class DukeException extends Exception {
    private String log;

    public DukeException(String log) {
        this.log = log;
    }

    @Override
    public String toString() {
        return this.log;
    }
}
