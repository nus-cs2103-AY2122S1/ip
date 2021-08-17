public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public void markFinished(){
        this.isDone = true;
    }
    
    public String toString() {
        return '[' + getStatusIcon() + ']' + ' ' + this.description;
    }
}
