public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String todoMarker = "[T]";
        if (isDone) {
            return todoMarker + hasCross + " " + item;
        } else {
            return todoMarker + hasNoCross + " " + item;
        }
    }
}
