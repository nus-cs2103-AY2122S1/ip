public class Task {
    private boolean isDone;
    private String Todo;

    public Task(String Todo){
        this.isDone = false;
        this.Todo = Todo;
    }

    public void completed(){
        this.isDone = true;
    }

    public String getString(){
        return this.Todo;
    }

    public String toString(){
        if(this.isDone){
            return "[X] " + this.getString();
        } else {
            return "[ ] " + this.getString();
        }
    }
}
