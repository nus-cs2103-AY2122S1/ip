public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object.getClass().equals(this.getClass()))) {        // getClass to inherit in subclasses of class
            return false;
        } else {
            Task t = (Task) object;
            if (this.description == null) {
                return (t.description == null);
            } else {
                return (this.description.equalsIgnoreCase(t.description));
            }
        }
    }
}