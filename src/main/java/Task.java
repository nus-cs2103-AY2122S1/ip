public class Task {
    protected String description;
    protected boolean status;

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public String getStatusIcon() {
        return (status ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void setStatus(boolean status) {
        this.status= status;
    }
}
