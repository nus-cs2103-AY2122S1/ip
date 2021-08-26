class ToDo extends Task {

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public ToDo(String description) {
        super(description);
    }

    public String formatForSave() {
        return "T" + super.formatForSave();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
