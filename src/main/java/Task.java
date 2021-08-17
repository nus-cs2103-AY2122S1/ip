public class Task {
    private String name;
    private boolean completion;

    public Task(String name) {
        this.name = name;
        this.completion = false;
    }

    public void setCompleted() {
        this.completion = true;
    }

    public String getStatusIcon() {
        return (this.completion
                ?"X"
                :" ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}