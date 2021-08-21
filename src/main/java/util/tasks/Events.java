package util.tasks;

import java.time.LocalDate;

public class Events extends DatedTask {
    private static String label = "[E]";

    public Events(String name, LocalDate date) {
        super(name.trim(), date);

    }

    @Override
    public String toString() {
        return this.label + super.toString() + " (at: " + this.localDate() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Events) {
            Events e = (Events) obj;
            return this.name.equals(e.name) && e.lDate.equals(this.lDate);
        }
        return false;
    }
}
