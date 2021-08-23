import org.w3c.dom.Text;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public boolean isToday() {
        return date.equals(LocalDate.now());
    }

    public static Deadline load(String[] loadData) {
        boolean done = loadData[1].equals("o");
        String name = loadData[2];
        LocalDate time = LocalDate.parse(loadData[3]);

        Deadline deadline = new Deadline(name, time);
        if (done) {
            deadline.doTask();
        }

        return deadline;
    }

    @Override
    public boolean isExpired() {
        return date.isBefore(LocalDate.now());
    }

    @Override
    public TextColor getListColor() {
        return isDone()
                ? TextColor.DEFAULT
                : isToday()
                ? TextColor.YELLOW
                : isExpired()
                ? TextColor.RED
                : TextColor.DEFAULT;
    }

    // prints in red if the deadline is expired, yellow if deadline is today
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + date + ")";
    }

    @Override
    public String getSaveString() {
        return "d," + super.getSaveString() + "," + date;
    }
}
