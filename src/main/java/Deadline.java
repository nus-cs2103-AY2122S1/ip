import java.time.LocalDate;

public class Deadline extends Task{
    private LocalDate time;

    public Deadline(String name, LocalDate time) {
        super(name, "D");
        this.time = time;
    }

    public LocalDate getTime() {
        return time;
    }
}
