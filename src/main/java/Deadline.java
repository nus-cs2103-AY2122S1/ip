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

    // prints in red if the deadline is expired, yellow if deadline is today
    @Override
    public String toString() {
        String color = isDone()
                ? ""
                : isToday()
                ? StringFormatter.ANSI_YELLOW
                : isExpired()
                ? StringFormatter.ANSI_RED
                : "";

        return color +
                "[D] " + super.toString() + " (by: " + date + ")" +
                (isToday() || isExpired() ? StringFormatter.ANSI_RESET : "");
    }

    @Override
    public String getSaveString() {
        return "d," + super.getSaveString() + "," + date;
    }
}
