public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toText() {
        String[] props = new String[]{"T", super.getStatusIcon(), super.getName()};
        return String.join(" | ", props);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
