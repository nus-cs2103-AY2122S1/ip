class ToDo extends Task {

    ToDo(String taskName) {
        super(taskName);
    }

    private ToDo(ToDo oldTask) {
        super(oldTask);
    }

    @Override
    ToDo markAsCompleted() {
        return new ToDo(this);
    }

    @Override
    public String toString() {
        return "T: " + super.toString();
    }
}
