/**
 * ToDo class: For tasks without any date/time attached to it
 * e.g., visit new theme park
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description, Type.TODO);
    }

    public ToDo(boolean isDone, String description) {
        super(isDone, description, Type.TODO);
    }

    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description;
    }
}
