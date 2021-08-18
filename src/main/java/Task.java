public class Task {
    private String type;
    private String status;
    private String todo;
    private String time;

    public Task(String type, String todo, String time) {
        this.type = type;
        this.todo = todo;
        this.status = "[ ]";
        this.time = time;
    }

    public String getTask() {
        String str = type + status + " " + todo + time;
        return str;
    }

    public String done() {
        this.status = "[X]";
        String str = type + status + " " + todo + " " + time;
        return "Nice! I've marked this task as done:\n" + str;
    }

    public String getTodo() {
        return todo;
    }

    public String getStatus() {
        return status;
    }
}
