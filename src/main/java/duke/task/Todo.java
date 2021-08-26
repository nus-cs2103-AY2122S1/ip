package duke.task;

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
