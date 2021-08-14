public class Task {
    private String content;
    private boolean status;

    Task(String content) {
        this.content = content;
        this.status = false;
    }

    public String getContent() {
        return this.content;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void doneTask() {
        this.status = true;
    }

}
