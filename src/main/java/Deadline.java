public class Deadline extends Task {
    private String date;

    public Deadline(String description, String date) {
        super(description, Type.DEADLINE);
        this.date = date;
    }

    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description + "(by: "
                + date + ")";
    }
}
