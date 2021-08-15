public class ToDos extends Task {
    private static String label = "[T]";
    public ToDos(String s) {
        super(s.trim());

    }

    @Override
    public String toString() {
        return this.label + super.toString();
    }
}
