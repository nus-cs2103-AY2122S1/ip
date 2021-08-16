public class DukeException extends Exception {
    public DukeException(String description) {
        super(description);
    }
    @Override
    public String toString() {
        String strBreak = "    ____________________________________________________________";
        return String.format("%s\n    %s\n%s", strBreak, super.getMessage(), strBreak);
    }
}
