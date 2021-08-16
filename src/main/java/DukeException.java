public class DukeException extends Throwable{
    public DukeException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
