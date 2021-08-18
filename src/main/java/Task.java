public class Task {
    private String description;
    private boolean status;

    /**
     * Constructor
     * @param description The string that describes the Task.
     */
    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    /**
     * Correctly assigns the task a string that indicates if it has been marked as done or not.
     * @return String that hows the correct icon.
     */
    public String getStatusIcon() {
        if (!status) {
            return "[ ] ";
        } else {
            return "[X] ";
        }
    }


    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

}
