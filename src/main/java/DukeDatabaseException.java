public class DukeDatabaseException extends DukeException {
    @Override
    public String toString() {
        return String.format("%s Encountered fatal problem with the database!", super.toString());
    }
}