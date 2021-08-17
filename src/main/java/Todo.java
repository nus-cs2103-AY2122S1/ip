public class Todo extends Task {

    public Todo(String description) {
        super(description, Type.TODO);
    }

    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description;
    }
}
