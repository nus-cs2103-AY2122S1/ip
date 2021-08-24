class Deadline extends Task {

    private final String dueOn;

    Deadline(String taskName, String dueOn) {
        super(taskName);
        this.dueOn = dueOn;
    }

    private Deadline(Deadline oldDeadline) {
        super(oldDeadline);
        this.dueOn = oldDeadline.dueOn;
    }
    
    static protected Deadline createTask(String name, boolean isCompleted, String dateTime) {
        Deadline d = new Deadline(name, dateTime);
        if (isCompleted) {
            return new Deadline(d);
        } else {
            return d;
        }
    }

    @Override
    Deadline markAsCompleted() {
        return new Deadline(this);
    }

    @Override
    public String toString() {
        return "D: " + super.toString() + " [" + this.dueOn + "]";
    }
}
