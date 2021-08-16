public class Todo extends Task{
    private final static String symbol = "[T]";
    public Todo(String action) {
        super(action);
    }

    public String toString() {
        return String.format("%s%s", symbol, super.toString());
    }
}
