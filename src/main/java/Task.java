import java.io.Serializable;

public class Task implements Executable, Serializable{
    protected String name; 
    protected boolean isDone = false; 

    public Task(String name) {
        this.name = name; 
    }
    
    public void done() {
        isDone = true; 
    }

    public String name() {
        return name;
    }

    public void execute(TaskList taskList) {
        taskList.addTask(this);
    }

    public String toString() {
        if (isDone) {
            return "[X] " + name; 
        } else {
            return "[ ] " + name; 
        }
    }
}