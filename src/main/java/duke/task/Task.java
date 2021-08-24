public class Task {
    protected String description;
    protected boolean isDone;



    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;

    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDoneIndicator() {
        String doneIndicator;
        if (isDone == false) {
            doneIndicator = "0";
        } else {
            doneIndicator = "1";
        }
        return doneIndicator;
    }


    public String getDescription() {
        return this.description;
    }


    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toFileString() {
        return "|" + this.getDoneIndicator() + "|" + this.description;
    }



    public void maskAsDone() {
        isDone = true;
    }
    
}
