package duke;

public class Todo extends Task {

    /**
     * Returns a ToDo instance.
     * @param description
     */
    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a ToDo instance.
     * @param description
     * @param isDone
     */
    public Todo(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String output = "[T]";
        if (this.isDone) {
            output += "[X] ";
        } else {
            output += "[] ";
        }
        output += this.description;
        return output;
    }

    /**
     * Returns proper format to write to txt file.
     */
    public String toWriteString() {
        String output = Duke.COMMAND_TODO;
        String isDone = (this.isDone ? "1" : "0");
        output += DIVIDER + isDone + DIVIDER + this.description;
        return output;
    }
}
