package skeltal;
public class Task {

    private boolean completed;
    private String task;

    public Task(String task) {
        this.completed = false;
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public String store() {
        int done = this.isCompleted() ? 1 : 0;
        return done + " | " + this.getTask();
    }


    public boolean isCompleted() {
        return this.completed;
    }

    public void setComplete() {
        this.completed = true;
    }



    @Override
    public String toString() {
        String tick = this.isCompleted() ? "[X] " : "[ ] ";
        int num = TaskList.getIndex(this) + 1;
        String str = tick + num + ". " + this.task;
        return str;
    }

}

