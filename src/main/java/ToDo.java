public class ToDo extends Task{
    ToDo(String name) {
        super(name);
    }

    ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public ToDo markAsDone() {
        return new ToDo(super.getName(), true);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
