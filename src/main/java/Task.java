import java.util.ArrayList;

public class Task {

    private boolean completed;
    private String task;

    public Task(String task) {
        this.completed = false;
        this.task = task;
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
        int num = Manager.getIndex(this) + 1;
        String str = tick + num + ". " + this.task;
        return str;
    }

}

