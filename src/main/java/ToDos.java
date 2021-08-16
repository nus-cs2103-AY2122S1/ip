public class ToDos extends Task {

    public static ToDos of(String description) throws DukeException {
        return new ToDos(description);
    }

    private ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}