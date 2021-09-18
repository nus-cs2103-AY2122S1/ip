package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (this.getTag() != null) {
            return "[T]" + super.toString() + " " + this.getTag();
        } else {
            return "[T]" + super.toString();
        }
    }

    @Override
    public String toStringForStorage() {
        if (this.getTag() != null) {
            return "todo " + super.toStringForStorage() + "/ " + super.isDone + " " + this.getTag();
        } else {
            return "todo " + super.toStringForStorage() + "/ " + super.isDone;
        }
    }
}
