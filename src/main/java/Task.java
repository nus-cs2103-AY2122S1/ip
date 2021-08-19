import java.time.LocalDate;

public class Task {
    private boolean done = false;
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public Task(boolean done, String description) {
        this.description = description;
        this.done = done;
    }

    public void setDone() {
        done = true;
    }

    @Override
    public String toString() {
        char indicator = ' ';
        if (done) {
            indicator = 'X';
        }
        return "[" + indicator + "] " + this.description;
    }

    public String saveString() {
        char indicator = '0';
        if (done) {
            indicator = '1';
        }
        return indicator + "-" + this.description;
    }
}