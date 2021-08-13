public class ToDo extends Task {

    ToDo(String name) {
        super(name);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
