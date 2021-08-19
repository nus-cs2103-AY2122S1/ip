public class Task {
    private String name;
    private Boolean completed = false;

    public Task(String name) {
        this.name = name;
    }

    public void doneTask() {
        this.completed = true;
    }

    public String toString() {
        if(completed){
            return "[X] " + name;
        }
        return "[ ] " + name;
    }
}
