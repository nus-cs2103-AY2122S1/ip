public class ToDo extends Task {
    private String name;

    ToDo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String logo() {
        return "[T]";
    }
}
