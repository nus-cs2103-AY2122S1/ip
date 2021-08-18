public class Task {
    protected String title;
    protected Boolean done;

    Task(String title) {
        this.title = title;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public Boolean getDone() {
        return this.done;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.title;
        } else {
            return "[ ] " + this.title;
        }
    }
}
