package duke.task;

/**
 * To do is an extension of Task which allows it to have it's own unique toString method.
 */
public class Todo extends Task {

    public Todo(String str) {
        super(str);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(super.equals(obj))) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Todo)) {
            return false;
        }
        return true;
    }
}
