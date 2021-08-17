public class Todo extends Task{
    private String name;
    private boolean isDone;
    private int index;
    private String type;

    public Todo(String name, boolean isDone, int index) {
        super(name, isDone, index);
    }
}
