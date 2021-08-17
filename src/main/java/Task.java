public class Task {
    private boolean done;
    private String title;
    public Task(String title) {
        this.title = title;
        this.done = false;

    }

    public void setDone(boolean status) {
        done = status;
        System.out.println(String.format("Nice! I've marked this task as done:\n[X]" + this.title));
    }

    public boolean getDone() {
        return this.done;
    }

    public String getTitle() {
        return this.title;
    }



    public String getInfo() {
        return "[ ] " + this.title;
    }

    @Override
    public String toString() {
        if (!done) {
            return "[ ] " + this.title;
        } else {
            return "[X] " + this.title;
        }

    }



}