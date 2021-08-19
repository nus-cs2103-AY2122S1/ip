import java.time.LocalDate;

public class Task {
    private boolean done = false;
    private String description;

    public Task(String description) {
        this.description = description;
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
}