public class Task {
    private String todo;
    private String status;

    public Task(String todo) {
        this.todo = todo;
        this.status = "[ ]";
    }

    public String getTask() {
        String str = status + " " + todo;
        return str;
    }

    public String done() {
        this.status = "[X]";
        String str = status + " " + todo;
        return "Nice! I've marked this task as done:\n" + str;
    }
}
