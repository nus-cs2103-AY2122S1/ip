public class ToDo extends Task {
    public ToDo(String title) {
        super(title);
    }

    static String getClassRepr() {
        return "T";
    }

    public String toString() {
        return "[" + getClassRepr() + "]" + super.toString();
    }
}
