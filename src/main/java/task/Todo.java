package main.java.task;

/**
 * The Todo class is a child class of Task.
 * @author  Hoon Darren
 * @version 1.0
 * @since   2021-08-21
 */
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
