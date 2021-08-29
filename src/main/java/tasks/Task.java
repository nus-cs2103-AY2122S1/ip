package tasks;

import viper.Instruction;

public class Task {
    protected String description;
    protected boolean isDone;
    protected Instruction type;
    
    public Task(String description, Instruction type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    
    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
