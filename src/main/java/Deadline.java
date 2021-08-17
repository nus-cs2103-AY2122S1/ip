public class Deadline extends Task {

    private String date;

    public Deadline(String title, int indexNumber, String date) {
        super(title, indexNumber);
        this.date = date;
    }

    @Override
    public String getInfo() {
        return "[D][ ]" + this.getTitle() + "(by: " + this.date + ")";
    }

    @Override
    public String toString() {
        if (!this.getDone()) {
            return String.format(this.getIndexNumber() + ".[D][ ]" + this.getTitle() + "(by: " + this.date + ")");
        } else {
            return String.format(this.getIndexNumber() + ".[D][X]" + this.getTitle() + "(by: " + this.date + ")");
        }

    }
}