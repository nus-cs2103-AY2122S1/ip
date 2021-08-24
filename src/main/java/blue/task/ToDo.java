package blue.task;

public class ToDo extends Task {
    public ToDo(String title) {
        super(title);
    }

    public static String getClassRepr() {
        return "T";
    }

    public String toString() {
        return "[" + getClassRepr() + "]" + super.toString();
    }
}
