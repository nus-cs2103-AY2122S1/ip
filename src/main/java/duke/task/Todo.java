package duke.task;

import duke.command.MalformedCommandException;

public class Todo extends Task {
    public static final String IDENTIFIER = "T";

    public static Task create(String userInput) throws MalformedCommandException {
        try {
            String description = userInput.split(" ", 2)[1];
            return new Todo(description);
        } catch(ArrayIndexOutOfBoundsException e){
            throw new MalformedCommandException("Creating an todo needs to follow the following format: " +
                "todo [description]");
        }
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + IDENTIFIER + "]" + super.toString();
    }

    @Override
    public String toStorageFormat() {
        return IDENTIFIER + Task.STORAGE_DELIMITER + super.toStorageFormat();
    }
}
