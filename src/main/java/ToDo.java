/**
 * ToDo class: For tasks without any date/time attached to it
 * e.g., visit new theme park
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description, Type.TODO);
    }

    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description;
    }
}
