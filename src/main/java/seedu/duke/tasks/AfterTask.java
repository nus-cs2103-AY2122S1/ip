package seedu.duke.tasks;

public class AfterTask {
    private String description;

    public AfterTask(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public ToDos getTask() {
        return new ToDos(this.description);
    }
}
