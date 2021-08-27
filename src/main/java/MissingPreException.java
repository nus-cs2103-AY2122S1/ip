public class MissingPreException extends DukeException {
    private String pre;
    public MissingPreException(String msg, String pre) {
        super(msg);
        this.pre = pre;
    }

    @Override
    public void printError() {
        System.out.printf("( ⚆ _ ⚆ ) OOPS!!! Missing %s preposition!", pre);
    }
}
