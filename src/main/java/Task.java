public class Task {
    private int index;
    private String name; 
    private boolean isDone = false; 

    public Task(int index, String name) {
        this.index = index;
        this.name = name; 
    }
    
    public void done() {
        isDone = true; 
    }

    public String name() {
        return name;
    }

    public String toString() {
        if (isDone) {
            return index + ".[X] " + name; 
        } else {
            return index + ".[ ] " + name; 
        }
    }
}
