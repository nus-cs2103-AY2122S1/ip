public class Task {
    private boolean done;
    private String title;
    private int indexNumber;

    public Task(String title, int indexNumber) {
        this.title = title;
        this.done = false;
        this.indexNumber = indexNumber;
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

    public int getIndexNumber() {
        return this.indexNumber;
    }

    public String getInfo() {
        return "[ ] " + this.title;
    }

    @Override
    public String toString() {
        if (!done) {
            return this.indexNumber + ".[ ] " + this.title;
        } else {
            return this.indexNumber + ".[X] " + this.title;
        }

    }



}