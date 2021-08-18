public class Task {
    private String name;
    private boolean isDone;

    public Task(String name){
        this.name = name;
        this.isDone = false;
    }

    public String getName(){
        return name;
    }

    public void markDone(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        if (this.isDone){
            return "[X] " + name;
        }
        else {
            return "[ ] " + name;
        }
    }
}