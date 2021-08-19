public class Deadline extends Task{

    private final String deadline;

    public Deadline(String description) {
        super(description);
        if (description.isBlank()) {
            throw new IllegalArgumentException("Description of Deadline cannot be empty!");
        }
        this.type = 'D';
        this.deadline = "";
    }

    public Deadline(String description, String deadline) {
        super(description);
        this.type = 'D';
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadline + ")";
    }

}
