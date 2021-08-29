package duke.assignment;

public enum AssignmentType {

    TODO("[T]"), EVENT("[E]"), DEADLINE("[D]");
    private final String type;

    AssignmentType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return this.type;
    }
}
