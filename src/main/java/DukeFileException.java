public class DukeFileException extends Exception {
    public DukeFileException(String msg){
        super(msg);
    }

    public DukeFileException(String customMsg, String additionalMsg) {
        super(String.format("%s\n%s", customMsg, additionalMsg));
    }
}
