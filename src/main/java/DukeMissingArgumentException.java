public class DukeMissingArgumentException extends DukeException {
    @Override
    public String toString() {
        return String.format("%s You are missing argument(s) for this command!", super.toString());
    }
}
