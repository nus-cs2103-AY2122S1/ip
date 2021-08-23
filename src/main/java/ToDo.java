public class ToDo extends Task {

    public ToDo(String description, boolean isDone, boolean hasNotif) {
        super(description, isDone);
        this.category = Category.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

