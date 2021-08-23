import java.time.LocalDate;

public class Event extends Task {
    private LocalDate date;

    public Event(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public boolean isToday() {
        return date.equals(LocalDate.now());
    }

    public static Event load(String[] loadData) {
        boolean done = loadData[1].equals("o");
        String name = loadData[2];
        LocalDate time = LocalDate.parse(loadData[3]);

        Event event = new Event(name, time);
        if (done) {
            event.doTask();
        }

        return event;
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
        return "[E] " + super.toString() + " (at: " + date + ")";
    }

    @Override
    public String getSaveString() {
        return "e," + super.getSaveString() + "," + date;
    }
}
