public class Todo extends Task {
    private final char TYPE_INDICATOR = 'T';

    public Todo(String s) {
        super(s);
        this.typeIndicator = TYPE_INDICATOR;
    }
}