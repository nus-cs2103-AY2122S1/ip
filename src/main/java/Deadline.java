public class Deadline extends Task {
    private String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    public Deadline(String name, boolean isDone, String dueDate) {
        super(name, isDone);
        this.dueDate = dueDate;
    }

    public static Deadline fromText(String text) throws DukeException {
        String[] deadlineDetails = text.split(" \\| ", 4);
        if (deadlineDetails.length < 4) {
            throw new DukeException(String.format("Cannot parse Deadline from \n\t`%s`", text));
        }
        boolean isDone = deadlineDetails[1].equals("X");
        String name = deadlineDetails[2];
        String dueDate = deadlineDetails[3];
        return new Deadline(name, isDone, dueDate);
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
