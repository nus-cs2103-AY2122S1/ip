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
        System.out.println(String.format("Nice! I've marked this task as done:\n  [X] " + this.title));
    }

    @Override
    public String toString() {
        if (!done) {
            return String.format(this.indexNumber + ".[ ] " + this.title);
        } else {
            return String.format(this.indexNumber + ".[X] " + this.title);
        }

    }



}
