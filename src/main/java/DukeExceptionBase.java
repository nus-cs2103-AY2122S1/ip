public class DukeExceptionBase extends Exception {

    public String exceptionMsg;

    public DukeExceptionBase(String msg) {
        this.exceptionMsg = msg;
    }

    public void dukeSayErrorMsg() {
        Duke.dukeSays(this.exceptionMsg);
    }

}
