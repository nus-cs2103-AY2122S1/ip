public class ToDo extends Task{

    private ToDo(String description) {
        super(description);
    }

    public static ToDo of(String description) {
        return new ToDo(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
