package main.java;

public class Todo extends Task {

    /**
     * Constructor for a new TooDo object
     * @param description description of the TooDo Task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
