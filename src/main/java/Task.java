public class Task {
    protected String description;
    protected Boolean status;

    public Task(String desc) {
        this.description = desc;
        this.status = false;
    }

    public void setDone() {
        // sets the task as completed
        this.status = true;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public String getStatusString() {
        return this.status ? "[X]" : "[ ]";
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean equals(Task t) {
        if (t.getDescription().equals(this.description)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.getStatusString() + " " + this.getDescription();
    }
}
