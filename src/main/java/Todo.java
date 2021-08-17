public class Todo extends Task {
    private final char TYPE_INDICATOR = 'T';

    public Todo(String s) throws DukeException {
        super(s);
        this.typeIndicator = TYPE_INDICATOR;
    }
}