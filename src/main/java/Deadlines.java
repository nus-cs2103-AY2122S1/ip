public class Deadlines extends Task {

    private String dateTimeBy;
    public Deadlines(String description, String dateTimeBy, boolean isDone) {
        super(description, isDone);
        this.dateTimeBy = dateTimeBy;
    }
    @Override
    public String persistedDataStringFormat() {
        char isDone01 = this.isDone ? '1' : '0';
        return String.format("D,%c,%s,%s", isDone01, this.description, this.dateTimeBy);
    }
    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + " (%s:%s)", this.dateTimeBy.substring(0,2), this.dateTimeBy.substring(2));
    }
}
