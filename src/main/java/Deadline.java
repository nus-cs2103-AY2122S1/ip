public class Deadline extends Task {

    private String date;

    public Deadline(String title, String date) {
        super(title);
        this.date = date;
    }

    @Override
    public String getInfo() {
        return "[D][ ]" + this.getTitle() + "(by: " + this.date + ")";
    }

    @Override
    public String toString() {
        if (!this.getDone()) {
            return String.format("[D][ ]" + this.getTitle() + "(by: " + this.date + ")");
        } else {
            return String.format("[D][X]" + this.getTitle() + "(by: " + this.date + ")");
        }

    }
}