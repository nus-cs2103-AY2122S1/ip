package duke.commands;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String logo;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.logo = "N";
    }

    public Task(String description, String logo) {
        this.description = description;
        this.isDone = false;
        this.logo = logo;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String markDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n  [X] %s", this.description);
    }

    public String getLogo() {
        return this.logo;
    }

    public boolean checkDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
