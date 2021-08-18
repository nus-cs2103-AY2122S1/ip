public class Task {
    private String content;
    private boolean isCompleted;

    public Task(String content){
        this.content = content;
        this.isCompleted = false;
    }

    boolean isComplete(){
        return this.isCompleted;
    }

    void markComplete(){
        this.isCompleted = true;
    }

    @Override
    public String toString(){
        String cross = this.isCompleted?"X":" ";
        return "[" + cross + "] " + this.content;
    }

}
