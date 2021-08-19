/**
 * This represents a Task object for the list.
 * 
 * @author Rishabh Anand
 * @version CS2103, AY21/222 Semester 1
 * 
 */
public class Task {
    protected String description;
    protected Boolean status;

    public Task(String desc) {
        this.description = desc;
        this.status = false;
    }

    // Sets the task as completed
    public void setDone() {
        this.status = true;
    }

    // returns the task status
    public Boolean getStatus() {
        return this.status;
    }

    // returns the string representation of the status
    public String getStatusString() {
        return this.status ? "[X]" : "[ ]";
    }

    // returns the textual description of the task
    public String getDescription() {
        return this.description;
    }

    // compares the task with another
    public Boolean equals(Task t) {
        if (t.getDescription().equals(this.description)) {
            return true;
        } else {
            return false;
        }
    }

    // returns the string representation of the Task
    @Override
    public String toString() {
        return this.getStatusString() + " " + this.getDescription();
    }
}
