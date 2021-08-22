public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toCommand(int index) {
        return String.format("todo %s\n%s", this.name, super.toCommand(index));
    }
}
