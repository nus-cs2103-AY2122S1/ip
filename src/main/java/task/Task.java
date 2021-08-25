package task;

import java.time.LocalDate;

public abstract class Task {
    private final String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        done = false;
    }

    public void setDone() {
        done = true;
    }

    public String listEntry() {
        if (done) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }

    public String databaseEntry() {
        if (done) {
            return " | 1 | " + taskName;
        } else {
            return " | 0 | " + taskName;
        }
    }

    public boolean containsKeyword(String keyword) {
        return taskName.contains(keyword);
    }
    
    public abstract boolean isTodayTask(LocalDate l);
}
