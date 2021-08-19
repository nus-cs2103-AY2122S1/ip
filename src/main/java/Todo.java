public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return ("\t[T]" + super.toString());
    }
}
