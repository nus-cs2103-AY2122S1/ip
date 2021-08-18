public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }

    public void print() {
        System.out.println(super.getMessage());
    }
}
