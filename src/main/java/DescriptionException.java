package main.java;

public class DescriptionException extends DukeException {
    public DescriptionException(String task) {
        super(String.format("The description of a %s cannot be empty.", task));
    }
}
