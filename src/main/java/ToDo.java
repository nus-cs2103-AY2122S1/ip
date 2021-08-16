public class ToDo extends Item {

    public ToDo(String[] strings) {
        super(strings);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
