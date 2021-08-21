public class Deadline extends Task {
    private String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    @Override
    public String toText() {
        String[] props = new String[]{"D", super.getStatusIcon(), super.getName(), this.dueDate};
        return String.join(" | ", props);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate);
    }
}
