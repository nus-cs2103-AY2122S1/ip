public class Deadlines extends Task {

    private String dateTimeBy;
    public Deadlines(String description, String dateTimeBy) {
        super(description);
        this.dateTimeBy = dateTimeBy;
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + "(%s:%s)", this.dateTimeBy.substring(0,2), this.dateTimeBy.substring(2));
    }
}
