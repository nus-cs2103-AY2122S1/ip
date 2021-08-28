package duke;

public class DukeExceptionBase extends Exception {

    private String exceptionMsg;

    public DukeExceptionBase(String msg) {
        this.exceptionMsg = msg;
    }

    public void dukeSayErrorMsg() {
        Duke.dukeSays(this.exceptionMsg);
    }

    @Override
    public String toString() {
        return exceptionMsg;
    }

}
