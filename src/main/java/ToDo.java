public class ToDo extends Task{
    public ToDo(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
