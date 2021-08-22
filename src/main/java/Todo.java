package main.java;

public class Todo extends Task {

    /**
     * Constructor for a new Todo object.
     * @param description description of the Todo Task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
