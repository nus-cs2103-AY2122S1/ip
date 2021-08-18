public class DukeException extends RuntimeException {
    private String msg;
    public DukeException(String msg) {
        this.msg = msg;
    }

    public void printMsg() {
        System.out.println(msg);
    }
}
