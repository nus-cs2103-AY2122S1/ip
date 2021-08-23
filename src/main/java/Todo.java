public class Todo extends Task {
    public Todo(String title) {
        super(title, TypeIndicators.TODO);
    }

    public Todo(String title, boolean isDone) {
        super(title, TypeIndicators.TODO);
        this.isDone = isDone;
    }
}