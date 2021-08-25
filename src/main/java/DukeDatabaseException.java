public class DukeDatabaseException extends DukeException {
    @Override
    public String toString() {
        return String.format("%s I cannot find the task with number %d!", super.toString());
    }
}
