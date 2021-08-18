public class Task {
    private String action;
    private Boolean isDone = false;
    
    public Task(String action){
        this.action = action;
    }
    
    public void markAsDone() {
        this.isDone = true;
    }
    
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + action;
        } else {
            return "[ ] " + action;
        }
    }
}
