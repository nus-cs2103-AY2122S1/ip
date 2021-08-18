public class Task {
    boolean done;
    String title;
    String check;

    Task(String title) {
        this.done = false;
        this.title = title;
        this.check = "[ ]";
    }

    String printTask() {
        return check + title;
    }

    void markAsDone() {
        this.check = "[X]";
    }

    void markAsUndone() {
        this.check = "[ ]";
    }



}
